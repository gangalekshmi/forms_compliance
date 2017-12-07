/**
 * 
 */
package com.cognizant.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.CIAFormDetails;
import com.cognizant.model.DocumentUploadStatus;
import com.cognizant.model.NDAFormDetails;
import com.cognizant.services.DocumentStorageService;
import com.cognizant.util.PdfGenerationUtil;
import com.lowagie.text.DocumentException;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * @author 407807
 *
 */
@RestController
public class DocumentController {

	Logger LOGGER = LogManager.getLogger(DocumentController.class.getName());

	@Autowired
	PdfGenerationUtil pdfgenerator;

	@Autowired
	DocumentStorageService documentStorageService;

	@RequestMapping(value = "api/signNICForm", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json", "application/xml" })
	public Object signandSaveNDAForm(HttpServletResponse response, @RequestBody NDAFormDetails formDetails)
			throws Exception {
		String status = null;
		try {
			Map formDetailsMap = new HashMap<String, String>();
			LOGGER.debug("employeeName " + formDetails.getEmployeeName());
			LOGGER.debug("companyname " + formDetails.getCompanyName());
			LOGGER.debug("date " + formDetails.getDate());
			LOGGER.debug("empId " + formDetails.getEmployeeID());
			formDetailsMap.put("employeename", formDetails.getEmployeeName() != null
					? formDetails.getEmployeeName().toUpperCase() : formDetails.getEmployeeName());
			formDetailsMap.put("companyname", formDetails.getCompanyName());
			formDetailsMap.put("date", formDetails.getDate());
			formDetailsMap.put("empId", formDetails.getEmployeeID());
			status = pdfgenerator.createPdf(response, "NIC", formDetailsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("FAILED".equals(status)){
			return new DocumentUploadStatus(status,null);
			}
		else{
			return new DocumentUploadStatus("Success",status);
			}
	}

	@RequestMapping(value = "api/signCIAForm", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json", "application/xml" })
	public Object signandSaveCIAForm(HttpServletResponse response, @RequestBody CIAFormDetails formDetails)
			throws Exception {
		String status = null;
		try {
			Map formDetailsMap = new HashMap<String, String>();
			LOGGER.debug("signature " + formDetails.getEmpSignature());
			LOGGER.debug("addressLine1 " + formDetails.getAddressLine1());
			LOGGER.debug("addressLine2 " + formDetails.getAddressLine2());
			LOGGER.debug("date " + formDetails.getDate());
			formDetailsMap.put("signature", formDetails.getEmpSignature() != null
					? formDetails.getEmpSignature().toUpperCase() : formDetails.getEmpSignature());
			formDetailsMap.put("addressLine1", formDetails.getAddressLine1());
			formDetailsMap.put("addressLine2", formDetails.getAddressLine2());
			formDetailsMap.put("date", formDetails.getDate());
			formDetailsMap.put("empId", formDetails.getEmpID());
			status = pdfgenerator.createPdf(response, "CIA", formDetailsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("FAILED".equals(status)) {
			return new DocumentUploadStatus(status,null);
		} else {
			return new DocumentUploadStatus("Success",status);
		}
	}

	@RequestMapping(value = "api/downloadPDF", method = RequestMethod.POST)
	public void downloadPDF(HttpServletResponse response, @RequestBody NDAFormDetails formDetails) {
		try {
			LOGGER.debug("employeeName " + formDetails.getEmployeeName());
			LOGGER.debug("companyname " + formDetails.getCompanyName());
			LOGGER.debug("date " + formDetails.getDate());
			Map formDetailsMap = new HashMap<String, String>();
			formDetailsMap.put("employeename", formDetails.getEmployeeName() != null
					? formDetails.getEmployeeName().toUpperCase() : formDetails.getEmployeeName());
			formDetailsMap.put("companyname", formDetails.getCompanyName());
			formDetailsMap.put("date", formDetails.getDate());
			pdfgenerator.downloadPdf(response, "NDA", formDetailsMap);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "api/retrivePDF/{fileName}", method = RequestMethod.GET)
	public void retrieveForms(HttpServletResponse response, @PathVariable String fileName) {
		LOGGER.debug("File Name " + fileName);
		try {
			GridFSDBFile gridFSDBFile = documentStorageService.retrieveFormsUsingFilename(fileName);
			try {
				if (gridFSDBFile != null && gridFSDBFile.getContentType() != null) {
					response.setContentType(gridFSDBFile.getContentType());
				} else {
					LOGGER.error("No records found in DB for " + fileName);
				}
				response.setContentType("application/pdf; name=\"" + fileName + "\"");
				response.setContentLength((int) gridFSDBFile.getLength());
				response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".pdf\"");
				LOGGER.debug("File name from DB " + gridFSDBFile.getFilename());
				OutputStream os = response.getOutputStream();
				IOUtils.copy(gridFSDBFile.getInputStream(), os);
				os.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
