package br.com.helpet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.entities.Address;
import br.com.helpet.entities.Person;

public class PersonDao implements Dao<Person>{

	@Override
	public void insert(Person p) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("INSERT INTO person (name, address_id, email, cpf, phone) "
					+ "   VALUES (?,?,?,?,?)");
			pstm.setString (1, p.getName());
			pstm.setLong   (2, p.getAddress().getId());
			pstm.setString (3, p.getEmail());
			pstm.setString (4, p.getCpf());
			pstm.setString (5, p.getPhone());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(Person p) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("UPDATE person SET name=?, address_id=?, email=?, cpf=?, phone=?"
					+ "WHERE id=" + p.getId());
			pstm.setString (1, p.getName());
			pstm.setLong   (2, p.getAddress().getId());
			pstm.setString (3, p.getEmail());
			pstm.setString (4, p.getCpf());
			pstm.setString (5, p.getPhone());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public boolean delete(long id) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("DELETE FROM person WHERE id="+id);
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	@Override
	public List<Person> list() {
		List<Person> people = new ArrayList<>();
		try (Connection connection = DatabaseHelper.connect()){
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM person");
			while(rs.next()){
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAddress(retrieveAddress(stm, rs.getLong("address_id")));
				person.setEmail(rs.getString("email"));
				person.setCpf(rs.getString("cpf"));
				person.setPhone(rs.getString("phone"));
				
				people.add(person);
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return people;
	}

	private Address retrieveAddress(Statement stm, long id) throws SQLException {
		Address address = new Address();
		ResultSet rs = stm.executeQuery("SELECT * FROM address WHERE id="+id);
		while(rs.next()){
			address.setStreet(rs.getString("street"));
			address.setCity(rs.getString("city"));
			address.setState(rs.getString("state"));
			address.setComplement(rs.getString("complement"));
		}
		return address;
	}

	@Override
	public Person find(long id) {
		Person person =new Person();
		try (Connection connection = DatabaseHelper.connect()){
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM person WHERE id="+id);
			while(rs.next()){
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setAddress(retrieveAddress(stm, rs.getLong("address_id")));
				person.setEmail(rs.getString("email"));
				person.setCpf(rs.getString("cpf"));
				person.setPhone(rs.getString("phone"));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return person;
	}

}
