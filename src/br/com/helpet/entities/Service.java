package br.com.helpet.entities;

public class Service extends BaseEntity{

	private double value;
	private String description;
	
	public Service() {
	}

	public Service(double value, String description) {
		this.value = value;
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
