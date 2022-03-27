package ru.javarush.kolontsov.cryptoanalizer.commands;

import ru.javarush.kolontsov.cryptoanalizer.entity.Result;

public interface Action {
    Result execute(String[] parameters);

}
