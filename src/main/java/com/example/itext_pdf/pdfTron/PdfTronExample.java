package com.example.itext_pdf.pdfTron;

import com.example.itext_pdf.Thymeleaf_PDF.TableDto;
import com.pdftron.common.PDFNetException;
import com.pdftron.pdf.HTML2PDF;
import com.pdftron.pdf.PDFDoc;
import com.pdftron.pdf.PDFNet;
import com.pdftron.sdf.SDFDoc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//import static com.example.itext_pdf.Thymeleaf_PDF.ThymeleafApp.HTML;

// These are the most important packages to import
// for basic document manipulation.

@Slf4j
@SpringBootApplication
public class PdfTronExample
{

	public static final String HTML_CSS = "<html>\n  <head>\n\t\t<style>\n\t\t\tp {padding:0px; margin:0px;}\n\t\t</style>\n\t</head>\n  <body>\n    <p>\n<b><span style=\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\">HTML&#160;tekstas, ar tiks?</span><span style=\"color:#3333ff; font-family:Yu Gothic UI Light;\"> </span></b>\n\n    </p>\n\n    <p>\n<b><span style=\"color:#3333ff;\">&#160;</span></b>\n\n    </p>\n\n</body>\n</html>";
	public static final String DIAGRAM2 = "<html> <head> </head> <body><p> <img src=\"https://support.content.office.net/en-us/media/3ac1da3e-ab76-41a8-85ba-5e48752138db.png\" /></p></body></html>";
	public static final String DIAGRAM_SVG = "<html> <head></head> <body><p> <img src=\"https://upload.wikimedia.org/wikipedia/commons/4/4d/Periodic_table_large.svg\" /></p></body></html>";

	// Just a simple setup for the application
	public static void main(String[] args) throws PDFNetException
	{
		new PdfTronExample().generatePdf();
	}

	private void generatePdf() throws PDFNetException
	{
		// PDFNet must always be initialized before any PDFTron
		// classes and methods can be used
		PDFNet.initialize();
//		try {
//			HTML2PDF.setModulePath("C:\\_Projects\\PDFTron\\PDFNetC64\\PDFNetC64\\Lib");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}


		ClassLoaderTemplateResolver templateResolver = getTemplateResolver();
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		List<TableDto> listData = new ArrayList<>();
		populateData(listData);

		Context context = new Context();
		context.setVariable("listData", listData);

		log.info("############### RENDERING #########################");
		long start =System.currentTimeMillis();
		String renderedHtmlContent = templateEngine.process("template", context);

		// Most PDFTron operations are required to be wrapped in
		// a try-catch block for PDFNetException, or in a method/class that
		// throws PDFNetException


		try {
			PDFDoc doc = new PDFDoc();
//			Optimizer.optimize(doc);

			HTML2PDF converter = new HTML2PDF();
//			converter.setImageDPI(25);

			// Our HTML data
//			HTML2PDF.WebPageSettings settings = new HTML2PDF.WebPageSettings();
//			settings.setLoadImages(true);
//			settings.setIncludeInOutline(false);
//			settings.setPrintBackground(false);
//			settings.setLoadErrorHandling(HTML2PDF.WebPageSettings.e_ignore);
			converter.insertFromHtmlString(convertToXhtml(renderedHtmlContent));//convertToXhtml(renderedHtmlContent));
			if (converter.convert(doc))
			{

				doc.save("pdfTron_01.pdf", SDFDoc.SaveMode.LINEARIZED, null);

			} else {
				System.out.println("Conversion failed. HTTP Code: " + converter.getHTTPErrorCode() + "\n" + converter.getLog());
			}
			doc.close();
		} catch (PDFNetException e) {
			System.out.println(e);
			e.getStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		long finish =System.currentTimeMillis();
		long timeElapsed = getTimeElapsed(start, finish);
		log.info("############### END RENDERING #####################");
		log.info(timeElapsed + "min");
		PDFNet.terminate();
	}

	private ClassLoaderTemplateResolver getTemplateResolver()
	{
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	private void populateData(List<TableDto> listData)
	{
		log.info("############### POPULATE #########################");
		// 100 = 1 Page size: 53.1in;
		// 3000 1min
		// 10000 3min su optimizavimu convertToXhtml
		// 40000 36min su optimizavimu convertToXhtml


		while (listData.size() < 1)
		{
			listData.add(new TableDto("name", "paprastas textas"));
			listData.add(new TableDto("name", HTML_CSS));
			listData.add(new TableDto("name", DIAGRAM2));
			listData.add(new TableDto("name", DIAGRAM_SVG));
		}
		log.info("############### END POPULATE #########################");

	}
	private long getTimeElapsed(long start, long finish)
	{
		return ((finish - start) / 1000) / 60;
	}

	private String convertToXhtml(String html) throws IOException
	{
		log.info("############### convertToXhtml #########################");
		Tidy tidy = new Tidy();
		tidy.setInputEncoding("UTF-8");
		tidy.setOutputEncoding("UTF-8");
		tidy.setXHTML(true);
		tidy.setMakeClean(true);
		tidy.setJoinClasses(true);
		tidy.setJoinStyles(true);
		tidy.setWraplen(0);
		tidy.setDropProprietaryAttributes(true);
		tidy.setEscapeCdata(true);
		tidy.setHideComments(true);
		tidy.setMakeBare(true);
		tidy.setRawOut(true); //palikti uzkomentuota
//		tidy.setFixComments(true); palikti uzkomentuota
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.parseDOM(
				new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)),
				outputStream
		);
		return outputStream.toString(StandardCharsets.UTF_8);
	}
}