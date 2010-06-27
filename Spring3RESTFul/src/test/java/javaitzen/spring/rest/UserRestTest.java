package javaitzen.spring.rest;

import static org.junit.Assert.assertNotNull;
import javaitzen.spring.rest.client.UserServiceClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"/javaitzen/spring/rest/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestTest {

    @Autowired
    UserServiceClient client;
    
    @Before
    public void setup(){
    }
    
    @Test
    public void testRest() throws Exception{
        User user = client.getUserDetails("Mary");
        assertNotNull(user);
    }
    
}
