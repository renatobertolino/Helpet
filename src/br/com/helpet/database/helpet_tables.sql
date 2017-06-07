-- Database: helpet

-- DROP DATABASE helpet;

CREATE TABLE address(
	id SERIAL,
	street varchar(255) NOT NULL,
	city varchar(100) NOT NULL,
	state varchar(100) NOT NULL,
	complement varchar(255) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE person(
	id SERIAL,
	name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	cpf varchar(16) NOT NULL,
	phone varchar(16) NOT NULL,
	address_id int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(address_id) REFERENCES address(id)
);

CREATE TABLE animal(
	id SERIAL, 
	specie varchar(20) NOT NULL,
	breed varchar(100) NOT NULL,
	gender varchar(1) NOT NULL,
	weight bigint NOT NULL,
	age int,
	description varchar(255) NOT NULL,
	person_id int,
	PRIMARY KEY (id),
	FOREIGN KEY(person_id) REFERENCES person(id)
);

CREATE TABLE adoption(
	id SERIAL,
	animal_id int NOT NULL,
	person_id int NOT NULL,
	adoption_date date NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(animal_id) REFERENCES animal(id),
	FOREIGN KEY(person_id) REFERENCES person(id)
);

CREATE TABLE service(
	id SERIAl,
	value double NOT NULL,
	description varchar(255) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE expense(
	id SERIAL,
	animal_id int NOT NULL,
	service_id int NOT NULL,
	expense_date date NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(animal_id) REFERENCES animal(id),
	FOREIGN KEY(service_id) REFERENCES service(id)
);