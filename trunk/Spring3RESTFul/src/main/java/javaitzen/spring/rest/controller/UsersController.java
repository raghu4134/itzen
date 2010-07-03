package javaitzen.spring.rest.controller;

import java.util.Map;
import java.util.TreeMap;

import javaitzen.spring.rest.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class UsersController.
 */
@Controller
public class UsersController {

    private static Map<String, User> users = new TreeMap<String, User>();
    
    /**
     * Gets the user.
     * 
     * @param user
     *            the user
     * @return the user
     */
    @RequestMapping(value="/users/{firstName}",method=RequestMethod.GET) 
    @ResponseBody public User getUser(@PathVariable("firstName")String user) {
        System.out.println("retrieve");
        User theUser = someExampleData().get(user);
        return theUser;
        
    } 
    
    /**
     * Creates the user.
     * 
     * @param user
     *            the user
     * @return the user
     */
    @RequestMapping(value="/users/",method=RequestMethod.POST) 
    @ResponseBody public User createUser(@RequestBody User user) {
        System.out.println("create");
        users.put(user.getFirstName(), user);
        return user;
    } 
    
    /**
     * Update user.
     * 
     * @param name
     *            the name
     * @param user
     *            the user
     * @return the user
     */
    @RequestMapping(value="/users/{firstName}",method=RequestMethod.PUT) 
    @ResponseBody public User updateUser(@PathVariable("firstName")String name, @RequestBody User user) {
        System.out.println("update");
        users.put(user.getFirstName(), user);
        return user;
    } 
    
    
    /**
     * Delete user.
     * 
     * @param name
     *            the name
     */
    @RequestMapping(value="/users/{firstName}",method=RequestMethod.DELETE) 
    @ResponseBody public void deleteUser(@PathVariable("firstName")String name) { 
        System.out.println("delete");
        users.remove(name);
        
    } 
    
    
    /**
     * Some example data.
     * 
     * @return the map
     */
    private Map<String, User> someExampleData() {
        users.put("Bob", new User("Bob", "Smith", "bobs", "clean", "simple", "code"));
        users.put("John", new User("John", "Doe", "johnd", "large", "complicated", "code"));
        users.put("Mary", new User("Mary", "Smith", "marys", "nice", "pretty", "code"));
        return users;
    }
}
