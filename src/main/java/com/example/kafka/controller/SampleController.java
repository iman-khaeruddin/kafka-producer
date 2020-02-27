package com.example.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.model.User;

@RestController
@RequestMapping("kafka/")
public class SampleController {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate2;
	
	private String TOPIC_1 = "topic1";
	private String TOPIC_2 = "topic2";

	/**
	 * @param name
	 *
	 * Publish user object.
	 * */
	@GetMapping("publish/json/{name}")
	public String Post1(@PathVariable("name") String name) {
		
		kafkaTemplate.send(TOPIC_1 , new User(name, "TECH"));
		
		return "publish successfully";
	}

	/**
	 * @param message
	 *
	 * Publish string.
	 * */
	@GetMapping("publish/{message}")
	public String Post2(@PathVariable("message") String message) {
		
		kafkaTemplate2.send(TOPIC_2 , message);
		
		return "publish successfully";
	}
}
