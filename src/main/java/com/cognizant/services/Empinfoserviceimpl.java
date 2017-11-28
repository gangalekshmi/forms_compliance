package com.cognizant.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.BSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.cognizant.dao.Empinfodao;
import com.cognizant.model.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Service
public class Empinfoserviceimpl {

	@Autowired
	Empinfodao Empinfodaoimpl;

	public List<Object> getDetails() {
		System.out.println("inside getDetails");
		return Empinfodaoimpl.getDetails();

	}

	public List<Object> getEmpById(String id) {
		return Empinfodaoimpl.getEmpById(id);

	}

}
