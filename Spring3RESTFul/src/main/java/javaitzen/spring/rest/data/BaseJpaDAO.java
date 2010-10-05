package javaitzen.spring.rest.data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * The Class BaseJpaDAO.
 * 
 * @param <key>
 *            the generic type
 * @param <entity>
 *            the generic type
 */
public abstract class BaseJpaDAO<key extends Serializable, entity extends Serializable> extends JpaDaoSupport {
    protected Class<entity> entityClass; 

    
    /**
     * Instantiates a new base jpa dao.
     */
    @SuppressWarnings("unchecked") 
    public BaseJpaDAO() { 
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass(); 
        this.entityClass = (Class<entity>) genericSuperclass.getActualTypeArguments()[1]; 
    } 
    
    /**
     * Find all.
     * 
     * @return the list
     */
    @SuppressWarnings("unchecked") 
    public List<entity> findAll() { 
     Object res = getJpaTemplate().execute(new JpaCallback() { 

      public Object doInJpa(EntityManager em) throws PersistenceException { 
       Query q = em.createQuery("SELECT h FROM " + 
         entityClass.getName() + " h"); 
       return q.getResultList(); 
      } 
       
     }); 
      
     return (List<entity>) res; 
    } 

    /**
     * Find by id.
     * 
     * @param id
     *            the id
     * @return the entity
     */
    public entity findById(key id) {
       return this.getJpaTemplate().find(entityClass, id); 
    }

    /**
     * Flush.
     * 
     * @param entity
     *            the entity
     * @return the entity
     */
    public entity flush(entity entity) {
        getJpaTemplate().flush();
        return entity;

    }

    /**
     * Merge.
     * 
     * @param entity
     *            the entity
     * @return the entity
     */
    public entity merge(entity entity) {
        return getJpaTemplate().merge(entity);
    }

    /**
     * Persist.
     * 
     * @param entity
     *            the entity
     */
    public void persist(entity entity) {
        getJpaTemplate().persist(entity);
    }

    /**
     * Refresh.
     * 
     * @param entity
     *            the entity
     */
    public void refresh(entity entity) {
        
    }

    /**
     * Delete.
     * 
     * @param entity
     *            the entity
     */
    public void remove(entity entity) {
        getJpaTemplate().remove(entity);
    }


}
