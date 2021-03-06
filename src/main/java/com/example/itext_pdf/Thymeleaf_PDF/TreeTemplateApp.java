//package com.example.itext_pdf.Thymeleaf_PDF;
//
//import com.itextpdf.text.DocumentException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.w3c.tidy.Tidy;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.FileSystems;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
//import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
//
//@Slf4j
//@SpringBootApplication
//public class TreeTemplateApp
//{
//
//	//	public static final String HTML = "<html><body> This is my Project <table width= '50%' border='0' align='left' cellpadding='0' cellspacing='0'><tr><td>{VERTICALTEXT}</td></tr></table></body></html>";
//	public static final String HTML_CSS = "<html>\n  <head>\n\t\t<style>\n\t\t\tp {padding:0px; margin:0px;}\n\t\t</style>\n\t</head>\n  <body>\n    <p>\n<b><span style=\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\">HTML&#160;tekstas, ar tiks?</span><span style=\"color:#3333ff; font-family:Yu Gothic UI Light;\"> </span></b>\n\n    </p>\n\n    <p>\n<b><span style=\"color:#3333ff;\">&#160;</span></b>\n\n    </p>\n\n</body>\n</html>";
//	public static final String HTML = "<html> <head> <style>p {padding:0px; margin:0px; color:blue} </style> </head> <body> <p>paveiksliukas </p> <p> <img border=\"0\" src=\"https://webstockreview.net/images/small-house-png-2.png\" /></p></body></html>";
//	public static final String LOCATION = "./html_in_cell.pdf";//"results/xmlworker/html_in_cell.pdf";
//
//	public static final String DIAGRAM = "<html> <head> <style>p {padding:0px; margin:0px; color:blue} </style> </head> <body><p> <img border=\"0\" src=\"https://webstockreview.net/images/small-house-png-2.png\" /></p></body></html>";
//
//
//	public static void main(String[] args) throws Exception {
//
//		File file = new File(LOCATION);
//		file.getParentFile().mkdirs();
//		new TreeTemplateApp().generatePdf();
//	}
//	private static final String OUTPUT_FILE = "Treee.pdf";
//	private static final String UTF_8 = "UTF-8";
//
//	public void generatePdf() throws Exception {
//		ClassLoaderTemplateResolver templateResolver = getTemplateResolver();
//		TemplateEngine templateEngine = new TemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//
//		List<Node> listData = new ArrayList<>();
//		populateData(listData);
//
//		Context context = new Context();
//		context.setVariable("listData", listData);
//
//		// FlyingSaucer needs XHTML - not just normal HTML. To make our life
//		// easy, we use JTidy to convert the rendered Thymeleaf template to
//		// XHTML. Note that this might not work for very complicated HTML. But
//		// it's good enough for a simple letter.
//		String renderedHtmlContent = templateEngine.process("tree", context);
//		ITextRenderer renderer = rendererActions(convertToXhtml(renderedHtmlContent));
//		createPdf(renderer);
//	}
//
//	private ClassLoaderTemplateResolver getTemplateResolver()
//	{
//		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		templateResolver.setPrefix("/");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode(TemplateMode.HTML);
//		templateResolver.setCharacterEncoding("UTF-8");
//		return templateResolver;
//	}
//
//	private ITextRenderer rendererActions(String xHtml) throws DocumentException, IOException
//	{
//		ITextRenderer renderer = new ITextRenderer();
//		renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);
//
//		// FlyingSaucer has a working directory. If you run this test, the working directory
//		// will be the root folder of your project. However, all files (HTML, CSS, etc.) are
//		// located under "/src/test/resources". So we want to use this folder as the working
//		// directory.
//		log.info("############### RENDERING #########################");
//		long start =System.currentTimeMillis();
//
//		String baseUrl = FileSystems
//				.getDefault()
//				.getPath("src", "main", "resources")
//				.toUri()
//				.toURL()
//				.toString();
//
//		renderer.setDocumentFromString(xHtml, null);
//		renderer.layout(); // TODO increase performance
//		long finish =System.currentTimeMillis();
//		long timeElapsed = getTimeElapsed(start, finish);
//		log.info("############### END RENDERING #####################");
//		log.info(timeElapsed + "min");
//		return renderer;
//	}
//
//	private void createPdf(ITextRenderer renderer) throws DocumentException, IOException
//	{
//		log.info("############### CreatePDF #########################");
//		// And finally, we create the PDF:
//		long start =System.currentTimeMillis();
//		OutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
//		renderer.createPDF(outputStream);
//		renderer.finishPDF();
//		outputStream.close();
//		long finish =System.currentTimeMillis();
//		long timeElapsed = getTimeElapsed(start, finish);
//		log.info("############### END CreatePDF #####################");
//		log.info(timeElapsed + "min");
//	}
//
//	public static long getTimeElapsed(long start, long finish)
//	{
//		return ((finish - start) / 1000) / 60;
//	}
//
//	private void populateData(List<Node> listData)
//	{
//		log.info("############### POPULATE #########################");
//		// 100 = 1 Page size: 53.1in;
//		//90000 = 13 + 3min
//		//20000 = 1min
//		//20000 = 7-8 min su html
//		//20000 =   5 min su html  Memory increase
//		//40000 = 30 min su html
//		// 50k    48 + 1min
//		List<Node> child = Arrays.asList(new Node("child", new ArrayList<>(){}));
//		while (listData.size() < 1)
//		{
//			listData.add(new Node("Parent A", Arrays.asList(new Node("Child A", child),new Node("Child B", child))));
//			listData.add(new Node("Parent B", Arrays.asList(
//					new Node("Child A", child),
//					new Node("Child",
//							 Arrays.asList(new Node("child",
//													Arrays.asList(new Node("child",
//																		   Arrays.asList(new Node("Child A", child),new Node("Child B", child))))
//													)
//							 )))
//			));
//		}
//		log.info("############### END POPULATE #########################");
//
//	}
//
//	private String convertToXhtml(String html) throws IOException
//	{
//		log.info("############### convertToXhtml #########################");
//		Tidy tidy = new Tidy();
//		tidy.setInputEncoding(UTF_8);
//		tidy.setOutputEncoding(UTF_8);
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
///*		Files.writeString(Paths.get("output.txt"), html);
//
//		return result;*/
//	}
//}
