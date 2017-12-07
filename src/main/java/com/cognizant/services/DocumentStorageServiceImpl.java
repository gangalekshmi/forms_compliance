package com.cognizant.services;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.controller.DocumentController;
import com.cognizant.dao.DocumentStorageDao;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class DocumentStorageServiceImpl implements DocumentStorageService {
	
	Logger LOGGER = LogManager.getLogger(DocumentStorageServiceImpl.class.getName());
	
	@Autowired
	DocumentStorageDao documentStorageDao;

	@Override
	public GridFSDBFile retrieveFormsUsingFilename(String fileName) throws Exception {
		LOGGER.debug("Inside retrieveFormsUsingFilename");
		GridFSDBFile gridFSDBFile= documentStorageDao.getByFilename(fileName);
		LOGGER.debug("Inside retrieveFormsUsingFilename");			
		return gridFSDBFile;
	}
}
