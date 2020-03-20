/*
package com.example.itext_pdf.Thymeleaf_PDF;

import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;

@Slf4j
@SpringBootApplication
public class ThymeleafApp
{

	//	public static final String HTML = "<html><body> This is my Project <table width= '50%' border='0' align='left' cellpadding='0' cellspacing='0'><tr><td>{VERTICALTEXT}</td></tr></table></body></html>";
	public static final String HTML_CSS = "<html>\n  <head>\n\t\t<style>\n\t\t\tp {padding:0px; margin:0px;}\n\t\t</style>\n\t</head>\n  <body>\n    <p>\n<b><span style=\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\">HTML&#160;tekstas, ar tiks?</span><span style=\"color:#3333ff; font-family:Yu Gothic UI Light;\"> </span></b>\n\n    </p>\n\n    <p>\n<b><span style=\"color:#3333ff;\">&#160;</span></b>\n\n    </p>\n\n</body>\n</html>";
	public static final String HTML = "<html> <head> <style>p {padding:0px; margin:0px; color:blue} </style> </head> <body> <p>paveiksliukas </p> <p> <img border=\"0\" src=\"https://webstockreview.net/images/small-house-png-2.png\" /></p></body></html>";
	public static final String LOCATION = "./html_in_cell.pdf";//"results/xmlworker/html_in_cell.pdf";

	public static final String JSON = String
			.format("{\n    \"id\": \"3e5529ee-e9b0-43f8-b708-a77718ce1db9\",\n    \"type\": \"table\",\n    \"commentTargetId\": \"T_MD_8a7fbb42-d335-41de-9e26-c249bc3f0708\",\n    \"viewId\": \"Containment_DiagramMainImage__8a7fbb42-d335-41de-9e26-c249bc3f0708\",\n    \"hideHeader\": false,\n    \"rowCount\": 3,\n    \"title\": \"table\",\n    \"columns\": [\n      {\n        \"id\": \"ae042166-7126-4411-a4de-d36b1523daff\",\n        \"width\": 0.1,\n        \"text\": \"#\",\n        \"type\": \"flex\"\n      },\n      {\n        \"id\": \"37d79cef-62c9-4fb7-8089-829f8de7e66f\",\n        \"width\": 0.44,\n        \"text\": \"Name\",\n        \"type\": \"flex\"\n      },\n      {\n        \"id\": \"dc89c75a-130e-4a11-bcf7-8b595aa226be\",\n        \"width\": 0.44,\n        \"text\": \"Documentation\",\n        \"type\": \"flex\"\n      }\n    ],\n    \"rows\": [\n      [\n        {\n          \"number\": \"1\",\n          \"type\": \"numberParagraph\"\n        },\n        {\n          \"fieldName\": \"name\",\n          \"elementUUID\": \"5bc195b2-1487-482d-ab23-b06e2ca41831\",\n          \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n          \"text\": \"b1\",\n          \"type\": \"elementParagraph\",\n          \"navigation\": [\n            {\n              \"text\": \"b1\",\n              \"sectionId\": \"5bc195b2-1487-482d-ab23-b06e2ca41831\",\n              \"viewId\": \"3a287f76-9d4f-49d6-8890-dcbfdc688e44\",\n              \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n              \"viewName\": \"Model\",\n              \"type\": \"model\"\n            }\n          ],\n          \"commentTargetId\": \"C_-362082966_T_MD__5bc195b2-1487-482d-ab23-b06e2ca41831QPROP__Element__name\"\n        },\n        {\n          \"textType\": \"text/plain\",\n          \"text\": \"paprastas tekstas\",\n          \"type\": \"textParagraph\",\n          \"commentTargetId\": \"C_-362082966_T_MD__5bc195b2-1487-482d-ab23-b06e2ca41831QPROP__Element__documentation\"\n        }\n      ],\n      [\n        {\n          \"number\": \"2\",\n          \"type\": \"numberParagraph\"\n        },\n        {\n          \"fieldName\": \"name\",\n          \"elementUUID\": \"b252917e-c30d-49d6-9501-cbebe9effbff\",\n          \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n          \"text\": \"b2\",\n          \"type\": \"elementParagraph\",\n          \"navigation\": [\n            {\n              \"text\": \"b2\",\n              \"sectionId\": \"b252917e-c30d-49d6-9501-cbebe9effbff\",\n              \"viewId\": \"3a287f76-9d4f-49d6-8890-dcbfdc688e44\",\n              \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n              \"viewName\": \"Model\",\n              \"type\": \"model\"\n            }\n          ],\n          \"commentTargetId\": \"C_-362082966_T_MD__b252917e-c30d-49d6-9501-cbebe9effbffQPROP__Element__name\"\n        },\n        {\n          \"textType\": \"text/html\",\n          \"text\": \"<html>\\n  <head>\\n\\t\\t<style>\\n\\t\\t\\tp {padding:0px; margin:0px;}\\n\\t\\t</style>\\n\\t</head>\\n  <body>\\n    <p>\\n<b><span style=\\\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\\\">HTML&#160;tekstas, ar tiks?</span><span style=\\\"color:#3333ff; font-family:Yu Gothic UI Light;\\\"> </span></b>\\n\\n    </p>\\n\\n    <p>\\n<b><span style=\\\"color:#3333ff;\\\">&#160;</span></b>\\n\\n    </p>\\n\\n</body>\\n</html>\",\n          \"type\": \"textParagraph\",\n          \"commentTargetId\": \"C_-362082966_T_MD__b252917e-c30d-49d6-9501-cbebe9effbffQPROP__Element__documentation\"\n        }\n      ],\n      [\n        {\n          \"number\": \"3\",\n          \"type\": \"numberParagraph\"\n        },\n        {\n          \"fieldName\": \"name\",\n          \"elementUUID\": \"b61e15b1-716d-4dc0-9778-023070d3602c\",\n          \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n          \"text\": \"b3\",\n          \"type\": \"elementParagraph\",\n          \"navigation\": [\n            {\n              \"text\": \"b3\",\n              \"sectionId\": \"b61e15b1-716d-4dc0-9778-023070d3602c\",\n              \"viewId\": \"3a287f76-9d4f-49d6-8890-dcbfdc688e44\",\n              \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n              \"viewName\": \"Model\",\n              \"type\": \"model\"\n            }\n          ],\n          \"commentTargetId\": \"C_-362082966_T_MD__b61e15b1-716d-4dc0-9778-023070d3602cQPROP__Element__name\"\n        },\n        {\n          \"textType\": \"text/html\",\n          \"text\": \"<html>\\n  <head>\\n\\t\\t<style>\\n\\t\\t\\tp {padding:0px; margin:0px;}\\n\\t\\t</style>\\n\\t</head>\\n  <body>\\n    <p>\\npaveiksliukas\\n\\n    </p>\\n\\n    <p>\\n      \\n<img border=\\\"0\\\" src=\\\"mdp://4a3889ae-fe2a-401a-be01-db74e2f526fc\\\" />\\n\\n    </p>\\n\\n</body>\\n</html>\",\n          \"type\": \"textParagraph\",\n          \"commentTargetId\": \"C_-362082966_T_MD__b61e15b1-716d-4dc0-9778-023070d3602cQPROP__Element__documentation\"\n        }\n      ]\n    ]\n  }");
	public static final String DIAGRAM = "<html> <head> <style>p {padding:0px; margin:0px; color:blue} </style> </head> <body><p> <img border=\"0\" src=\"https://webstockreview.net/images/small-house-png-2.png\" /></p></body></html>";

	public static void main(String[] args) throws Exception
	{

		File file = new File(LOCATION);
		file.getParentFile().mkdirs();
		new ThymeleafApp().generatePdf();
	}

	private static final String TEMPLATE = "template";
	private static final String OUTPUT_FILE = "testFree01.pdf";
	private static final String UTF_8 = "UTF-8";

	public void generatePdf() throws Exception
	{

		List<TableDto> listData = new ArrayList<>();
		populateData(listData);
		List<List<TableDto>> newList = new ArrayList<>();
		while(newList.size()< 100){
			newList.add(listData);
		}

		long start = System.currentTimeMillis();

//		List<List<byte[]>> subSet = Lists.partition(listData, 50);
//		List<byte[]> htmlList = new ArrayList<>();
		int cores = Runtime.getRuntime().availableProcessors();
		int batchSize = newList.size() / cores;
		List<List<List<TableDto>>> partition = Lists.partition(newList, batchSize);
		List<byte[]> htmlList = partition.parallelStream().map(list -> {
//			htmlList.add(render(TEMPLATE,list));
			return render(TEMPLATE, list.get(0));
		}).collect(Collectors.toList());
*/
/*		List<byte[]> htmlList = Lists.partition(listData, batchSize).parallelStream().map(list -> {
//			htmlList.add(render(TEMPLATE,list));
			return render(TEMPLATE, list);
		}).collect(Collectors.toList());*//*


//		List<byte[]> temp = new ArrayList<>();
*/
/*
		List<byte[]> temp = Lists.partition(htmlList, 50).parallelStream().map(list -> {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try
			{
				outputStream = (ByteArrayOutputStream) mergePdf(list, new ByteArrayOutputStream());
//				temp.add(outputStream.toByteArray());
			}
			catch(DocumentException | IOException e)
			{
				e.printStackTrace();
			}
			return outputStream.toByteArray();
		}).collect(Collectors.toList());*//*

		mergePdf(htmlList, new FileOutputStream("Merged_Views.pdf"));
		long finish = System.currentTimeMillis();
		log.info("############### END FullDoc #####################");
		log.info(getTimeElapsed(start, finish));
//
	}

	public OutputStream mergePdf(List<byte[]> listOfPdfFiles, OutputStream outputStream) throws DocumentException, IOException
	{
		log.info("############### MergePDF #########################");
		long start = System.currentTimeMillis();

		Document document = new Document();
		PdfCopy copy = new PdfSmartCopy(document, outputStream);
		document.open();
		// Create reader list for the input pdf files.

		listOfPdfFiles.forEach(pdf -> {
			try
			{
				PdfReader reader = new PdfReader(pdf, null);
				copy.addDocument(reader);
				copy.freeReader(reader);
				reader.close();
			}
			catch(IOException | DocumentException e)
			{
				e.printStackTrace();
			}

		});
		document.close();

		long finish = System.currentTimeMillis();
		log.info("############### MergePDF End #########################");
		log.info(getTimeElapsed(start, finish));
		return outputStream;
	}

	private byte[] render(String template, List<TableDto> list)
	{
		Context context = new Context();
		context.setVariable("listData", list);
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver());
		String renderedHtmlContent = templateEngine.process(template, context);
		try
		{
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			createPdf(rendererActions(convertToXhtml(renderedHtmlContent)), byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
		}
		return null;
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

	private ITextRenderer rendererActions(String xHtml) throws DocumentException, IOException
	{
		ITextRenderer renderer = new ITextRenderer();
		renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);

		// FlyingSaucer has a working directory. If you run this test, the working directory
		// will be the root folder of your project. However, all files (HTML, CSS, etc.) are
		// located under "/src/test/resources". So we want to use this folder as the working
		// directory.
		log.info("############### RENDERING #########################");
		long start = System.currentTimeMillis();

		String baseUrl = FileSystems
				.getDefault()
				.getPath("src", "main", "resources")
				.toUri()
				.toURL()
				.toString();

		renderer.setDocumentFromString(xHtml, null);
		renderer.layout(); // TODO increase performance
		long finish = System.currentTimeMillis();
		log.info("############### END RENDERING #####################");
		log.info(getTimeElapsed(start, finish));
		return renderer;
	}

	private void createPdf(ITextRenderer renderer, OutputStream outputStream) throws DocumentException, IOException
	{
		log.info("############### CreatePDF #########################");
		// And finally, we create the PDF:
		long start = System.currentTimeMillis();
		renderer.createPDF(outputStream);
		renderer.finishPDF();
		outputStream.close();
		long finish = System.currentTimeMillis();
		log.info("############### END CreatePDF #####################");
		log.info(getTimeElapsed(start, finish));
	}

	private String getTimeElapsed(long start, long finish)
	{
		long ms = finish - start;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(ms);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(ms);
		return String.format("min: %d:%d", minutes, seconds);
	}

	private void populateData(List<TableDto> listData)
	{
		log.info("############### POPULATE #########################");
		// 100 = 1 Page size: 53.1in;
		//90000 = 13 + 3min
		//20000 = 1min
		//20000 = 7-8 min su html
		//20000 =   5 min su html  Memory increase

		// 50k    48 + 1min
		// 300000k 7.2h
		// 1000=500 1:94 improved to 0:26

		//20000 = 30 min  impr to 16:97
//		  10k     7:50 i 0:50sec

//		while (listData.size() < 1000)
		for(int i = 0; i < 100; i++)
		{
			listData.add(new TableDto("name"+i, "paprastas textas"));
			listData.add(new TableDto("name"+i, HTML_CSS));
		}
		log.info("############### END POPULATE #########################");

	}

	private String convertToXhtml(String html) throws IOException
	{
		log.info("############### convertToXhtml #########################");
		Tidy tidy = new Tidy();
		tidy.setInputEncoding(UTF_8);
		tidy.setOutputEncoding(UTF_8);
		tidy.setXHTML(true);
		tidy.setMakeClean(true);
		tidy.setJoinClasses(true);
		tidy.setJoinStyles(true);
		tidy.setWraplen(0);
		tidy.setDropProprietaryAttributes(true);
		tidy.setEscapeCdata(true);
		tidy.setHideComments(true);
		tidy.setMakeBare(true);
//		tidy.setRawOut(true); //palikti uzkomentuota
//		tidy.setFixComments(true); palikti uzkomentuota
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.parseDOM(
				new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)),
				outputStream
		);
		return outputStream.toString(StandardCharsets.UTF_8);
*/
/*		Files.writeString(Paths.get("output.txt"), html);

		return result;*//*

	}
}
*/
