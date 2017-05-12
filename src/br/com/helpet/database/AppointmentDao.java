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
	public void insert(Appointment t) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("INSERT INTO appointment (animal, person, date) "
					+ "   VALUES (?,?,?)");

			pstm.setObject(1, t.getAnimal());
			pstm.setObject(2, t.getPerson());
			pstm.setDate(3, t.getDate());

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Appointment t) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("UPDATE appointment SET animal=?, person=?, date=?"
					+ " WHERE id=" + t.getId());

			pstm.setObject(1, t.getAnimal());
			pstm.setObject(2, t.getPerson());
			pstm.setDate(3, t.getDate());

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}	


	}

	@Override
	public boolean delete(long id) {
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

			while(rs.next()){

				Appointment appointment = new Appointment();
				appointment.setId(rs.getInt("id"));
				appointment.setAnimal( (Animal) rs.getObject("animal"));
				appointment.setPerson( (Person) rs.getObject("person"));
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
	public Appointment find(long id) {
		Appointment appointment = new Appointment();

		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM appointment WHERE id="+id);
			ResultSet rs = pstm.executeQuery();

			while(rs.next()){

				appointment.setId(rs.getInt("id"));
				appointment.setAnimal( (Animal) rs.getObject("animal"));
				appointment.setPerson( (Person) rs.getObject("person"));
				appointment.setDate(rs.getDate("date"));

			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return appointment;	}

}
