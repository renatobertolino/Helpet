package br.com.helpet.dao;

import java.util.List;

import br.com.helpet.entities.Adoption;

public interface IAdoptionDao extends IDao<Adoption>{

	List<String> getAdoptionHistory();
	List<String> getAdoptionSummary(String city);
}
