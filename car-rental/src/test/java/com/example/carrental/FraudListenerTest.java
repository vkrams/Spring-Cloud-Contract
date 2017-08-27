package com.example.carrental;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vikramt on 26/08/2017.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FraudListenerTest {

    @Autowired
    private FraudListener listener;

    @Autowired
    private Sink sink;

    @Test
    public void test_should_consume_fraud_message() throws Throwable
    {
        String name = "Gz#!***8assxx@";
        Message<Fraud> msg = MessageBuilder.withPayload(new Fraud(name)).build();
        SubscribableChannel input = sink.input();
        input.send(msg);

        BDDAssertions.then(this.listener.name).isEqualTo(name);

    }
}
