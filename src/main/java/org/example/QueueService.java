package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
public class QueueService implements ICommand {

    private final ICommand command;
    private final Queue<ICommand> queue;

    public QueueService(ICommand originalCommand, Queue<ICommand> queue) {
        this.command = originalCommand;
        this.queue = queue;
    }

    @Override
    public void execute() {
        queue.add(command);
    }
}
