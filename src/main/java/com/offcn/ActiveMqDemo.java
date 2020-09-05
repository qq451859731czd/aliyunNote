package com.offcn;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMqDemo {
    public static void main(String[] args) {
//        创建静态工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.188.146:61616");
        try {
//            创建连接
            Connection connection = factory.createConnection();
//            启动连接
            connection.start();
//            获得session 参数1:是否开启事务,参数2:AUTO_ACKNOWLEDGE=1 消息确认模式
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            创建队列 并给队列赋名
            Queue queue=session.createQueue("queue-one-one");
//            创建生产者
            MessageProducer producer = session.createProducer(queue);
//            创建消息
            TextMessage textMessage = session.createTextMessage("123456789");
//            发送消息
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
