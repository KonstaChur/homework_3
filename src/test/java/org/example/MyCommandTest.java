package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.Queue;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class MyCommandTest {

    @Mock
    private ICommand c2;

    @BeforeEach
    void setup() {
        doThrow(new RuntimeException()).when(c2).execute();
    }

    @Test
    void testRepeat2Command() {
        Queue<ICommand> queue = new LinkedList<>();
        MyCommand myCommand = new MyCommand(queue);
        myCommand.registerHendler();
        ICommand addQueueLog = new QueueService(new LogExceptionCommand(new RuntimeException()), queue);

        ICommand repeat2 = new Repeat_n(new Repeat(c2), addQueueLog, 1);
        queue.add(repeat2);

        Assertions.assertEquals(1, queue.size());
        Assertions.assertTrue(queue.contains(repeat2));

        myCommand.processCommands();

        verify(c2, times(2)).execute();
    }
}



