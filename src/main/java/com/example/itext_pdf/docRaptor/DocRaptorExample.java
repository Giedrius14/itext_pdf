//package com.example.itext_pdf.docRaptor;
//
//import com.example.itext_pdf.Thymeleaf_PDF.TableDto;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Font;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PdfWriter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.w3c.tidy.Tidy;
//
//import java.awt.*;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.itext_pdf.Thymeleaf_PDF.ThymeleafApp.HTML;
//
//// These are the most important packages to import
//// for basic document manipulation.
//
//@Slf4j
//@SpringBootApplication
//public class DocRaptorExample
//{
//
//	public static final String HTML_CSS = "<html>\n  <head>\n\t\t<style>\n\t\t\tp {padding:0px; margin:0px;}\n\t\t</style>\n\t</head>\n  <body>\n    <p>\n<b><span style=\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\">HTML&#160;tekstas, ar tiks?</span><span style=\"color:#3333ff; font-family:Yu Gothic UI Light;\"> </span></b>\n\n    </p>\n\n    <p>\n<b><span style=\"color:#3333ff;\">&#160;</span></b>\n\n    </p>\n\n</body>\n</html>";
//
//	// Just a simple setup for the application
//	public static void main(String[] args)
//	{
//		new DocRaptorExample().generatePdf();
//	}
//
//	private void generatePdf()
//	{
//		ClassLoaderTemplateResolver templateResolver = getTemplateResolver();
//		TemplateEngine templateEngine = new TemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//
//		List<TableDto> listData = new ArrayList<>();
//		populateData(listData);
//
//		Context context = new Context();
//		context.setVariable("listData", listData);
//
//		log.info("############### RENDERING #########################");
//		long start =System.currentTimeMillis();
//		String renderedHtmlContent = templateEngine.process("template", context);
//
//		try {
//			Document doc = new Document();
//			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("openPDF.pdf"));
//			//setting font family, color
//			Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, Color.RED);
//			doc.open();
//			Paragraph para = new Paragraph(renderedHtmlContent, font);
//			doc.add(para);
//			doc.close();
//			writer.close();
//		} catch (DocumentException | FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// TODO senas lib delto nedejau https://docraptor.com/documentation/java
//
//		long finish =System.currentTimeMillis();
//		long timeElapsed = getTimeElapsed(start, finish);
//		log.info("############### END RENDERING #####################");
//		log.info(timeElapsed + "min");
//	}
//
//	private ClassLoaderTemplateResolver getTemplateResolver()
//	{
//		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		templateResolver.setPrefix("/");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode(HTML);
//		templateResolver.setCharacterEncoding("UTF-8");
//		return templateResolver;
//	}
//
//	private void populateData(List<TableDto> listData)
//	{
//		log.info("############### POPULATE #########################");
//		// 100 = 1 Page size: 53.1in;
//		// 3000 1min
//		// 10000 3min su optimizavimu convertToXhtml
//		// 40000 36min su optimizavimu convertToXhtml
//
//
//		while (listData.size() < 100)
//		{
//			listData.add(new TableDto("name", "paprastas textas"));
//			listData.add(new TableDto("name", HTML_CSS));
//		}
//		log.info("############### END POPULATE #########################");
//
//	}
//	private long getTimeElapsed(long start, long finish)
//	{
//		return ((finish - start) / 1000) / 60;
//	}
//
//	private String convertToXhtml(String html) throws IOException
//	{
//		log.info("############### convertToXhtml #########################");
//		Tidy tidy = new Tidy();
//		tidy.setInputEncoding("UTF-8");
//		tidy.setOutputEncoding("UTF-8");
//		tidy.setXHTML(true);
//		tidy.setMakeClean(true);
//		tidy.setJoinClasses(true);
//		tidy.setJoinStyles(true);
//		tidy.setWraplen(0);
//		tidy.setDropProprietaryAttributes(true);
//		tidy.setEscapeCdata(true);
//		tidy.setHideComments(true);
//		tidy.setMakeBare(true);
//		tidy.setRawOut(true); //palikti uzkomentuota
////		tidy.setFixComments(true); palikti uzkomentuota
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		tidy.parseDOM(
//				new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)),
//				outputStream
//		);
//		return outputStream.toString(StandardCharsets.UTF_8);
//	}
//}