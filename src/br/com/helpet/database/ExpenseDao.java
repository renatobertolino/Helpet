package br.com.helpet.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helpet.entities.Expense;

public class ExpenseDao implements Dao<Expense>{

	@Override
	public void insert(Expense t) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("INSERT INTO expense (animal_id, service_id, date) "
					+ "   VALUES (?,?,?)");
			pstm.setInt(1, t.getAnimal().getId());
			pstm.setInt(2, t.getService().getId());
			pstm.setDate(3, t.getDate());

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Expense t) {
		try (Connection connection = DatabaseHelper.connect()){
			PreparedStatement pstm = connection.prepareStatement("UPDATE expense SET animal_id=?, service_id=?, date=?"
					+ " WHERE id=" + t.getId());

			pstm.setInt(1, t.getAnimal().getId());
			pstm.setInt(2, t.getService().getId());
			pstm.setDate(3, t.getDate());

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}

	@Override
	public boolean delete(int id) {
		try (Connection connection = DatabaseHelper.connect()){

			PreparedStatement pstm = connection.prepareStatement("DELETE FROM expense WHERE id="+id);
			pstm.executeUpdate();
			pstm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


		return true;
	}

	@Override
	public List<Expense> list() {
		List<Expense> expenses = new ArrayList<>();
		
		try (Connection connection = DatabaseHelper.connect()){
			
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM expense");
			ResultSet rs = pstm.executeQuery();
			AnimalDao animalDao = new AnimalDao();
			ServiceDao serviceDao = new ServiceDao();
			
			while(rs.next()){
				
				Expense expense = new Expense();
				expense.setId(rs.getInt("id"));
				expense.setAnimal(animalDao.find(rs.getInt("animal_id")));
				expense.setService(serviceDao.find(rs.getInt("service_id")));
				expense.setDate(rs.getDate("date"));
				
				expenses.add(expense);
			}
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return expenses;
	}

	@Override
	public Expense find(int id) {
		Expense expense = new Expense();
		
		try (Connection connection = DatabaseHelper.connect()){
			
			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM expense WHERE id="+id);
			ResultSet rs = pstm.executeQuery();
			AnimalDao animalDao = new AnimalDao();
			ServiceDao serviceDao = new ServiceDao();
			
			while(rs.next()){
				
				expense.setId(rs.getInt("id"));
				expense.setAnimal(animalDao.find(rs.getInt("animal_id")));
				expense.setService(serviceDao.find(rs.getInt("service_id")));
				expense.setDate(rs.getDate("date"));

			}
			
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return expense;
	}

}
