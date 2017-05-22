package br.com.helpet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.database.DatabaseHelper;
import br.com.helpet.entities.Service;

public class ServiceDao implements Dao<Service>{

	@Override
	public void insert(Service s) {
		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("INSERT INTO service (value, description) "
					+ "   VALUES (?,?)");
			pstm.setDouble(1, s.getValue());
			pstm.setString (2, s.getDescription());
			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void update(Service s) {
		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("UPDATE service SET value=?, description=? WHERE id="+s.getId());
			pstm.setDouble(1, s.getValue());
			pstm.setString(2, s.getDescription());
			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public boolean delete(int id) {
		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("DELETE FROM service WHERE id="+id);
			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public List<Service> list() {

		List<Service> services = new ArrayList<>();

		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM service");
			ResultSet rs = pstm.executeQuery();

			while(rs.next()){

				Service service = new Service(rs.getDouble("value"), rs.getString("description"));
				service.setId(rs.getInt("id"));
				services.add(service);

			}
			
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return services;
	}

	@Override
	public Service find(int id) {
		
		Service service = new Service();
		
		try (Connection connection = DatabaseHelper.connect()){
			
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM service WHERE id="+id);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				service.setId(rs.getInt("id"));
				service.setValue(rs.getDouble("value"));
				service.setDescription(rs.getString("description"));
			}
			
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return service;
	}

}
