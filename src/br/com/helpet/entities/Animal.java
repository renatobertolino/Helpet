package br.com.helpet.entities;

public class Animal extends BaseEntity{

	private AnimalEnum specie;
	private String breed;
	private String gender;
	private double weight;
	private int age;
	private String description;
	private Person person;
	
	public Animal() {
		
	}

	public Animal(AnimalEnum specie, String breed, String gender, double weight, int age, String description, Person person) {
		this.specie = specie;
		this.breed = breed;
		this.gender = gender;
		this.weight = weight;
		this.age = age;
		this.description = description;
		this.person = person;
	}

	public AnimalEnum getSpecie() {
		return specie;
	}

	public void setSpecie(AnimalEnum specie) {
		this.specie = specie;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public enum AnimalEnum {
		CACHORRO, GATO;	
		
		public static AnimalEnum setName(String name){
			if(name.equals(CACHORRO.name())){ 
				return CACHORRO;
			}
			else return GATO;
		}
	}
}
