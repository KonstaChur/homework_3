package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.ICommand;

@Slf4j
public class Test2 implements ICommand {

    @Override
    public void execute() {
            throw new RuntimeException("Test2 Exception");
    }
}
