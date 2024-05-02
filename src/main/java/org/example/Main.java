package org.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        Deque<ICommand> queue = new ArrayDeque<>();
        MyCommand myCommand = new MyCommand(queue);

        ICommand c1 = new Test1();

        myCommand.registerHendler();

        ICommand c2 = new Test2();

        queue.add(c1);
        queue.add(c2);

        myCommand.processCommands();
    }
}