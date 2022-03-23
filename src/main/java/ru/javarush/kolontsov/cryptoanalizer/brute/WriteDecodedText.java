package ru.javarush.kolontsov.cryptoanalizer.brute;

import ru.javarush.kolontsov.cryptoanalizer.exceptions.AppException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteDecodedText {

    public static void writeDecodedText(String decoderText, StringBuilder stock, List<Character> indexOfAlphabet) {
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
