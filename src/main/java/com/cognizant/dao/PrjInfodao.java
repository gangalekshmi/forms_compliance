/**
 * 
 */
package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Project;

/**
 * @author 572792
 *
 */
public interface PrjInfodao {

	public List<Object> getProjectDetails(String id,String name);
	
}