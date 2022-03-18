package com.qa.QACrossing.service;

import java.util.List;

public interface ServiceMethods<T> {

		T create(T villager);
		
		List<T> readAll();
		
		T readById(long id);
		
		T update(long id, T villager);
		
		boolean delete(long id);

}
