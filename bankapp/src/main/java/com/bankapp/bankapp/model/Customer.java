package com.bankapp.bankapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customer_Id;
	@NotBlank(message="Name can not be blank.")
	private String name;
	@Email(message="Email is invalid.")
	private String email;
	@Length(min=10, max=10, message = "pan number must be 10 characters")
	private String pan_number;

	@NotBlank(message="Password can not be blank.")
	private String password;
	private String dob;
	private String fathername;
	private String mothername;
	private String address;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private List<Account> accounts = new ArrayList<>();
	
	public Customer() {}
	
	public Customer(Long customer_Id, String name, String email, String pan_number, String dob, String fathername,
			String mothername, String address, String password ) {
		super();
		this.customer_Id = customer_Id;
		this.name = name;
		this.email = email;
		this.pan_number = pan_number;
		this.dob = dob;
		this.fathername = fathername;
		this.mothername = mothername;
		this.address = address;
		this.password = password;
	}

	public Long getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(Long customer_Id) {
		this.customer_Id = customer_Id;
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

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
