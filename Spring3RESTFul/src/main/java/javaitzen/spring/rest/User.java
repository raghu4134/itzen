package javaitzen.spring.rest;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class User.
 */
@SuppressWarnings("restriction")
@XmlRootElement(name = "user" )
public class User {


    private String firstName;
    private String lastName;
    private String userName;
    private List<String> preferences;

    /**
     * Instantiates a new user.
     */
    public User() {    }
    
    /**
     * Instantiates a new user.
     * 
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     * @param userName
     *            the user name
     * @param preferences
     *            the preferences
     */
    public User(String firstName, String lastName, String userName, String... preferences) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.preferences = Arrays.asList(preferences);
    }

    
    /**
     * Gets the first name.
     * 
     * @return the first name
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName
     *            the new first name
     */
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     * 
     * @return the last name
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * 
     * @param lastName
     *            the new last name
     */
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user name.
     * 
     * @return the user name
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     * 
     * @param userName
     *            the new user name
     */
    public final void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the preferences.
     * 
     * @return the preferences
     */
    public final List<String> getPreferences() {
        return preferences;
    }

    /**
     * Sets the preferences.
     * 
     * @param preferences
     *            the new preferences
     */
    public final void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

}
