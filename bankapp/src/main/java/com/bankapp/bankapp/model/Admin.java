package com.bankapp.bankapp.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long admin_id;
	@NotBlank(message="Name can not be blank.")
	private String name;
	@Email(message="Email is invalid.")
	private String email;
	@Length(min=10, max=10, message = "pan number must be 10 characters")
	private String pan_number;

	@NotBlank(message="Password can not be blank.")
	private String password;
	private String dob;
	
	public Admin() {}
	public Admin(Long admin_id, String name, String email, String pan_number, String password, String dob) {
		this.admin_id=admin_id;
		this.name=name;
		this.email=email;
		this.pan_number=pan_number;
		this.password=password;
		this.dob=dob;
	}
	
	public Long getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPan_number() {
		return pan_number;
	}
	public void setPan_number(String pan_number) {
		this.pan_number = pan_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
}

