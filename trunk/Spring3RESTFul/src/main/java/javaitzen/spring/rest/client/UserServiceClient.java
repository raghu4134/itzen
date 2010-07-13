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
    private final static String serviceUrl = "http://localhost:8080/Spring3RESTFul-1/app/users/";
 
    /**
     * Retrieve user details.
     * 
     * @param id
     *            the id
     * @return the user
     */
    public User retrieveUserDetails(final Long id) {
        System.out.println("Client: get");
        return restTemplate.getForObject(serviceUrl + "{id}", User.class, id);
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
     * @param user
     *            the user
     * @return the user
     */
    public User updateUserDetails(final User user) {
        System.out.println("Client: put");
        try{
            restTemplate.put(serviceUrl  + "{id}", user, user.getUserId());    
            return user;
        }catch (final RestClientException rce){
           throw rce; 
        } 
        
    }


    /**
     * Delete user details.
     * 
     * @param Id
     *            the id
     * @return true, if successful
     */
    public boolean deleteUserDetails(final Long Id) {        
        System.out.println("Client: delete");
        try{
            restTemplate.delete(serviceUrl + "{Id}", Id);
        }catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

        
    
}
