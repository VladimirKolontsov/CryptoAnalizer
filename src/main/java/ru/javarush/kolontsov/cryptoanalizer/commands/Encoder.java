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

public class Encoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        String originalText = parameters[0];
        String encryptedText = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        HashMap<Character, Integer> indexOfAlphabet = new HashMap<>();
        for (int i = 0; i < Constants.ALPHABET.length; i++) {
            indexOfAlphabet.put(Constants.ALPHABET[i], i);
        }
        try (
                BufferedReader reader = Files.newBufferedReader(Path.of(originalText));
                BufferedWriter writer = Files.newBufferedWriter(Path.of(encryptedText))
        ) {
            while (reader.ready()) {
                Character character = (char) reader.read();
                if (indexOfAlphabet.containsKey(character)) {
                    Integer index = indexOfAlphabet.get(character);
                    index = (index + key) % Constants.ALPHABET.length;
                    writer.write(Constants.ALPHABET[index]);
                } else {
                    writer.append(character);
                }
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
        return null;
    }
}
