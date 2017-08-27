package com.example.fraudservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableBinding(Source.class)
public class FraudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudServiceApplication.class, args);
	}
}

@RestController
class FraudController
{
	private final Source source;

	FraudController(Source source) {
		this.source = source;
	}

	@GetMapping("/fraud")
	ResponseEntity<List<String>> fraud(){
		return ResponseEntity.status(201).body(Arrays.asList("marcin","josh"));
	}

	@PostMapping("/message")
	void message ()
	{
		source.output().send(org.springframework.messaging.support.MessageBuilder.withPayload(new Fraud("hgdsjdhjsah")).build());
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	class Fraud
	{
		String surname;
	}
}