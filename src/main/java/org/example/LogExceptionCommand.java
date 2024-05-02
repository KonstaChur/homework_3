package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class LogExceptionCommand implements ICommand{

    private final Exception exception;

    @Override
    public void execute() {
        log.info("Пишем в лог");
        log.info( exception.toString());
    }
}
