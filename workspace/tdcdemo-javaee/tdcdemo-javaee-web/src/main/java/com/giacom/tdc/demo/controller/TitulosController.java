package com.giacom.tdc.demo.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.giacom.tdc.demo.model.Cliente;
import com.giacom.tdc.demo.model.StatusTitulo;
import com.giacom.tdc.demo.model.Titulo;
import com.giacom.tdc.demo.model.TituloFilter;
import com.giacom.tdc.demo.service.ClienteService;
import com.giacom.tdc.demo.service.TitulosService;

@ManagedBean
@ViewScoped
public class TitulosController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TitulosService titulosService;
	
	@Inject
	private ClienteService clienteService;
	
	private  List<Titulo> titulos;
	
	private Cliente cliente;
	
	Titulo titulo = null;
	
	private TituloFilter filtro = null;
	
	public String findTitulos(){
		this.titulos = titulosService.filtrar(filtro);
		return null;
	}

	public String novo(){
		titulo = new Titulo();
		this.cliente = new Cliente();
		titulo.setCliente(cliente);
		titulo.setStatus(StatusTitulo.PENDENTE);
		return null;
	}

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if(titulo.getCodigo() == null){
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Titulo criado com sucesso!"));
			}else{
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Titulo salvo com sucesso!"));
			}
			titulo.setCliente(cliente);
			titulosService.salvar(titulo);
			findTitulos();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao salvar Titulo" + e.getCause()));

		}
		
		return null;
	}

	public String receber(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			titulosService.receber(titulo);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Titulo recebido com sucesso"));
			findTitulos();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao receber Titulo" + e.getCause()));

		}
		
		return null;
	}

	public List<StatusTitulo> getStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			titulosService.excluir(titulo);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Titulo [" + titulo.getCodigo() + "] excluido com sucesso!"));
			findTitulos();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao excluir Titulo: " + e.getCause()));
		}
		
		return null;
	}
	
	public List<StatusTitulo> getStatusList(){
		return Arrays.asList(StatusTitulo.values());
	}
	
	
	@PostConstruct
	public void initBean(){
		filtro = new TituloFilter();
		titulo = new Titulo();
		cliente = new Cliente();
		titulo.setCliente(cliente);
	}

	public List<Titulo> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<Titulo> titulos) {
		this.titulos = titulos;
	}

	public TituloFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(TituloFilter filtro) {
		this.filtro = filtro;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Cliente> getClientes(){
		return  clienteService.findAll();
	}
	
	
}
