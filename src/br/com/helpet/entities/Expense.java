package br.com.helpet.entities;

import java.sql.Date;

public class Expense extends BaseEntity{

	private Animal animal;
	private Service service;
	private Date date;
	
	public Expense() {
	}

	public Expense(Animal animal, Service service, Date date) {
		this.animal = animal;
		this.service = service;
		this.date = date;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
