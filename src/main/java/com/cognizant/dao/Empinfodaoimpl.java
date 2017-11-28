package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.configurations.SpringMongoDBConfiguration;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Repository
public class Empinfodaoimpl implements Empinfodao {

	Logger LOGGER = LogManager.getLogger(Empinfodaoimpl.class.getName());

	@Autowired
	private SpringMongoDBConfiguration mongoDB;

	@Override
	public List<Object> getDetails() {
		LOGGER.debug("inside Empinfodaoimpl");
		List<Object> list = new ArrayList<Object>();
		try {

			DBCollection collection = mongoDB.getMongoTemplate().getCollection("Form_Compliance");
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

	public List<Object> getEmpById(String id) {

		List<Object> list1 = new ArrayList<Object>();
		try {
			DBCollection collection = mongoDB.getMongoTemplate().getCollection("Form_Compliance");
			LOGGER.debug("Collectionnnnnnnn" + collection.getCount());
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("empid", id);
			DBCursor cursor = collection.find(whereQuery);
			while (cursor.hasNext()) {
				list1.add(cursor.next());
				// System.out.println("cursorrrrr"+cursor.next());
			}
		} catch (Exception e) {
			LOGGER.error(e.getClass().getName() + ": " + e.getMessage());
		}
		return list1;
	}

}
