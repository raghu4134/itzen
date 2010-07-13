package javaitzen.spring.rest.data;

import javaitzen.spring.rest.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The Class UserDAO.
 */
@Repository("userDAO")
public class UserDAO extends BaseJpaDAO<Long, User> {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
     super.setEntityManagerFactory(entityManagerFactory);
    }

}
