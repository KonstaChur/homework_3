package org.example;


public class Test1 implements ICommand {

    @Override
    public void execute() {
        throw new RuntimeException("Test1 Exception");
    }
}
