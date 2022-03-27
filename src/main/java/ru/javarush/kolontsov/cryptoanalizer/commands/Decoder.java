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

public class Decoder implements Action{
    @Override
    public Result execute(String[] parameters) {

        String encodedText = parameters[0];
        String decoderText = parameters[1];
        int key = Integer.parseInt(parameters[2]);


        List<Character> indexOfAlphabet = new ArrayList<>();
        for (int i = 0; i < Constants.ALPHABET.length; i++) {
            indexOfAlphabet.add(Constants.ALPHABET[i]);
        }

        try (
                BufferedReader reader = Files.newBufferedReader(Path.of(encodedText));
                BufferedWriter writer= Files.newBufferedWriter((Path.of(decoderText)))
        ) {
            while (reader.ready()) {
                char character = (char) reader.read();
                if (indexOfAlphabet.contains(character)) {
                    int index = indexOfAlphabet.indexOf(character);
                    //TODO ---  отличие кодера от декодера только в этой строчке а значит идет дубликация кода
                    index = (index - key) % Constants.ALPHABET.length;
                    writer.write(Constants.ALPHABET[index]);
                } else {
                    writer.append(character);
                }
            }
        } catch (IOException e) {
                throw new AppException(e.getMessage(), e);
        }
        return new Result("Decoder complete", ResultCode.OK);
    }
}
