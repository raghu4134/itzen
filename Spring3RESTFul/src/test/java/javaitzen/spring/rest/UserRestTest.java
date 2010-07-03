package javaitzen.spring.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    public void testGetRest() throws Exception{
        User user = client.retrieveUserDetails("John");
        assertEquals(user.getFirstName(), "John");
        assertEquals(user.getLastName(), "Doe");

    }
    
    @Test
    public void testPostRest() throws Exception{
        User user = client.createNewUser("Ray", "Pingbo", "NI");
        assertEquals(user.getFirstName(), "Ray");
        assertEquals(user.getLastName(), "Pingbo");
    }
    @Test
    public void testPutRest() throws Exception{
        client.updateUserDetails("Ray", "Pingbo", "NI");
    }
    
    @Test
    public void testDeleteRest(){
        assertTrue(client.deleteUserDetails("Mary"));
    }
    
}
