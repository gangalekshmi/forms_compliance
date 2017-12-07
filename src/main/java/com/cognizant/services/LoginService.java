package com.cognizant.services;

import com.cognizant.model.Login;

// TODO: Auto-generated Javadoc
/**
 * The Interface LoginService.
 */
public interface LoginService {
	
	/**
	 * Authenticate.
	 *
	 * @param login the login
	 * @return the string
	 */
	public Login authenticate(Login login);
	
	/**
	 * Creates the login.
	 *
	 * @param login the login
	 * @return true, if successful
	 */
	public void createLogin(long employeeId, String password);
}
