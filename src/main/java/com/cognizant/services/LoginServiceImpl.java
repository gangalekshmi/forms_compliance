package com.cognizant.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dao.LoginDAO;
import com.cognizant.model.Login;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDAO loginDAO;

	@Override
	public Login authenticate(Login login) {
		Login result = loginDAO.readById(login.getEmployeeId());
		
		if (result != null) {
			if(StringUtils.equals(result.getPassword(), login.getPassword())) {
				result.setStatus("success");
			} else {
				result.setStatus("wrongpassword");
			}
		} else {
			result = new Login();
			result.setStatus("notfound");
		}

		return result;
	}

	@Override
	public void createLogin(long employeeId, String password) {
		Login login = new Login(employeeId, password);
		loginDAO.create(login);
	}
}
