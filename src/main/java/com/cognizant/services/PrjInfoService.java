/**
 * 
 */
package com.cognizant.services;

import java.util.List;

import com.cognizant.model.Project;

/**
 * @author 572792
 *
 */
public interface PrjInfoService {

	
	public List<Object> getProjectDetails(String id,String name);
}
