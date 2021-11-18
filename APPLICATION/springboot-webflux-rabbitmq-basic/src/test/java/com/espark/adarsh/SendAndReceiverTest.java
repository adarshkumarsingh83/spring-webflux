package com.espark.adarsh;

import com.espark.adarsh.receiver.MessageReceiver;
import com.espark.adarsh.sender.MesageSender;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendAndReceiverTest {


    @Test
    public void SenderReceiver() throws Exception {
        String queue = "espark-queue";
        int count = 10;
        CountDownLatch sendLatch = new CountDownLatch(count);
        CountDownLatch receiveLatch = new CountDownLatch(count);
        MessageReceiver receiver = new MessageReceiver();
        receiver.consume(queue, receiveLatch);
        MesageSender sender = new MesageSender();
        sender.send(queue, count, sendLatch);
        assertTrue(sendLatch.await(10, TimeUnit.SECONDS));
        assertTrue(receiveLatch.await(10, TimeUnit.SECONDS));
        receiver.close();
        sender.close();
    }
}
