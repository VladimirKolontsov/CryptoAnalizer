package ru.javarush.kolontsov.cryptoanalizer.commands;

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

        List<Character> indexOfAlphabet = new ArrayList<>();
        for (int i = 0; i < Constants.ALPHABET.length; i++) {
            indexOfAlphabet.add(Constants.ALPHABET[i]);
        }

        //searching the key
        for (int j = 0; j < Constants.ALPHABET.length; j++) {
            StringBuilder result = new StringBuilder();

            //encrypt text
            try (BufferedReader reader = Files.newBufferedReader(Path.of(encodedText))) {
                while (reader.ready()) {
                    Character character = (char) reader.read();
                    if (!indexOfAlphabet.contains(character)) {
                        result.append(character);
                    } else {
                        int old = indexOfAlphabet.indexOf(character);
                        int newIndex = (old - j) % Constants.ALPHABET.length;
                        if (newIndex < 0) {
                            newIndex = Constants.ALPHABET.length + newIndex;
                        }
                        result.append(Constants.ALPHABET[newIndex]);
                    }
                }
                //check encrypted text
                if (CondCheck.condCheck(result.toString()) == 0) {
                    stock = result;
                    key = j;
                    stock.append("\n Ключ: ").append(key);
                } else {
                    result.delete(0, result.length());
                }
            } catch (IOException e) {
                throw new AppException(e.getMessage(), e);
            }
        }

        // write decoded text to a new file
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

        return new Result("BruteForce complete", ResultCode.OK);
    }
}
