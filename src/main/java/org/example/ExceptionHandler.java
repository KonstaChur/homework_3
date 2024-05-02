package org.example;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExceptionHandler {

    private static final Map<Type, Map<Type, ICommand>> handels = new HashMap<>();

    public static ICommand handle(ICommand command, Exception exception) {
        try {
            return handels.get(command.getClass()).get(exception.getClass());
        } catch (Exception ex) {
            log.error("Ошибка {}  во время выполнения действия для command = {} и exception = ", ex.getMessage(), command.getClass(), exception);
        }
        return null;
    }

    public static void setHandlers(ICommand command, Exception exception, ICommand command_2) {
        Map<Type, ICommand> getCommand = new HashMap<>();
        Type et = exception.getClass();
        Type ct = command.getClass();
        getCommand.put(et, command_2);
        handels.put(ct, getCommand);
    }
}
