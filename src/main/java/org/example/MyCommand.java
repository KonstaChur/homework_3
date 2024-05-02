package org.example;


import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
public class MyCommand {

    private final Queue<ICommand> queue;

    public MyCommand(Queue<ICommand> queue) {
        this.queue = queue;
    }

    public void processCommands() {
        while (!queue.isEmpty()) {
            ICommand c = queue.poll();
            try {
                c.execute();
            } catch (Exception e) {
                ICommand handledCommand = ExceptionHandler.handle(c, e);
                try {
                    handledCommand.execute();
                }catch (Exception exception){
                    log.error("Что то пошло не так. Ошибка {}", exception.getMessage());
                }
            }
        }
    }

    public void registerHendler(){
        ICommand c1  = new Test1();
        ICommand c2 = new Test2();
        RuntimeException re = new RuntimeException();
//        Реализовать обработчик исключения, который ставит Команду, пишущую в лог в очередь Команд.
        ICommand addQueueLog = new QueueService(new LogExceptionCommand(re), queue);
        ExceptionHandler.setHandlers(c1, re, addQueueLog);
//        Реализовать обработчик исключения, который ставит в очередь Команду - повторитель команды, выбросившей исключение.
        ICommand addQueueRepeat = new QueueService(new Repeat(c2), queue);
        ExceptionHandler.setHandlers(c2, re, addQueueRepeat);
//        при первом выбросе исключения повторить команду, при повторном выбросе исключения записать информацию в лог
        ICommand repeat_1 = new Repeat_n(new Repeat(c2), addQueueLog, 1);
        ExceptionHandler.setHandlers(c2, re, repeat_1);
//        повторить два раза, потом записать в лог.
        ICommand repeat_2 = new Repeat_n(new Repeat(c2), addQueueLog, 2);
        ExceptionHandler.setHandlers(c2, re, repeat_2);

    }
}

