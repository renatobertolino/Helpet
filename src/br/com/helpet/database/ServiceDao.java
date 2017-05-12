package br.com.helpet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.entities.Service;

public class ServiceDao implements Dao<Service>{

	@Override
	public void insert(Service t) {
		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("INSERT INTO service (value, description) "
					+ "   VALUES (?,?)");
			pstm.setDouble(1, t.getValue());
			pstm.setString (2, t.getDescription());
			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void update(Service t) {
		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("UPDATE service SET value=?, description=? WHERE id="+t.getId());
			pstm.setDouble(1, t.getValue());
			pstm.setString(2, t.getDescription());
			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	@Override
	public boolean delete(long id) {
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
	public Service find(long id) {
		
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
