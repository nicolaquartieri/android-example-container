package com.bootcamp.globant.dao;

import java.util.List;
import java.util.Map;

public interface DAO {
	
	public void open();
	public void close();
	public long insert(Map<String, ?> valores);
	public void delete(int id);	
	public List<?> getAll();
}
