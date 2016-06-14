package com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS", catalog="company")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME", nullable=false, unique=false)
	private String name;
	
	@Column(name="PASSWORD", nullable=false, unique=false)
	private String password;
	
	@Column(name="CPF", nullable=false, unique=true)
	private String cpf;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USERS_ID", unique=true)
	private User friendId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public User getFriendId() {
		return friendId;
	}

	public void setFriendId(User friendId) {
		this.friendId = friendId;
	}	
}
