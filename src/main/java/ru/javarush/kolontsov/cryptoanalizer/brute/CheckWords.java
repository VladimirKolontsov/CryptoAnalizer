package ru.javarush.kolontsov.cryptoanalizer.brute;

public class CheckWords {

    //TODO ---  много статических методов.
    // какие методы и когда могут быть статическими?
    // 1. не меняются 2. переносятся в другие проекты без проблем.
    // если это не так - статические методы использовать не стоит

    public static int checkWords(String result){

        int count = 0;
        for (int i=0; i < Vocabulary.vocabulary.length; i++) {
            if (result.contains(Vocabulary.vocabulary[i])) {
                count++;
            }
        }
        return count;
    }

}
