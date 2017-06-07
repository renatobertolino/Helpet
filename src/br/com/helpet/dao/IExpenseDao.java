package br.com.helpet.dao;

import java.sql.Date;
import java.util.List;

import br.com.helpet.entities.Expense;

public interface IExpenseDao extends IDao<Expense>{

	List<String> getSpeciesExpenses();
	List<String> getServicesHistory(String description);
	List<String> getExpensesSummary(Date start, Date end);
}
