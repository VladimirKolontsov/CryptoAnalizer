package ru.javarush.kolontsov.cryptoanalizer.commands;

import ru.javarush.kolontsov.cryptoanalizer.brute.CheckWords;
import ru.javarush.kolontsov.cryptoanalizer.brute.CondCheck;
import ru.javarush.kolontsov.cryptoanalizer.brute.DecodeInputText;
import ru.javarush.kolontsov.cryptoanalizer.constants.Constants;
import ru.javarush.kolontsov.cryptoanalizer.entity.Result;
import ru.javarush.kolontsov.cryptoanalizer.entity.ResultCode;
import ru.javarush.kolontsov.cryptoanalizer.exceptions.AppException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BruteForce implements Action{
    @Override
    public Result execute(String[] parameters) {
        //parameters
        String encodedText = parameters[0];
        String decoderText = parameters[1];
        int key;
        StringBuilder stock = new StringBuilder();
        int alignment = 0;

        List<Character> indexOfAlphabet = new ArrayList<>();
        for (int i = 0; i < Constants.ALPHABET.length; i++) {
            indexOfAlphabet.add(Constants.ALPHABET[i]);
        }

        //searching the key
        for (int j = 0; j < Constants.ALPHABET.length; j++) {
            StringBuilder result = new StringBuilder();

            //encrypt text
            try (BufferedReader reader = Files.newBufferedReader(Path.of(encodedText))) {
                DecodeInputText.decodeInputText(indexOfAlphabet, j, result, reader);
                //check encrypted text
                if (CondCheck.condCheck(result.toString()) == 0) {
                    if (CheckWords.checkWords(result.toString()) > alignment) {
                        alignment = CheckWords.checkWords(result.toString());
                        stock = result;
                        key = j;
                        stock.append("\n Ключ: ").append(key);
                    }
                } else {
                    result.delete(0, result.length());
                }
            } catch (IOException e) {
                throw new AppException(e.getMessage(), e);
            }
        }

        // write decoded text to a new file
        writeDecodedText(decoderText, stock, indexOfAlphabet);

        return new Result("BruteForce complete", ResultCode.OK);
    }

    private void writeDecodedText(String decoderText, StringBuilder stock, List<Character> indexOfAlphabet) {
        try (BufferedWriter writer= Files.newBufferedWriter(Path.of(decoderText))) {
            for (int i = 0; i < stock.length(); i++) {
                char character = stock.charAt(i);
                if (!indexOfAlphabet.contains(character)) {
                    writer.write(character);
                    continue;
                }
                writer.write(character);
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
    }
}
