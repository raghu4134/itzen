package javaitzen.spring.rest.controller;

import java.util.Map;
import java.util.TreeMap;

import javaitzen.spring.rest.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class UsersController.
 */
@Controller
public class UsersController {

    public static final String USER_VIEW_KEY = "userView";
    

    /**
     * Show bobs page.
     * 
     * @return the model and view
     */
    @RequestMapping(method=RequestMethod.GET,value="/users/bob")
    public ModelAndView showBobsPage() {
        User user = new User("Bob", "Smith", "bobs", "clean", "simple", "code");
        ModelAndView mav = new ModelAndView(USER_VIEW_KEY);
        mav.addObject("users", user);
        return mav;
    }

    
    /**
     * Gets the user by name.
     * 
     * @param user
     *            the user
     * @return the user by name
     */
    @RequestMapping(value = "/users/{user}")
    public ModelAndView getUserByName(@PathVariable String user) {
        User theUser = someExampleData().get(user);
        ModelAndView mav = new ModelAndView(USER_VIEW_KEY );
        mav.addObject("user", theUser);
        return mav;
    }
    
    
    
    /**
     * Some example data.
     * 
     * @return the map
     */
    private Map<String, User> someExampleData(){
        Map<String, User> users = new TreeMap<String, User>();
        users.put("Bob",new User("Bob", "Smith", "bobs", "clean", "simple", "code"));
        users.put("John",new User("John", "Doe", "johnd", "large", "complicated", "code"));
        users.put("Mary",new User("Mary", "Smith", "marys", "nice", "pretty", "code"));
        return users;
    }
}
