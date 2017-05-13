package br.com.helpet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.entities.Animal;
import br.com.helpet.entities.Appointment;
import br.com.helpet.entities.Person;

public class AppointmentDao implements Dao<Appointment>{

	@Override
	public void insert(Appointment a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("INSERT INTO appointment (animal_id, person_id, date) "
					+ "   VALUES (?,?,?)");

			pstm.setInt(1, a.getAnimal().getId());
			pstm.setInt(2, a.getPerson().getId());
			pstm.setDate(3, a.getDate());

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Appointment a) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("UPDATE appointment SET animal_id=?, person_id=?, date=?"
					+ " WHERE id=" + a.getId());

			pstm.setInt(1, a.getAnimal().getId());
			pstm.setInt(2, a.getPerson().getId());
			pstm.setDate(3, a.getDate());

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}	


	}

	@Override
	public boolean delete(int id) {
		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("DELETE FROM appointment WHERE id="+id);
			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


		return true;
	}

	@Override
	public List<Appointment> list() {
		List<Appointment> appointments= new ArrayList<>();

		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM appointment");
			ResultSet rs = pstm.executeQuery();
			AnimalDao animalDao = new AnimalDao();
			PersonDao personDao = new PersonDao();

			while(rs.next()){

				Appointment appointment = new Appointment();
				appointment.setId(rs.getInt("id"));
				appointment.setAnimal(animalDao.find(rs.getInt("animal_id")));
				appointment.setPerson(personDao.find(rs.getInt("person_id")));
				appointment.setDate(rs.getDate("date"));

				appointments.add(appointment);
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return appointments;

	}

	@Override
	public Appointment find(int id) {
		Appointment appointment = new Appointment();

		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM appointment WHERE id="+id);
			ResultSet rs = pstm.executeQuery();
			AnimalDao animalDao = new AnimalDao();
			PersonDao personDao = new PersonDao();

			while(rs.next()){

				appointment.setId(rs.getInt("id"));
				appointment.setAnimal(animalDao.find(rs.getInt("animal_id")));
				appointment.setPerson(personDao.find(rs.getInt("person_id")));
				appointment.setDate(rs.getDate("date"));

			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return appointment;	
	}

}
