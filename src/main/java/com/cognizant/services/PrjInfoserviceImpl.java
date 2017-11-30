/**
 * 
 */
package com.cognizant.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dao.PrjInfodao;
import com.cognizant.model.Compliance;



/**
 * @author 572792
 *
 */
@Service
public class PrjInfoserviceImpl {
	
	@Autowired
	PrjInfodao PrjInfodaoimpl;
	
	public List<Object> getProjectDetails(String id,String name) {
		return PrjInfodaoimpl.getProjectDetails(id, name);

	}

	public Compliance getEmpDetails(String projectId,String projectName) {
		// TODO Auto-generated method stub
		
		return PrjInfodaoimpl.getEmpDetails(projectId, projectName);
	}
	
}
