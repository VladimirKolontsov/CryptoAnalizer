package ru.javarush.kolontsov.cryptoanalizer;

import ru.javarush.kolontsov.cryptoanalizer.entity.Result;

public class ConsoleRunner {
    public static void main(String[] args) {
        Application application = new Application();
        Result result = application.run(args);
        System.out.println(result);
    }
}

//    int key = 3; //ключ по заданию - args
// какой-то исходный текст - args
// зашифрованный текст - args