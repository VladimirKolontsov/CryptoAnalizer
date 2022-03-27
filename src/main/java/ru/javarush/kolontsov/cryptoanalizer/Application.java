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
    public Result run(String[] args) throws AppException {
        if (args.length > 0) {
            String action = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            return mainController.doAction(action, parameters);
        } else {
            //TODO ---  ошибка есть - а обработки нет
            throw new AppException("no args");
        }
    }
}
