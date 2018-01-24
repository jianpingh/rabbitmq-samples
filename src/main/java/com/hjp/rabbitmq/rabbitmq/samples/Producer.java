package com.hjp.rabbitmq.rabbitmq.samples;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生成者
 */
public class Producer {
	private final static String QUEUE_NAME = "test.hjp";// 队列名不能重复 之前已有就会失败

	public static void main(String[] argv) throws java.io.IOException {

		/* 使用工厂类建立Connection和Channel，并且设置参数 */
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");// MQ的IP
		factory.setPort(5672);// MQ端口
		factory.setUsername("guest");// MQ用户名
		factory.setPassword("guest");// MQ密码
		Connection connection = null;
		try {
			connection = factory.newConnection();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Channel channel = connection.createChannel();

		/* 创建消息队列，并且发送消息 */
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "消息";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("生产了个'" + message + "'");

		/* 关闭连接 */
		try {
			channel.close();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
	}
}
