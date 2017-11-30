package com.cognizant.services;

import java.util.List;

import com.cognizant.model.Compliance;
import com.cognizant.model.Employee;

public interface Empinfoservice {

	public List<Object> getDetails();

	public Compliance getAllEmployee();
	
	public List<Object> getEmpById(String id,String name);
	
}
