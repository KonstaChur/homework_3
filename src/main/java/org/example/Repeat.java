package org.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Repeat implements ICommand {

    private final ICommand command;

    public Repeat(ICommand command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}
