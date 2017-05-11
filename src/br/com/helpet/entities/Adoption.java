package br.com.helpet.entities;

import java.sql.Date;

public class Adoption extends BaseEntity{

	private Animal animal;
	private Person person;
	private Date date;
	
	public Adoption() {
	}

	public Adoption(Animal animal, Person person, Date date) {
		this.animal = animal;
		this.person = person;
		this.date = date;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
}
