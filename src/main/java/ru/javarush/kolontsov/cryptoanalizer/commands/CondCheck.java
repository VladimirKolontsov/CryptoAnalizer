package ru.javarush.kolontsov.cryptoanalizer.commands;

public class CondCheck {
    public static int condCheck(String result) {
        if (result.contains("Ъ") | result.contains("Ь")) {
            return 1;
        }

        for (int i = 0; i < result.length(); i++) {
            if (symbols(result, i) == 1) {
                return 1;
            }
        }
        return 0;
    }

    public static int symbols(String result, int i) {
        if (i < result.length() - 1) {
            if ("ьъйёчыщЬЪЙЁЧЫЩ".contains(String.valueOf(result.charAt(i))) &&
                    String.valueOf(result.charAt(i)).equalsIgnoreCase(String.valueOf(result.charAt(i+1)))) {
                return 1;
            }
        }
        return 0;
    }


}