package br.com.helpet.entities;

public class Person extends BaseEntity{

	private String name;
	private Address address;
	private String email;
	private String cpf;
	private String phone;
	
	public Person() {
	}

	public Person(String name, Address address, String email, String cpf, String phone) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.cpf = cpf;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
