package ru.javarush.kolontsov.cryptoanalizer.constants;

public class Constants {
    private static final String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"; //алфавит по заданию
    private static final String z = ".,\"':-!? "; // пунктуация по заданию

    public static final String ALPHABET = rus.toLowerCase() + z;
}
