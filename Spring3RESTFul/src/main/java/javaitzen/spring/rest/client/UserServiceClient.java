package javaitzen.spring.rest.client;

import javaitzen.spring.rest.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * The Class UserServiceClient.
 */
@Component("userClient")
public class UserServiceClient {

    @Autowired
    protected RestTemplate restTemplate;
    private final static String serviceUrl = "http://127.0.0.1:7001/Spring3RESTFul-1/app/users/";

    /**
     * Retrieve user details.
     * 
     * @param firstName
     *            the first name
     * @return the user
     */
    public User retrieveUserDetails(final String firstName) {
        System.out.println("Client: get");
        return restTemplate.getForObject(serviceUrl + "{firstName}", User.class, firstName);
    }
    

    /**
     * Creates the new user.
     * 
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     * @param userName
     *            the user name
     * @return the user
     */
    public User createNewUser(final String firstName, final String lastName, final String userName) {
        System.out.println("Client: post");
        User newUser = new User(firstName, lastName, userName);
        newUser = restTemplate.postForObject(serviceUrl, newUser, User.class);
        return newUser;
    }
    
    /**
     * Update user details.
     * 
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     * @param userName
     *            the user name
     */
    public void updateUserDetails(final String firstName, final String lastName, final String userName) {
        System.out.println("Client: put");
        User newUser = new User(firstName, lastName, userName);
        restTemplate.put(serviceUrl + "{firstName}", newUser, firstName);
    }

    /**
     * Delete user details.
     * 
     * @param firstName
     *            the first name
     * @return true, if successful
     */
    public boolean deleteUserDetails(final String firstName) {        
        System.out.println("Client: delete");
        try{
            restTemplate.delete(serviceUrl + "{firstName}", firstName);
        }catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

        
    
}
