package com.tekcamp.spring.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name = "User")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 25, nullable = false)
	private String firstName;
	@Column(length = 25, nullable = false)
	private String  lastName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(length = 20, nullable = false)
	private String password;
	
	public UserEntity() {
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String setPassword(String password) {
		return password;
	}

	public String getPassword() {
		return password;
	}
}