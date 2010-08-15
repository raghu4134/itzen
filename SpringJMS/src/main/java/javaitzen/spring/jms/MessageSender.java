package javaitzen.spring.jms;

import javaitzen.spring.jms.message.ExampleMessage;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.ObjectMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageSender {

    private JmsTemplate jmsTemplate;
    private String queue;

    public MessageSender() { }

    public void sendMessage(final ExampleMessage message) {
        MessageCreator creator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage objectMessage = null;
                try {
                    objectMessage = session.createObjectMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return objectMessage;
            }
        };

        jmsTemplate.send(queue, creator);
    }

    public final void setQueue(final String queue) {
        this.queue = queue;
    }

    public final void setJmsTemplate(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


}
