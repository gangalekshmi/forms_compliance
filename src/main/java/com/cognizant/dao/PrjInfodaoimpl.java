package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.configurations.SpringMongoDBConfiguration;
import com.cognizant.model.Project;
import com.cognizant.services.Empinfoserviceimpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


@Repository
public class PrjInfodaoimpl implements PrjInfodao{

	
	
	
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
}
	
