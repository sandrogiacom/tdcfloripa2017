package com.giacom.tdc.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="usuario")
public class User implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@TableGenerator(name = "USER_SEQ", table = "SEQUENCES",pkColumnName = "SEQ_NAME",
    valueColumnName = "SEQ_NUMBER", pkColumnValue = "SEQUENCE",  allocationSize=1)
	private Integer id;
	
	private String name;
	
	private String password;
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer Id) {
		this.id = Id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}
