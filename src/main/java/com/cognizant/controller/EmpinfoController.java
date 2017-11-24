package com.cognizant.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.Employee;
import com.cognizant.services.Empinfoserviceimpl;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

import net.minidev.json.writer.JsonReader;

@RestController
public class EmpinfoController {

	@Autowired
	private Empinfoserviceimpl empService;

	Logger LOGGER = LogManager.getLogger(EmpinfoController.class.getName());

	public static final String JSON_FILE="employee.txt";
	

	@RequestMapping(value = "/emp", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Object> getEmpinfo() {
		LOGGER.debug("Geeting employee details");
		
		List<Object> empList = empService.getDetails();
				
		return empList;
	}

	@RequestMapping(value = "/emp/{id}/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Object getEmployee(@PathVariable String id,@PathVariable String name) {
				List<Object> employee = empService.getEmpById(id,name);

		return employee;
	}
	
	
	
	
//	@RequestMapping(value="getDetails",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Object>> Empinfo(@RequestBody Employee employee){
//		ResponseEntity<List<Object>> objResEntity;
//		List<Object> empList = empService.getDetails();
//		
//		if(empList!=null && !empList.isEmpty()){
//			objResEntity=new ResponseEntity<List<Object>>(empList, HttpStatus.OK);
//		}
//		else{
//			objResEntity=new ResponseEntity<List<Object>>(HttpStatus.NO_CONTENT);
//		}
//		
//		return objResEntity;
//		
//	}
	


}
