package com.cognizant.services;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface DocumentStorageService {
	
	public GridFSDBFile retrieveFormsUsingFilename(String fileName)throws Exception;

}
