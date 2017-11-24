/**
 * 
 */
package com.cognizant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.util.PdfGenerationUtil;

/**
 * @author 407807
 *
 */
@RestController
public class DocumentController {

	Logger LOGGER = LogManager.getLogger(DocumentController.class.getName());

	@Autowired
	PdfGenerationUtil pdfgenerator;

	@RequestMapping(value = "/createPdf/{fname}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Object createPDF(HttpServletResponse response,@PathVariable String fname) throws Exception {
		try {
			LOGGER.debug("Going to create PDF");
			Map<String, String> data = new HashMap<String, String>();
			LOGGER.debug( "Input text "+fname);
			data.put("name", fname);
			pdfgenerator.createPdf("compliance-forms", data);

			LOGGER.debug("PDF Creation completed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
