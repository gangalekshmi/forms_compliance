package com.cognizant.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cognizant.configurations.SpringDataGridFSTemplate;
import com.cognizant.controller.EmpinfoController;
import com.lowagie.text.DocumentException;
import com.mongodb.gridfs.GridFSFile;

@Component
public class PdfGenerationUtil {

	Logger LOGGER = LogManager.getLogger(PdfGenerationUtil.class.getName());

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private SpringDataGridFSTemplate springDataGridFsTemplate;

	/**
	 * createPdf
	 * 
	 * @param templateName
	 * @param map
	 * @throws Exception
	 *             http://www.oodlestechnologies.com/blogs/How-To-Create-PDF-
	 *             through-HTML-Template-In-Spring-Boot
	 */
	public String createPdf(HttpServletResponse response, String templateName, Map map) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry pair = (Map.Entry) itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		String processedHtml = templateEngine.process(templateName, ctx);
		FileOutputStream os = null;
		ByteArrayOutputStream bos = null;
		String fileName = (String) map.get("empId");
		fileName = fileName + "_"+templateName;
		try {
			final File outputFile = File.createTempFile(fileName, ".pdf");
			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			if(processedHtml!=null){
			renderer.setDocumentFromString(processedHtml);
			}else{
				throw new Exception("Error parsing template HTML "+templateName);
			}			
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();
			LOGGER.debug("PDF created successfully");
			LOGGER.debug(" Going to save File");
		
			String File_ID = saveFormsInMongoDB(outputFile,fileName);

			return File_ID;
			
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					LOGGER.debug("error creating PDF : "+ e.getMessage());
				}catch(Exception e){
					LOGGER.debug("error creating PDF : "+ e.getMessage());
				}
			}
		}
	}
	
	/**
	 * saveFormsInMongoDB
	 * @param outPutFile
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public String saveFormsInMongoDB(File outPutFile,String fileName ) throws Exception{
		InputStream inputStream = new FileInputStream(outPutFile);
		GridFSFile gridFSFile=this.springDataGridFsTemplate.gridFsTemplate().store(inputStream, fileName, "application/pdf");
		if(gridFSFile!=null && gridFSFile.getId()!=null)
		return gridFSFile.getId().toString()+":" +gridFSFile.getFilename() ;
		else
		return "FAILED"; 
	}

	/**
	 * downloadPdf
	 * 
	 * @param response
	 * @return
	 * @throws DocumentException
	 */
	public void downloadPdf(HttpServletResponse response, String templateName, Map map) throws DocumentException {
		Context ctx = new Context();
		if (map != null) {
			Iterator itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry pair = (Map.Entry) itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		LOGGER.debug("Going to create PDF using template" + templateName);
		String processedHtml = templateEngine.process(templateName, ctx);
		FileOutputStream os = null;
		// String fileName = UUID.randomUUID().toString();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(processedHtml);
		renderer.layout();
		renderer.createPDF(bos, false);
		renderer.finishPDF();		
		OutputStream outPutstream;
		try {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename="+ "NDA.pdf");
			outPutstream = response.getOutputStream();
			bos.writeTo(outPutstream);
			outPutstream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
