package javaitzen.spring.jms;


import javaitzen.spring.jms.message.ExampleMessage;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.stereotype.Component;


@Component
public class JmsMessageListener implements MessageListener { 

    
    /**
     * Implementation of <code>MessageListener</code>.
     */
    public void onMessage(final Message message) {
        try {               
            if (message instanceof ObjectMessage) {
                ObjectMessage oMessage = (ObjectMessage) message;
                ExampleMessage tehMessage = (ExampleMessage) oMessage.getObject();
                System.out.println("Message Listener: Result Queue:" + tehMessage.getContent().getContentString());
                oMessage.acknowledge();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
