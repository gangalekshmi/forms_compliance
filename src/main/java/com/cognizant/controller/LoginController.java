package com.cognizant.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.Login;
import com.cognizant.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	Logger LOGGER = LogManager.getLogger(EmpinfoController.class.getName());

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Login> authenticate(@RequestBody Login login) {
		
		LOGGER.debug("Authenticate user: " + login.getEmployeeId());
		
		ResponseEntity<Login> response = null;
		
		if (login.getEmployeeId() != 0 && StringUtils.isNotEmpty(login.getPassword())) {
			response = new ResponseEntity<Login>(loginService.authenticate(login), HttpStatus.OK);
		} else {
			response = new ResponseEntity<Login>(new Login(), HttpStatus.BAD_REQUEST);
		}
		
		LOGGER.debug("Authentication response for " + login.getEmployeeId() + ": " + response.getBody());
		
		return response;
	}
	
	@RequestMapping(value="/register/{cognizantId}/{password}", method = RequestMethod.GET)
	public ResponseEntity<String> register(@PathVariable long cognizantId, @PathVariable String password) {
		loginService.createLogin(cognizantId, password);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
