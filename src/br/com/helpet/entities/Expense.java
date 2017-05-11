package br.com.helpet.entities;

import java.sql.Date;

public class Expense extends BaseEntity{

	private Person person;
	private Service service;
	private Date date;
	
	public Expense() {
	}

	public Expense(Person person, Service service, Date date) {
		this.person = person;
		this.service = service;
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
