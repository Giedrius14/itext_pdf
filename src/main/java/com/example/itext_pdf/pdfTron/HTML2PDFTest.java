package com.example.itext_pdf.pdfTron;

import com.example.itext_pdf.Thymeleaf_PDF.TableDto;
import com.pdftron.pdf.HTML2PDF;
import com.pdftron.pdf.PDFDoc;
import com.pdftron.pdf.PDFNet;
import com.pdftron.sdf.SDFDoc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.ArrayList;
import java.util.List;

import static com.example.itext_pdf.Aspose.AsposeExample.getTemplateResolver;

public class HTML2PDFTest {
	//---------------------------------------------------------------------------------------
	// The following sample illustrates how to convert HTML pages to PDF format using
	// the HTML2PDF class.
	//
	// 'pdftron.PDF.HTML2PDF' is an optional PDFNet Add-On utility class that can be
	// used to convert HTML web pages into PDF documents by using an external module (html2pdf).
	//
	// html2pdf modules can be downloaded from http://www.pdftron.com/pdfnet/downloads.html.
	//
	// Users can convert HTML pages to PDF using the following operations:
	// - Simple one line static method to convert a single web page to PDF.
	// - Convert HTML pages from URL or string, plus optional table of contents, in user defined order.
	// - Optionally configure settings for proxy, images, java script, and more for each HTML page.
	// - Optionally configure the PDF output, including page size, margins, orientation, and more.
	// - Optionally add table of contents, including setting the depth and appearance.
	//---------------------------------------------------------------------------------------
//	public static final String DIAGRAM2 = "<html> <head> <style>p {padding:0px; margin:0px; color:blue} </style> </head> <body><p> <img src=\"https://support.content.office.net/en-us/media/3ac1da3e-ab76-41a8-85ba-5e48752138db.png\" /></p></body></html>";

	public static void main(String[] args) {
		String output_path = "./html2pdf_example";
		String host = "http://www.gutenberg.org/";
		String page0 = "wiki/Main_Page";
		String page1 = "catalog/";
		String page2 = "browse/recent/last1";
		String page3 = "wiki/Gutenberg:The_Sheet_Music_Project";

		// The first step in every application using PDFNet is to initialize the
		// library and set the path to common PDF resources. The library is usually
		// initialized only once, but calling Initialize() multiple times is also fine.
		PDFNet.initialize();

		// For HTML2PDF we need to locate the html2pdf module. If placed with the
		// PDFNet library, or in the current working directory, it will be loaded
		// automatically. Otherwise, it must be set manually using HTML2PDF.SetModulePath.
		try {
			HTML2PDF.setModulePath("C:\\_Projects\\PDFTron\\PDFNetC64\\PDFNetC64\\Lib");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		//--------------------------------------------------------------------------------
		// Example 1) Simple conversion of a web page to a PDF doc.

/*		try {
			PDFDoc doc = new PDFDoc();

			// now convert a web page, sending generated PDF pages to doc
			if (HTML2PDF.convert(doc, host + page0))
				doc.save(output_path + "_01.pdf", SDFDoc.SaveMode.LINEARIZED, null);
			else
				System.out.println("Conversion failed.");

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}*/

		//--------------------------------------------------------------------------------
		// Example 2) Modify the settings of the generated PDF pages and attach to an
		// existing PDF document.

/*		try {
			// open the existing PDF, and initialize the security handler
			PDFDoc doc = new PDFDoc("C:\\_Projects\\itext_pdf");
			doc.initSecurityHandler();

			// create the HTML2PDF converter object and modify the output of the PDF pages
			HTML2PDF converter = new HTML2PDF();
			converter.setImageQuality(25);
			converter.setPaperSize(PrinterMode.e_11x17);

			// insert the web page to convert
			converter.insertFromURL(host + page0);

			// convert the web page, appending generated PDF pages to doc
			if (converter.convert(doc))
				doc.save(output_path + "_02.pdf", SDFDoc.SaveMode.LINEARIZED, null);
			else
				System.out.println("Conversion failed. HTTP Code: " + converter.getHTTPErrorCode() + "\n" + converter.getLog());

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}*/

		//--------------------------------------------------------------------------------
		// Example 3) Convert multiple web pages, adding a table of contents, and setting
		// the first page as a cover page, not to be included with the table of contents outline.

	/*	try {
			PDFDoc doc = new PDFDoc();

			HTML2PDF converter = new HTML2PDF();

			// Add a cover page, which is excluded from the outline, and ignore any errors
			HTML2PDF.WebPageSettings cover = new HTML2PDF.WebPageSettings();
			cover.setLoadErrorHandling(HTML2PDF.WebPageSettings.e_ignore);
			cover.setIncludeInOutline(false);
			converter.insertFromURL(host + page3, cover);

			// Add a table of contents settings (modifying the settings is optional)
			HTML2PDF.TOCSettings toc = new HTML2PDF.TOCSettings();
			toc.setDottedLines(false);
			converter.insertTOC(toc);

			// Now add the rest of the web pages, disabling external links and
			// skipping any web pages that fail to load.
			//
			// Note that the order of insertion matters, so these will appear
			// after the cover and table of contents, in the order below.
			HTML2PDF.WebPageSettings settings = new HTML2PDF.WebPageSettings();
			settings.setLoadErrorHandling(HTML2PDF.WebPageSettings.e_skip);
			settings.setExternalLinks(false);
			converter.insertFromURL(host + page0, settings);
			converter.insertFromURL(host + page1, settings);
			converter.insertFromURL(host + page2, settings);

			if (converter.convert(doc))
				doc.save(output_path + "_03.pdf", SDFDoc.SaveMode.LINEARIZED, null);
			else
				System.out.println("Conversion failed. HTTP Code: " + converter.getHTTPErrorCode() + "\n" + converter.getLog());

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}*/

		//--------------------------------------------------------------------------------
		// Example 4) Convert HTML string to PDF.


		ClassLoaderTemplateResolver templateResolver = getTemplateResolver();
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		List<TableDto> listData = new ArrayList<>();
//		populateData(listData);

		Context context = new Context();
		context.setVariable("listData", listData);

//		log.info("############### RENDERING #########################");
//		long start =System.currentTimeMillis();
		String renderedHtmlContent = templateEngine.process("template", context);
		try {
			PDFDoc doc = new PDFDoc();

			HTML2PDF converter = new HTML2PDF();

			// Our HTML data
			String html = "<html><body><h1>Heading</h1><p>Paragraph.</p></body></html>";

			// Add html data
			converter.insertFromHtmlString(renderedHtmlContent);
			// Note, InsertFromHtmlString can be mixed with the other Insert methods.

			if (converter.convert(doc))
				doc.save(output_path + "_04.pdf", SDFDoc.SaveMode.LINEARIZED, null);
			else
				System.out.println("Conversion failed. HTTP Code: " + converter.getHTTPErrorCode() + "\n" + converter.getLog());

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		PDFNet.terminate();
	}
}