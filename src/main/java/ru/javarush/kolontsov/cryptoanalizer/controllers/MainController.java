package ru.javarush.kolontsov.cryptoanalizer.controllers;

import ru.javarush.kolontsov.cryptoanalizer.commands.Action;
import ru.javarush.kolontsov.cryptoanalizer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters){
        //action == encode
        //parameters == [text.txt encode.txt 3]
        Action action = Actions.find(actionName);
        return action.execute(parameters);
    }
}
