package ru.javarush.kolontsov.cryptoanalizer.commands;

import ru.javarush.kolontsov.cryptoanalizer.entity.Result;
import ru.javarush.kolontsov.cryptoanalizer.entity.ResultCode;

public class Decoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        //TO DO something do
        return new Result("decode all right", ResultCode.OK);
    }
}
