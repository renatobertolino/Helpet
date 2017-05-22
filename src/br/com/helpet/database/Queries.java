package br.com.helpet.database;

public class Queries {

	public static final String ADOPTION_HISTORY_QUERY = "SELECT ad.city , COUNT(a.id)"+
												        " FROM adoption a"+
												        " INNER JOIN person p ON p.id = a.person_id"+
                                                        " INNER JOIN address ad ON ad.id = p.address_id"+	
                                                        " GROUP BY ad.city"+	
                                                        " ORDER BY ad.city ASC, COUNT(a.id) DESC";
	
	
	public static final String SPECIE_EXPENSES_QUERY = "SELECT a.specie, COUNT(s.id), SUM(s.value)"+
												       " FROM expense e"+
												       " INNER JOIN animal a ON a.id = e.animal_id"+
												       " INNER JOIN service s ON s.id = e.service_id"+
												       " GROUP BY a.specie"+
												       " ORDER BY SUM(s.value) DESC"+
												       " LIMIT 10;";
	
	public static final String SERVICE_HISTORY_QUERY = "SELECT s.description, s.value, e.expense_date"+
												 	   " FROM expense e"+
												       " INNER JOIN service s ON s.id = e.service_id"+
												       " WHERE s.description = ?"+
												       " GROUP BY s.value, e.expense_date"+
												       " ORDER BY e.expense_date DESC";
	
	public static final String EXPENSES_SUMMARY_QUERY = "SELECT s.description, COUNT(s.id), SUM(s.value)"+
														" FROM expense e"+
														" INNER JOIN service s ON s.id = e.service_id"+
														" WHERE e.expense_date >= ? AND e.expense_date <= ?"+
														" GROUP BY s.description"+
														" ORDER BY SUM(s.value) DESC";
	
	public static final String ADOPTIONS_SUMMARY_QUERY = "SELECT a.specie, add.city, COUNT(ad.id)"+
														 " FROM adoption ad"+
														 " INNER JOIN animal a ON a.id = ad.animal_id"+
														 " INNER JOIN person p ON p.id = ad.person_id"+
														 " INNER JOIN address add ON add.id = p.address_id"+
														 " WHERE add.city = ?"+
														 " GROUP BY a.specie"+
														 " ORDER BY COUNT(ad.id) DESC, a.specie ASC, add.city ASC";
	
	
	
	
	
	
}
