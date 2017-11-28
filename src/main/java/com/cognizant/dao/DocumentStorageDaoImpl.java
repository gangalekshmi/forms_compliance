package com.cognizant.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.configurations.SpringDataGridFSTemplate;
import com.cognizant.controller.DocumentController;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public class DocumentStorageDaoImpl implements DocumentStorageDao {

	Logger LOGGER = LogManager.getLogger(DocumentStorageDaoImpl.class.getName());
	
	@Autowired
	SpringDataGridFSTemplate springDataGridFSTemplate;
	
	
	@Override
	public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData) throws Exception {
		  return this.springDataGridFSTemplate.gridFsTemplate()
				    .store(inputStream, fileName, contentType, metaData).getId()
				    .toString();
	}

	@Override
	public GridFSDBFile retrive(String fileName) {
		return null;
	}

	@Override
	public GridFSDBFile getById(String id) throws Exception {
		return this.springDataGridFSTemplate.gridFsTemplate().findOne(new Query(Criteria.where("_id").is(
			    id)));		
		}

	@Override
	public GridFSDBFile getByFilename(String filename) throws Exception {
		  LOGGER.debug("File Name "+filename);
		return this.springDataGridFSTemplate.gridFsTemplate().findOne(new Query(Criteria.where("filename").is(
				filename)));
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
