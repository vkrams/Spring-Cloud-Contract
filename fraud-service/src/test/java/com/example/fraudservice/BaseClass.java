package com.example.fraudservice;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vikramt on 26/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FraudServiceApplication.class)
@AutoConfigureMessageVerifier
public class BaseClass {
    @Autowired
    FraudController fraudController;

    @Before
    public void setup()
    {
        RestAssuredMockMvc.standaloneSetup(fraudController);
    }

    public void triggerMessage()
    {
        this.fraudController.message();
    }
}
