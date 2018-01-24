package com.hjp.rabbitmq.rabbitmq.samples3;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

	private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

	@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message) {
		
		logger.info("to send message:{}", message);
		amqpTemplate.convertAndSend("queueTestKey", message);

	}
}
