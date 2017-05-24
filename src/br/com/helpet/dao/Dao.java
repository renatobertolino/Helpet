package br.com.helpet.dao;

import java.util.List;

import br.com.helpet.entities.BaseEntity;

public interface Dao<T extends BaseEntity> {

	void insert(T t);
	void update(T t);
	boolean delete(int id);
	List<T> list();
	T find(int id);
}
