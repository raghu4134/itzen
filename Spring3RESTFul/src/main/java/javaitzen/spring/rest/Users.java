package javaitzen.spring.rest;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Users.
 */
@XmlRootElement
public class Users implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1588050943984658062L;
    private List<User> users;

    /**
     * Instantiates a new users.
     */
    public Users() {

    }
    
    /**
     * People.
     * 
     * @param users
     *            the users
     */
    public Users(final List<User> users) {
        this.users = users;
    }

    /**
     * Gets the person.
     * 
     * @return the person
     */
    public List<User> getPerson() {
        return users;
    }

    /**
     * Sets the users.
     * 
     * @param users
     *            the new users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
