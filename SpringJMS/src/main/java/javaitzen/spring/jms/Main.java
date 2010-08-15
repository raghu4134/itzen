package javaitzen.spring.jms;

import javaitzen.spring.jms.message.Content;
import javaitzen.spring.jms.message.ExampleMessage;
import javaitzen.spring.jms.message.Header;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    
    @Autowired
    private MessageSender   numberSender;
    @Autowired
    private MessageConsumer numberReader;

    @Autowired
    private MessageSender   fizzSender;
    @Autowired
    private MessageSender   buzzSender;
    @Autowired
    private MessageSender   fizzBuzzSender;
    @Autowired
    private MessageSender   resultSender;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", Main.class);
        Main main = (Main)context.getBean("main");
        try {
            main.runExample();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.exit(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    public void runExample() throws Exception{
      
        for (int i = 1; i <= 100; i++) {
            ExampleMessage message = new ExampleMessage();
            message.setHeader(new Header("I am a Number"));
            message.setContent(new Content<Integer>(i));
            numberSender.sendMessage(message);
        }
        
        for (int i = 1; i <= 100; i++) {            
            ExampleMessage  message = numberReader.receiveMessage();
            Content<Integer> messageInt = message.getContent();
            Integer val = messageInt.getContentValue();
            if (val % 15 == 0) {
                message.setContent(new Content<String>("FizzBuzz"));
                fizzBuzzSender.sendMessage(message);
            } else if (val % 3 == 0) {
                message.setContent(new Content<String>("Fizz"));
                fizzSender.sendMessage(message);
            } else if (val % 5 == 0) { 
                message.setContent(new Content<String>("Buzz"));
                buzzSender.sendMessage(message);
            }
            //send all to the result queue
            resultSender.sendMessage(message);
        }
        
    }

}
