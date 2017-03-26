package com.giacom.tdc.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giacom.tdc.demo.model.Cliente;
import com.giacom.tdc.demo.model.Titulo;
import com.giacom.tdc.demo.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	private static final String CADASTRO_VIEW = "CadastroCliente";
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cliente());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			clienteService.salvar(cliente);
			attributes.addFlashAttribute("mensagem", "cliente salvo com sucesso!");
			return "redirect:/cliente/novo";
		} catch (Exception e) {
			errors.rejectValue("", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	

	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		clienteService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
		return "redirect:/clientes";
	}
	
	
	@ModelAttribute("todosClientes")
	public List<Cliente> todosClientes() {
		return clienteService.listarTodos();
	}
	
}
