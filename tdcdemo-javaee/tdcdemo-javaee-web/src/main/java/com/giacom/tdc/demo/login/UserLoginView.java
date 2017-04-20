package com.giacom.tdc.demo.login;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.giacom.tdc.demo.model.User;
import com.giacom.tdc.demo.service.UserService;
import com.giacom.tdc.demo.util.Util;
 
@ManagedBean
@SessionScoped
public class UserLoginView implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	
	@Inject
	private FacesContext context;
     
    private String username;
     
    private String password;
 
    public String getUsername() {
    	username = context.getExternalContext().getUserPrincipal().getName();
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(username != null && password != null) {
        	User user = new User();
        	user.setName(username);
        	user.setPassword(password);
            loggedIn = userService.login(user);
        }
        
        if (loggedIn){
        	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        	FacesContext.getCurrentInstance().addMessage(null, message);
        	context.addCallbackParam("loggedIn", loggedIn);
        	
        	 HttpSession session = Util.getSession();
             session.setAttribute("username", username);
        	
        	return "home";
        }else{
        	message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        	FacesContext.getCurrentInstance().addMessage(null, message);
        	return "login";
        }
        
    }
    
    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "home";
     }
    
}