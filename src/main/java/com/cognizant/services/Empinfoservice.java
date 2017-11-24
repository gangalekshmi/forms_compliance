package com.cognizant.services;

import java.util.List;

import com.cognizant.model.Employee;

public interface Empinfoservice {

	public List<Object> getDetails();

	public List<Object> getEmpById(String id);
}
