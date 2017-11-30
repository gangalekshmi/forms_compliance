/**
 * 
 */
package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.model.Compliance;
import com.cognizant.model.Project;
import com.cognizant.services.PrjInfoserviceImpl;

/**
 * @author 572792
 *
 */

@RestController
public class PrjInfoController {
	
	
	@Autowired
	PrjInfoserviceImpl prjservice;
	
	
	
	Logger LOGGER = LogManager.getLogger(PrjInfoController.class.getName());
	
	@RequestMapping(value = "/prj/{id}/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Object getEmployee(@PathVariable String id,@PathVariable String name) {
				List<Object> prjList = prjservice.getProjectDetails(id,name);
				return prjList;
				

	}
	
	@RequestMapping(value = "/project/{projectId}/{projectName}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Compliance getEmployeeDetails(@PathVariable String projectId,@PathVariable String projectName){
		LOGGER.debug("Getting employee and project details");
		Compliance compliance=prjservice.getEmpDetails(projectId,projectName);
		return compliance;
	}

}
