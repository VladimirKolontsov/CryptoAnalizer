package ru.javarush.kolontsov.cryptoanalizer;

import ru.javarush.kolontsov.cryptoanalizer.controllers.MainController;
import ru.javarush.kolontsov.cryptoanalizer.entity.Result;
import ru.javarush.kolontsov.cryptoanalizer.exceptions.AppException;

import java.util.Arrays;

public class Application {

    private final MainController mainController;

    public Application() {
        mainController = new MainController();
    }

    public Result run(String[] args) {
        //encode text.txt encode.txt 3
        if (args.length > 0) {  //encode
            String action = args[0];
            //parameters text.txt encode.txt 3
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            return mainController.doAction(action, parameters);
        } else {
            throw new AppException("no args");
        }
    }
}
