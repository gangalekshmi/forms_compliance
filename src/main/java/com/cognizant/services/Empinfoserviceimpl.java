package com.cognizant.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dao.Empinfodao;
import com.cognizant.model.Compliance;
import com.cognizant.model.Employee;

@Service
public class Empinfoserviceimpl implements Empinfoservice {

	Logger LOGGER = LogManager.getLogger(Empinfoserviceimpl.class.getName());

	@Autowired
	Empinfodao Empinfodaoimpl;

	public List<Object> getDetails() {
		LOGGER.debug("Geeting employee details inside Empinfoserviceimpl");
		return Empinfodaoimpl.getDetails();
	}

	public Employee getEmpById(String id) {
		return Empinfodaoimpl.getEmpById(id);

	}

	public Compliance getAllEmployee() {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();

		return Empinfodaoimpl.getAllEmployee();
	}

	@Override
	public Object updateEmployeeDetails(String employeeID, String formName) {
		// TODO Auto-generated method stub
		if ("CIA".equals(formName)) {
			return Empinfodaoimpl.updateEmployeeDetails(employeeID, "ciaFormCompliant");
		} else if ("NDA".equals(formName)) {
			return Empinfodaoimpl.updateEmployeeDetails(employeeID, "ndaFormCompliant");
		} else {
			return "Failed";
		}
	}

}
