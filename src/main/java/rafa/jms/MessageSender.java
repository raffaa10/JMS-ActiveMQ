package rafa.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageSender {

    /**
     * ConnectionFactory ->  Connection ->  Session
     * ->  Session -> Destination (Queue or Topic)
     *     Session -> MessageProducer ->
     * MessageProducer -> TextMessage
     */

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String queueName = "MY_MESSAGE_QUEUE";

    public static void main(String[] args) throws JMSException {

        System.out.println(" url = " + url);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queueName);

        MessageProducer producer = session.createProducer(destination);

        TextMessage textMessage = session.createTextMessage("Hi there! My name is Rafa.");

        producer.send(textMessage);

        System.out.println("Message '" + textMessage.getText() + "', Sent Successfully to th Queue");

        connection.close();

    }


}
