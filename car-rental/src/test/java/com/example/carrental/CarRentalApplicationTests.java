package com.example.carrental;

import com.github.tomakehurst.wiremock.client.WireMock;
import groovy.util.logging.Log;
import groovy.util.logging.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock (port = 8081)
public class CarRentalApplicationTests {

	@Rule
	public StubRunnerRule stubRunnerRule = new StubRunnerRule()
			.downloadStub("com.example","fraud-service")
			.workOffline(true)
			.withPort(8083);

	@Test
	public void contextLoads() {
	}

	@Test
	public void test_should_return_all_frauds()
	{
		String json = "[\"marcin\",\"josh\"]";

		WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/frauds"))
				.willReturn(WireMock.aResponse().withBody(json).withStatus(201)));
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8081/frauds", String.class);
		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(201);
		BDDAssertions.then(entity.getBody()).isEqualTo(json);
	}


	@Test
	public void test_should_return_all_frauds_integration()
	{
		String json = "[\"marcin\",\"josh\"]";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8083/fraud", String.class);
		BDDAssertions.then(entity.getStatusCodeValue()).isEqualTo(201);
		BDDAssertions.then(entity.getBody()).isEqualTo(json);
	}
}
