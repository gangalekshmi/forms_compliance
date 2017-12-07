package com.cognizant.services;

import java.util.List;

import com.cognizant.model.Compliance;
import com.cognizant.model.Employee;

public interface Empinfoservice {

	public List<Object> getDetails();

	public Compliance getAllEmployee();
	
	public Employee getEmpById(String id);
	
	public Object updateEmployeeDetails(String employeeID,String FormName);
	
}
