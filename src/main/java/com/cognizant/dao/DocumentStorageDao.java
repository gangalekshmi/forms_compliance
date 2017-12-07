package com.cognizant.dao;


import java.io.InputStream;
import java.util.List;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

public interface DocumentStorageDao {

	public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData)throws Exception;

	public GridFSDBFile retrive(String fileName)throws Exception;

	public GridFSDBFile getById(String id)throws Exception;

	public GridFSDBFile getByFilename(String filename)throws Exception;

	public List findAll();

}
