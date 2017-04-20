package com.giacom.tdc.demo.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.giacom.tdc.demo.data.TitulosRepository;
import com.giacom.tdc.demo.model.Titulo;
import com.giacom.tdc.demo.model.TituloFilter;

/**
 * Session Bean implementation class TitulosService
 */
@Stateless
@LocalBean
public class TitulosService {
	
    @Inject
    private Logger log;

    @Inject
    private TitulosRepository repository;
   
	public List<Titulo> filtrar(TituloFilter filtro) {
		return repository.filtrar(filtro);
	}

	public String receber(Titulo titulo) {
		return repository.receber(titulo);
	}
	
    public void excluir(Titulo titulo) {
    	repository.excluir(titulo);
    }

	
    public Titulo salvar(Titulo titulo) {
    	return repository.salvar(titulo);
    }


}
