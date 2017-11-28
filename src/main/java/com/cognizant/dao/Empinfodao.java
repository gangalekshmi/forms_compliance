package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Employee;

public interface Empinfodao {

	public List<Object> getDetails();

	public List<Object> getEmpById(String id);
}
