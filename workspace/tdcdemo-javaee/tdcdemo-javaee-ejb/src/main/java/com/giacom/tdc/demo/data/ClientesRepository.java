package com.giacom.tdc.demo.data;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.giacom.tdc.demo.model.Cliente;

public class ClientesRepository {
   
	@Inject
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() {
		Query q = em.createQuery("Select c from Cliente c");
		List<Cliente> cli = q.getResultList();
		return cli; 
	}

}
