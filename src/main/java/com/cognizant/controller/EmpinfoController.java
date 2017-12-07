package com.cognizant.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.Compliance;
import com.cognizant.model.Employee;
import com.cognizant.services.Empinfoservice;

@RestController
public class EmpinfoController {

	@Autowired
	private Empinfoservice empService;

	Logger LOGGER = LogManager.getLogger(EmpinfoController.class.getName());

	public static final String JSON_FILE="employee.txt";
	

	
	@RequestMapping(value = "api/getAllemployee", method = RequestMethod.GET, headers = "Accept=application/json")
	public Compliance getAllEmployeeDetails(){
		LOGGER.debug("Getting AllEmployeeDetails details");
		Compliance compliance=empService.getAllEmployee();
		return compliance;
	}

	@RequestMapping(value = "api/emp/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Object getEmployee(@PathVariable String id) {
		Employee employee = empService.getEmpById(id);
		return employee;
	}
	
	
	


}
