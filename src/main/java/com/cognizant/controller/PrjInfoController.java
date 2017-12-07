/**
 * 
 */
package com.cognizant.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value = "api/project", method = RequestMethod.POST, headers = "Accept=application/json")
	public Compliance getProjectDetails(@RequestBody Project project){
		LOGGER.debug("Getting employee and project details");
		Compliance compliance=prjservice.getProjectComplianceDetails(project.getPrjId(),project.getPrjName());
		return compliance;
	}

}
