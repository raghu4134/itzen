package javaitzen.spring.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import javaitzen.spring.rest.client.UserServiceClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The Class UserRestTest.
 */
@ContextConfiguration(locations={"/javaitzen/spring/rest/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestTest {

    @Autowired
    private UserServiceClient client;
    
    private User user;   
    
    /**
     * Setup.
     */
    @Before
    public void setup(){
       user = client.createNewUser("Ray", "Pingbo", "NI");
    }
    
    /**
     * After.
     */
    @After
    public void after(){
        client.deleteUserDetails(user.getUserId());
    }
    
    /**
     * Test post rest.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testPostRest() throws Exception{
        assertEquals(user.getFirstName(), "Ray");
        assertEquals(user.getLastName(), "Pingbo");
    }
    
    /**
     * Test get rest.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testGetRest() throws Exception{
        user = client.retrieveUserDetails(user.getUserId());
        assertEquals(user.getFirstName(), "Ray");
        assertEquals(user.getLastName(), "Pingbo");

    }
    
    /**
     * Test put rest.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testPutRest() throws Exception{
        user.setFirstName("Bob");
        user.setLastName("Bean");
        client.updateUserDetails(user);
        user = client.retrieveUserDetails(user.getUserId());
        assertEquals(user.getFirstName(), "Bob");
        assertEquals(user.getLastName(), "Bean");

    }
    
   
}
