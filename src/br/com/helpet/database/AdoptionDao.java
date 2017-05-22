package br.com.helpet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.entities.Adoption;

public class AdoptionDao implements Dao<Adoption>{

	@Override
	public void insert(Adoption a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("INSERT INTO adoption (animal_id, person_id, adoption_date) "
					+ "   VALUES (?,?,?)");
			pstm.setInt (1, a.getAnimal().getId());
			pstm.setInt (2, a.getPerson().getId());
			pstm.setDate (3, a.getDate());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void update(Adoption a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("UPDATE adoption SET animal_id=?, person_id=?, adoption_date=?"
					+ " WHERE id=" + a.getId());
			pstm.setInt (1, a.getAnimal().getId());
			pstm.setInt (2, a.getPerson().getId());
			pstm.setDate (3, a.getDate());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}

	@Override
	public boolean delete(int id) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("DELETE FROM adoption WHERE id="+id);
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	@Override
	public List<Adoption> list() {
		List<Adoption> adoptions = new ArrayList<>();
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM adoption");
			ResultSet rs = pstm.executeQuery();
			AnimalDao animalDao = new AnimalDao();
			PersonDao personDao = new PersonDao();
			while(rs.next()){
				Adoption adoption = new Adoption();
				adoption.setAnimal(animalDao.find(rs.getInt("animal_id")));
				adoption.setPerson(personDao.find(rs.getInt("person_id")));
				adoption.setDate(rs.getDate("adoption_date"));
				
				adoptions.add(adoption);
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return adoptions;
	}

	@Override
	public Adoption find(int id) {
		Adoption adoption = new Adoption();
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM adoption WHERE id="+id);
			ResultSet rs = pstm.executeQuery();
			AnimalDao animalDao = new AnimalDao();
			PersonDao personDao = new PersonDao();
			while(rs.next()){
				adoption.setAnimal(animalDao.find(rs.getInt("animal_id")));
				adoption.setPerson(personDao.find(rs.getInt("person_id")));
				adoption.setDate(rs.getDate("adoption_date"));		
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return adoption;
	}

}
