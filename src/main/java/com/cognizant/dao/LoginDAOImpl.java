package com.cognizant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Login;
import com.mongodb.WriteResult;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	MongoOperations mongoOperations;
	
	@Override
	public void create(Login login) {
		mongoOperations.insert(login);
	}

	@Override
	public Login readById(Long id) {
		Query query = new Query(Criteria.where("_id").is(id));
		Login login = mongoOperations.findOne(query, Login.class);
		return login;
	}

	@Override
	public void update(Login login) {
		mongoOperations.save(login);
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOperations.remove(query, Login.class);
		return result.getN();
	}
	
}
