package javaitzen.spring.rest.controller;

import javaitzen.spring.rest.User;
import javaitzen.spring.rest.Users;
import javaitzen.spring.rest.data.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class UsersController.
 */
@Controller
@RequestMapping("/users")  
public class UsersController {
    
    @Autowired
    private UserDAO userDAO;
    
    /**
     * Gets the user.
     * 
     * @param user
     *            the user
     * @return the user
     */
    @RequestMapping(method=RequestMethod.GET)
    @Transactional(readOnly = true)
    @ResponseBody public Users getAllUsers() {
        System.out.println("retrieve all");
        Users users = new Users(userDAO.findAll());
        return users;
    } 
    
    /**
     * Gets the user.
     * 
     * @param user
     *            the user
     * @return the user
     */
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    @Transactional(readOnly = true)
    @ResponseBody public User getUser(@PathVariable("id")Long id) {
        System.out.println("retrieve");
        User theUser = userDAO.findById(id);
        return theUser;        
    } 
    
    /**
     * Creates the user.
     * 
     * @param user
     *            the user
     * @return the user
     */
    @RequestMapping(method=RequestMethod.POST) 
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @ResponseBody public User createUser(@RequestBody User user) {
        System.out.println("create");
        userDAO.persist(user);
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
    @RequestMapping(method=RequestMethod.PUT) 
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @ResponseBody public User updateUser(@RequestBody User user) {
        System.out.println("update");
        user = userDAO.merge(user);
        return user;
    } 
    
    
    /**
     * Delete user.
     * 
     * @param name
     *            the name
     */
    @RequestMapping(value="/{Id}",method=RequestMethod.DELETE) 
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @ResponseBody public void deleteUser(@PathVariable("Id")Long id) { 
        System.out.println("delete");
        userDAO.remove(userDAO.findById(id));
        
    } 
    
    
    /**
     * Gets the user dao.
     * 
     * @return the user dao
     */
    public final UserDAO getUserDAO() {
        return userDAO;
    }

    
    /**
     * Sets the user dao.
     * 
     * @param userDAO
     *            the new user dao
     */
    public final void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
