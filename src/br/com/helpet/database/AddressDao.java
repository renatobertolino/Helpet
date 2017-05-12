package br.com.helpet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.entities.Address;

public class AddressDao implements Dao<Address>{

	@Override
	public void insert(Address a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("INSERT INTO address (street, city, state, complement) "
					+ "   VALUES (?,?,?,?)");
			pstm.setString (1, a.getStreet());
			pstm.setString (2, a.getCity());
			pstm.setString (3, a.getState());
			pstm.setString (3, a.getComplement());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void update(Address a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("UPDATE address SET street=?, city=?, state=?, complement=?"
					+ " WHERE id=" + a.getId());
			pstm.setString (1, a.getStreet());
			pstm.setString (2, a.getCity());
			pstm.setString (3, a.getState());
			pstm.setString (4, a.getComplement());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public boolean delete(long id) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("DELETE FROM address WHERE id="+id);
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	@Override
	public List<Address> list() {
		List<Address> addresses = new ArrayList<>();
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM address");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setStreet(rs.getString("street"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setComplement(rs.getString("complement"));
				
				addresses.add(address);
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return addresses;
	}

	@Override
	public Address find(long id) {
		Address address = new Address();
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM address WHERE id="+id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				address.setId(rs.getInt("id"));
				address.setStreet(rs.getString("street"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setComplement(rs.getString("complement"));
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return address;
	}

}
