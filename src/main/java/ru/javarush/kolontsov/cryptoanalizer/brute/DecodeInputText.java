package ru.javarush.kolontsov.cryptoanalizer.brute;

import ru.javarush.kolontsov.cryptoanalizer.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class DecodeInputText {

    public static void decodeInputText(List<Character> indexOfAlphabet, int j, StringBuilder result, BufferedReader reader) throws IOException {
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
    }
}
