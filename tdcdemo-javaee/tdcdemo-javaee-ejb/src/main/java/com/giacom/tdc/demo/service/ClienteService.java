package com.giacom.tdc.demo.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.giacom.tdc.demo.data.ClientesRepository;
import com.giacom.tdc.demo.model.Cliente;

/**
 * Session Bean implementation class ClienteService
 */
@Stateless
@LocalBean
public class ClienteService {
	
    @Inject
    private Logger log;

    @Inject
    private ClientesRepository repository;
   
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	
}
