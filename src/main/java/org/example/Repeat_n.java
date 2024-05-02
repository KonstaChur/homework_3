package org.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Repeat_n implements ICommand {

    private final ICommand command_1;
    private final ICommand command_2;
    private int repeat;

    public Repeat_n(ICommand command_1, ICommand command_2, int repeat) {
        this.command_1 = command_1;
        this.command_2 = command_2;
        this.repeat = repeat;
    }

    @Override
    public void execute() throws RuntimeException {
        try {
            command_1.execute();
        } catch (Exception e) {
            while (repeat > 0) {
                repeat--;
                try {
                    command_1.execute();
                } catch (Exception e1) {
                }
            }
            command_2.execute();
        }
    }
}
