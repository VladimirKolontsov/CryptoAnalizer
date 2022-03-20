package ru.javarush.kolontsov.cryptoanalizer.commands;

import ru.javarush.kolontsov.cryptoanalizer.constants.Constants;
import ru.javarush.kolontsov.cryptoanalizer.entity.Result;
import ru.javarush.kolontsov.cryptoanalizer.exceptions.AppException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Decoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        String encodedText = parameters[0];
        String decoderText = parameters[1];
        int key = Integer.parseInt(parameters[2]);


        HashMap<Character, Integer> indexOfAlphabet = new HashMap<>();
        for (int i = 0; i < Constants.ALPHABET.length; i++) {
            indexOfAlphabet.put(Constants.ALPHABET[i], i);
        }


        try (
                BufferedReader reader = Files.newBufferedReader(Path.of(encodedText));
                BufferedWriter writer= Files.newBufferedWriter((Path.of(decoderText)))
        ) {
            while (reader.ready()) {
                Character character = (char) reader.read();
                if (!indexOfAlphabet.containsKey(character)) {
                    char c = ' ';
                    writer.write(c);
                } else {
                    Integer index = indexOfAlphabet.get(character);
                    index = (index + key) % Constants.ALPHABET.length;
                    writer.write(Constants.ALPHABET[index]);
                }
//                if (indexOfAlphabet.containsKey(character) && indexOfAlphabet.i) {
//                    Integer index = indexOfAlphabet.get(character);
//                    index = (index + key) % Constants.ALPHABET.length;
//                    writer.write(Constants.ALPHABET[index]);
//                }
            }
        } catch (IOException e) {
                throw new AppException(e.getMessage(), e);
        }
        return null;
    }
}
