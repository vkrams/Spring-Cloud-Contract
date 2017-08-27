package com.example.carrental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}
}

@Slf4j
@Component
class FraudListener
{
	String name;
	@StreamListener(Sink.INPUT)
	public void onFraudMessage(Fraud f)
	{
		this.name = f.getName();
		log.info("Name: {}", this.name);
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Fraud
{
	private String name;
}

