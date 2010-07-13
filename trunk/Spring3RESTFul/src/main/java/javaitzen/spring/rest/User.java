package javaitzen.spring.rest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class User.
 */

@SuppressWarnings("restriction")
@XmlRootElement(name = "user" )
@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = -2474375582120847864L;
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID", nullable = false)
    private Long userId;
    @Column(name="FIRST_NAME", nullable = false, length=45)
    private String firstName;
    @Column(name="LAST_NAME", nullable = false, length=45)
    private String lastName;
    @Column(name="USERNAME", nullable = false, length=45)
    private String userName;
    @Transient
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
    public final void setPreferences(final List<String> preferences) {
        this.preferences = preferences;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public final Long getUserId() {
        return userId;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the new id
     */
    public final void setUserId(final Long id) {
        this.userId = id;
    }

    /** (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + userId.hashCode();
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((preferences == null) ? 0 : preferences.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    /** (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (userId != other.userId)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (preferences == null) {
            if (other.preferences != null)
                return false;
        } else if (!preferences.equals(other.preferences))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

}
