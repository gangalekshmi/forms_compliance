package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Compliance;
import com.cognizant.model.Employee;

public interface Empinfodao {

	public List<Object> getDetails();

	public Compliance getAllEmployee();
	
	public Employee getEmpById(String id);
	
	public Object updateEmployeeDetails(String employeeId,String formName);
	
}
