package com.cognizant.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.services.SecurityService;
import com.cognizant.services.UserService;

@Controller
public class UserController {
	
	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    
    Logger LOGGER = LogManager.getLogger(EmpinfoController.class.getName());

    @RequestMapping(value = "api/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
        	LOGGER.error("Invalid credentials");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
