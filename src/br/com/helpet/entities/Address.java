package br.com.helpet.entities;

public class Address extends BaseEntity{

	private String street;
	private String city;
	private String state;
	private String complement;
	
	public Address() {
	}
	
	public Address(String street, String city, String state, String complement) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.complement = complement;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
}
