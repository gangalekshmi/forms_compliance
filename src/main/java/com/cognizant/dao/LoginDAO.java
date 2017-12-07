package com.cognizant.dao;

import com.cognizant.model.Login;

/**
 * The Interface LoginDAO.
 * @author 423207
 */
public interface LoginDAO {
	
	/**
	 * Creates the.
	 *
	 * @param login the login
	 */
	public void create(Login login);

	/**
	 * Read by id.
	 *
	 * @param id the id
	 * @return the login
	 */
	public Login readById(Long id);

	/**
	 * Update.
	 *
	 * @param login the login
	 */
	public void update(Login login);

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int deleteById(String id);
}
