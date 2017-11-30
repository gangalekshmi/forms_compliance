package com.cognizant.dao;

import java.util.ArrayList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.configurations.SpringMongoDBConfiguration;
import com.cognizant.model.Compliance;
import com.cognizant.model.Project;
import com.cognizant.services.Empinfoserviceimpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


@Repository
public class PrjInfodaoimpl implements PrjInfodao{

	
	Logger LOGGER = LogManager.getLogger(PrjInfodaoimpl.class.getName());
	
	
	@Autowired
	SpringMongoDBConfiguration mongoDB;
	
	
	public List<Object> getProjectDetails(String prjId,String prjName){
		
		List<Object> list1 = new ArrayList<Object>();
		
		DBCollection collection = null;
		try {
			collection = mongoDB.getMongoTemplate().getCollection("complianceDetails");
		System.out.println("Enetrs in to database");
		
		BasicDBObject whereQuery = new BasicDBObject();
		if(!"null".equals(prjId))
		whereQuery.put("prjid", prjId);
		if(!"null".equals(prjName))
		whereQuery.put("prjname", prjName);
		
		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			list1.add(cursor.next());
			//System.out.println(cursor.next());
		}
		

		}	catch (Exception e) {
			
			e.printStackTrace();
		}
		return list1;
	}
	
	public Compliance getEmpDetails(String projectId, String projectName) {

		int total_No_of_Employees = 0;
		String formA;
		String formB;
		int compliance_Available = 0;
		int non_Compliance;
		String totalEmployees = null;
		String compliance = null;
		try {
			DBCollection collection = mongoDB.getMongoTemplate().getCollection("Form_Compliance");
			DBObject statusQuery = new BasicDBObject("prjid", "prjName");
			if (!"null".equals(projectId))
				statusQuery.put("prjid", projectId);
			if (!"null".equals(projectName))
				statusQuery.put("prjName", projectName);
			DBObject fields = new BasicDBObject("$elemMatch", statusQuery);
			DBObject query = new BasicDBObject("prj_assignments", fields);
			DBCursor cursor = collection.find(query);
			if(null!=cursor){
			total_No_of_Employees = cursor.count();
			}else{
			throw new Exception("No Project available with id/name "+projectId+ " "+projectName);
			}
			LOGGER.debug("Total_No_of_Employees" + total_No_of_Employees);
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				formA = (String) object.get("formA");
				formB = (String) object.get("formB");

				if (formA.equals("Y") && formB.equals("Y")) {
					compliance_Available++;
				}
			}
			totalEmployees = String.valueOf(total_No_of_Employees);
			compliance = String.valueOf(compliance_Available);
			return new Compliance(totalEmployees, compliance,
					String.valueOf(total_No_of_Employees - compliance_Available),"success");
		} catch (Exception e) {
			LOGGER.error("Error in side form_Compliance Dao" + e.getStackTrace());
			return new Compliance("0", "0", "0","failed");
		}
	}
	
}
	
