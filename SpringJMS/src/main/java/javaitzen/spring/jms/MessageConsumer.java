package javaitzen.spring.jms;

import javaitzen.spring.jms.message.ExampleMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.springframework.jms.core.JmsTemplate;
 

public class MessageConsumer {

    private JmsTemplate jmsTemplate;
    private String queue;

    public MessageConsumer() {
    }

    public MessageConsumer(final String queue) {
        this.queue = queue;
    }
    
    public ExampleMessage receiveMessage() throws JMSException  {

        final Message message = jmsTemplate.receive(queue);
        
        ExampleMessage tehMessage  = null;
        if (message instanceof ObjectMessage) {
          ObjectMessage oMessage = (ObjectMessage) message;
            tehMessage = (ExampleMessage) oMessage.getObject();        
            oMessage.acknowledge();
        }
        return tehMessage;
    }


    public final void setQueue(final String queue) {
        this.queue = queue;
    }

    public final void setJmsTemplate(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


}
