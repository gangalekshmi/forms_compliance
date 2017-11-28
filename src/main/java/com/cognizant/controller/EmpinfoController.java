package com.cognizant.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.services.Empinfoserviceimpl;

@RestController
public class EmpinfoController {

	@Autowired
	private Empinfoserviceimpl empService;

	Logger LOGGER = LogManager.getLogger(EmpinfoController.class.getName());

	@RequestMapping(value = "api/employee", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Object> getEmpinfo() {
		LOGGER.debug("Going to get employee details");	
		List<Object> employeeList = empService.getDetails();	
		return employeeList;
	}

	@RequestMapping(value = "api/employee/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Object getEmployee(@PathVariable String id) {
		List<Object> employee = empService.getEmpById(id);
		return employee;
	}

}
