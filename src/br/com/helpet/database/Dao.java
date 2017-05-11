package br.com.helpet.database;

import java.util.List;

import br.com.helpet.entities.BaseEntity;

public interface Dao<T extends BaseEntity> {

	void insert(T t);
	void update(T t);
	boolean delete(long id);
	List<T> list();
	T find(long id);
	
}
