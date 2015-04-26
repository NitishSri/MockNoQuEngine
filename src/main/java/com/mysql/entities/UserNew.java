package com.mysql.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserNew {

	@Id
	private Long id;
 
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getAcType() {
		return acType;
	}


	public void setAcType(String acType) {
		this.acType = acType;
	}


	private String username;
 
	
	private String password;
	
	
	private String address;
	
	
	private String fullname;
	
	
	private String acType;
}
