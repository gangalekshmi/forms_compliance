package com.cognizant.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cognizant.controller.DocumentController;
import com.lowagie.text.DocumentException;

import org.springframework.stereotype.Component;

@Component
public class PdfGenerationUtil {

	Logger LOGGER = LogManager.getLogger(PdfGenerationUtil.class.getName());

	@Autowired
	private TemplateEngine templateEngine;

	public void createPdf(String templateName, Map map) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");

		Context ctx = new Context();

		if (map != null) {
			LOGGER.debug(" Map is not null");
			Iterator itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				
				Map.Entry pair = (Map.Entry) itMap.next();
				LOGGER.debug(" Key" + pair.getKey().toString());
				LOGGER.debug(" Value" + pair.getValue());
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}

		String processedHtml = templateEngine.process(templateName, ctx);
		LOGGER.debug(" HTML" + processedHtml);
		FileOutputStream os = null;
		String fileName = UUID.randomUUID().toString();
		try {
			final File outputFile = File.createTempFile(fileName, ".pdf");
			os = new FileOutputStream(outputFile);

			//ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();

			// response.setContentType("application/pdf");
			// response.setHeader("Content-Disposition", "attachment;
			// filename="+"MY_PDF_FILE.pdf");
			// bos.toByteArray();
			LOGGER.debug("PDF created successfully");

		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					/* ignore */ }
			}
		}
	}
}
