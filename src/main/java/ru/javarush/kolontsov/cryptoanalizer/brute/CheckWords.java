package ru.javarush.kolontsov.cryptoanalizer.brute;

public class CheckWords {


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
