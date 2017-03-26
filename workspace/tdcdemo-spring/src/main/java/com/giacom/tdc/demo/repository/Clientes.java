package com.giacom.tdc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giacom.tdc.demo.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> { 

}
