package com.cognizant.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.configurations.SpringMongoDBConfiguration;
import com.cognizant.model.Compliance;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


@Repository
public class PrjInfodaoimpl implements PrjInfodao{
	
	Logger LOGGER = LogManager.getLogger(PrjInfodaoimpl.class.getName());
		
	@Autowired
	SpringMongoDBConfiguration mongoDB;
		
	public Compliance getProjectComplianceDetails(String projectId, String projectName) {

		int total_No_of_Employees = 0;
		String nicForm;
		String ciaForm;
		int compliance_Available = 0;
		String totalEmployees = null;
		String compliance = null;
		try {
			DBCollection collection = mongoDB.getMongoTemplate().getCollection("Form_Compliance");
			DBObject statusQuery = new BasicDBObject("prjid", "prjName");
			if (null!=projectId){
				statusQuery.put("prjid", projectId);
			}
			if (null!=(projectName)){
				statusQuery.put("prjName", projectName);
			}
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
				nicForm = (String) object.get("ndaFormCompliant");
				ciaForm = (String) object.get("ciaFormCompliant" );
				if (nicForm.equals("Y") && ciaForm.equals("Y")) {
					compliance_Available++;
				}
			}
			totalEmployees = String.valueOf(total_No_of_Employees);
			compliance = String.valueOf(compliance_Available);
			return new Compliance(totalEmployees, compliance,
					String.valueOf(total_No_of_Employees - compliance_Available),"success");
		} catch (Exception e) {
			LOGGER.error("Error in side form_Compliance Dao" + e.getMessage());
			return new Compliance("0", "0", "0","failed");
		}
	}
	
}
	
