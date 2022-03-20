package ru.javarush.kolontsov.cryptoanalizer.commands;

import ru.javarush.kolontsov.cryptoanalizer.entity.Result;
import ru.javarush.kolontsov.cryptoanalizer.entity.ResultCode;

public class BrudeForce implements Action{
    @Override
    public Result execute(String[] parameters) {


        return new Result("BrudeForce complete", ResultCode.OK);
    }
}
