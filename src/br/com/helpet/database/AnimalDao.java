package br.com.helpet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.entities.Animal;
import br.com.helpet.entities.Animal.AnimalEnum;

public class AnimalDao implements Dao<Animal>{
	
	@Override
	public void insert(Animal a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("INSERT INTO animal (specie, breed, gender, weight, age, description, flag_adoption) "
					+ "   VALUES (?,?,?,?,?,?,?)");
			pstm.setString (1, a.getSpecie().name());
			pstm.setString (2, a.getBreed());
			pstm.setString (3, a.getGender());
			pstm.setDouble (4, a.getWeight());
			pstm.setInt    (5, a.getAge());
			pstm.setString (6, a.getDescription());
			pstm.setBoolean(7, a.getFlagAdoption());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void update(Animal a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("UPDATE animal SET specie=?, breed=?, gender=?, weight=?, age=?, "
					+ "description=?, flag_adoption=? WHERE id=" + a.getId());
			pstm.setString (1, a.getSpecie().name());
			pstm.setString (2, a.getBreed());
			pstm.setString (3, a.getGender());
			pstm.setDouble (4, a.getWeight());
			pstm.setInt    (5, a.getAge());
			pstm.setString (6, a.getDescription());
			pstm.setBoolean(7, a.getFlagAdoption());
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public boolean delete(int id) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("DELETE FROM animal WHERE id="+id);
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	@Override
	public List<Animal> list() {
		List<Animal> animals = new ArrayList<>();
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM animal");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Animal animal = new Animal();
				animal.setId(rs.getInt("id"));
				animal.setSpecie(AnimalEnum.setName(rs.getString("specie")));
				animal.setBreed(rs.getString("breed"));
				animal.setGender(rs.getString("gender"));
				animal.setWeight(rs.getDouble("weight"));
				animal.setAge(rs.getInt("age"));
				animal.setDescription(rs.getString("description"));
				animal.setFlagAdoption(rs.getBoolean("flag_adoption"));
				
				animals.add(animal);
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return animals;
	}

	@Override
	public Animal find(int id) {
		Animal animal =new Animal();
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM animal WHERE id="+id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				animal.setId(rs.getInt("id"));
				animal.setSpecie(AnimalEnum.setName(rs.getString("specie")));
				animal.setBreed(rs.getString("breed"));
				animal.setGender(rs.getString("gender"));
				animal.setWeight(rs.getDouble("weight"));
				animal.setAge(rs.getInt("age"));
				animal.setDescription(rs.getString("description"));
				animal.setFlagAdoption(rs.getBoolean("flag_adoption"));
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return animal;
	}

}
