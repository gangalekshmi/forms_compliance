package com.cognizant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.cognizant.configurations.SpringMongoDBConfiguration;
import com.cognizant.model.Employee;
import com.cognizant.services.Empinfoserviceimpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@Repository
public class Empinfodaoimpl implements Empinfodao {

	Logger LOGGER = LogManager.getLogger(Empinfoserviceimpl.class.getName());

	@Autowired
	private SpringMongoDBConfiguration mongoDB;

	@Override
	public List<Object> getDetails() {
		LOGGER.debug("Inside Empinfodaoimpl");
		List<Object> list = new ArrayList<Object>();
		try {
			/*
			 * MongoClient mongo = new MongoClient("localhost", 27017);
			 * System.out.println("Sucessfully connected with database!!!!!!!!"
			 * );
			 * 
			 * @SuppressWarnings("deprecation") DB db = mongo.getDB("database");
			 * System.out.println("Entered in database"); DBCollection
			 * collection = db.getCollection("empinfo");
			 */
			DBCollection collection = mongoDB.getMongoTemplate().getCollection("empinfo");
			DBCursor dbo = collection.find();
			while (dbo.hasNext()) {
				list.add(dbo.next());
				System.out.println(dbo.toString());
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}

	public List<Object> getEmpById(String id,String empname) {

		List<Object> list1 = new ArrayList<Object>();
		try {
			/*
			 * MongoClient mongo = new MongoClient("localhost", 27017);
			 * System.out.println("Sucessfully connected with database!!!!!!!!"
			 * );
			 * 
			 * @SuppressWarnings("deprecation") DB db = mongo.getDB("database");
			 * System.out.println("Entered in database getEmpById()");
			 * DBCollection collection = db.getCollection("empinfo");
			 */
			DBCollection collection = mongoDB.getMongoTemplate().getCollection("empinfo");
			
			BasicDBObject whereQuery = new BasicDBObject();
			//if(!"null".equals(id))
				whereQuery.put("id", id);
			//	if(!"null".equals(empname))
			//	whereQuery.put("empname", empname);
			
			DBCursor cursor = collection.find(whereQuery);
			while (cursor.hasNext()) {
				list1.add(cursor.next());
			}
			
		} catch (Exception e) {
			LOGGER.error("Error inside Employee DAO " + e.getStackTrace());
		}
		return list1;
	}
	


}
