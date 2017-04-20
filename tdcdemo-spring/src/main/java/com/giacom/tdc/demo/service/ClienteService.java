package com.giacom.tdc.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giacom.tdc.demo.model.Cliente;
import com.giacom.tdc.demo.repository.Clientes;

@Service
public class ClienteService {

	@Autowired
	private Clientes clientes;
	
	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}

	public void excluir(Long codigo) {
		clientes.delete(codigo);
	}
	
	public List<Cliente> listarTodos() {
		return clientes.findAll();
	}
	
}
