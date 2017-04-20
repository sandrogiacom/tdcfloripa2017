package com.giacom.tdc.demo.service;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.giacom.tdc.demo.model.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;
    
    
    public boolean login(User user){
    	
    	Query q = em.createQuery("Select u from User u where u.name = :name and u.password = :password");
    	
    	q.setParameter("name", user.getName());
    	q.setParameter("password", user.getPassword());
    	
    	User u = null;
    	
    	try {
    		u = (User) q.getSingleResult();
    	} catch (NoResultException e) {
			log.info("No user found!");
		} catch (Exception e) {
			log.severe(e.toString());
		}
    	
    	return u != null;
    	
    }
    
}
