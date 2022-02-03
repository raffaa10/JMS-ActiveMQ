package rafa.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class MessageReceiver {

    /**
     * ConnectionFactory ->  Connection ->  Session
     * ->  Session -> Destination
     *     Session -> MessageConsumer ->
     * MessageConsumer -> TextMessage
     */

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String queueName = "MY_MESSAGE_QUEUE";

    public static void main(String[] args) throws Exception {

        System.out.println("url = " + url);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queueName);

        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();

        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Received message '" + textMessage.getText() + "'");
        }

        connection.close();

    }


}
