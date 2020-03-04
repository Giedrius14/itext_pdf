package com.example.itext_pdf.Aspose;

import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlFragment;
import com.aspose.pdf.Page;
import com.aspose.pdf.internal.html.HTMLDocument;
import com.aspose.pdf.internal.html.drawing.Size;
import com.aspose.pdf.internal.html.rendering.HtmlRenderer;
import com.aspose.pdf.internal.html.rendering.pdf.PdfDevice;
import com.aspose.pdf.internal.html.rendering.pdf.PdfRenderingOptions;
import com.example.itext_pdf.Thymeleaf_PDF.TableDto;
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
public class AsposeExample
{

	public static final String HTML_CSS = "<html>\n  <head>\n\t\t<style>\n\t\t\tp {padding:0px; margin:0px;}\n\t\t</style>\n\t</head>\n  <body>\n    <p>\n<b><span style=\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\">HTML&#160;tekstas, ar tiks?</span><span style=\"color:#3333ff; font-family:Yu Gothic UI Light;\"> </span></b>\n\n    </p>\n\n    <p>\n<b><span style=\"color:#3333ff;\">&#160;</span></b>\n\n    </p>\n\n</body>\n</html>";
	public static final String PNG = "<html> <head> </head> <body><p> <img src=\"https://support.content.office.net/en-us/media/3ac1da3e-ab76-41a8-85ba-5e48752138db.png\" /></p></body></html>";
	//	public static final String DIAGRAM_SVG = "<html> <head></head> <body><p> <svg width=\"90\" height=\"90\"><img src=\"https://upload.wikimedia.org/wikipedia/commons/4/4d/Periodic_table_large.svg\" /></svg></p></body></html>";
	public static final String DIAGRAM_SVG = "<svg><image xlink:href=\"https://upload.wikimedia.org/wikipedia/commons/4/4d/Periodic_table_large.svg\" /></svg>";
	public static final String BASE_64= "<div>\n" +
										"  <p>Taken from wikpedia</p>\n" +
										"  <img src=\"data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAAAUA\n" +
										"    AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO\n" +
										"        9TXL0Y4OHwAAAABJRU5ErkJggg==\" alt=\"Red dot\" />\n" +
										"</div>";
	//	public static final String DIAGRAM_SVG = "<svg height=\"100\" width=\"100\"><circle cx=\"50\" cy=\"50\" r=\"40\" stroke\"black\" stroke-width=\"3\" fill=\"red\" />Sorry, your browser does not support inline SVG.</svg> ";
	public static final String _SVG = "<html><body>" +
									  "<svg height=\"90\" width=\"200\">\n" +
									  "  <text x=\"10\" y=\"20\" style=\"fill:red;\">Several lines:\n" +
									  "    <tspan x=\"10\" y=\"45\">First line.</tspan>\n" +
									  "    <tspan x=\"10\" y=\"70\">Second line.</tspan>\n" +
									  "  </text>\n" +
									  "  Sorry, your browser does not support inline SVG.\n" +
									  "</svg>" +
									  "</body></html>";
	public static final String iFrame_SVG = "<html><body>" +
									  "<iframe src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/movingcart_1.svg\" frameborder=\"0\"></iframe>" +
									  "</body></html>";
	public static final String object_SVG2 = "<html><body>" +
											"<object data=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/movingcart_1.svg\" type=\"\"></object>" +
											"</body></html>";
	public static final String embed_SVG3 = "<html><body>" +
											"<embed src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/movingcart_1.svg\" type=\"\" />" +
											"</body></html>";
	public static void main(String[] args) throws IOException
	{
		new AsposeExample().generatePdf();
	}

	private void generatePdf() throws IOException
	{
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

//		htmlToPdf(renderedHtmlContent);
		HTMLDocument htmlDocument = new HTMLDocument( renderedHtmlContent,"about:blank");

		com.aspose.pdf.Document doc = new Document();
		Page page = doc.getPages().add();
		HtmlFragment htmlFragment = new HtmlFragment(renderedHtmlContent);
		page.getParagraphs().add(htmlFragment);
		doc.save("HTMLStringUsingDOM.pdf");




/*		String renderedHtmlContent = templateEngine.process("template", context);
		SvgLoadOptions loadopt = new SvgLoadOptions();
		loadopt.ConversionEngine = PclLoadOptions.ConversionEngines.NewEngine;
		Document doc = new Document();
		Page page = doc.getPages().add();

		HtmlFragment htmlFragment = new HtmlFragment(renderedHtmlContent);
		page.getParagraphs().add(htmlFragment);
		doc.save("HTMLStringUsingDOM.pdf");*/
		long finish =System.currentTimeMillis();
		long timeElapsed = getTimeElapsed(start, finish);
		log.info("############### END RENDERING #####################");
		log.info(timeElapsed + "min");
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
			listData.add(new TableDto("PNG", PNG));
//			listData.add(new TableDto("name", DIAGRAM_SVG));
			listData.add(new TableDto("BASE_64", BASE_64));
			listData.add(new TableDto("iFrame_SVG", iFrame_SVG));
			listData.add(new TableDto("object_SVG2", object_SVG2));
			listData.add(new TableDto("embed_SVG3", embed_SVG3));

//			listData.add(new TableDto("_SVG", _SVG));

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

	//TODO ////////////
	private void htmlToPdf(String html){
		// File name for resultant PDF file
		String Resultant_output = "simple-any-page.pdf";
		// create PdfRendering Options object
		PdfRenderingOptions pdf_options = new PdfRenderingOptions();
		// The PageSetup also provides different properties i.e. FirstPage, LastPage,
		// LeftPage, RightPage and they are used to setup (PageSize, Margin) for every page.
		// In most cases, usage of setup any page is enough, but in some complicated cases,
		// you may need to fine tune page settings. It can be done either by CSS styles or by using rendering options.
		// the size for drawing is in pixels
		pdf_options.getPageSetup().setAnyPage(new com.aspose.pdf.internal.html.drawing.Page(new Size(400, 100)));
		// instantiate PdfDevice object while passing PdfRenderingOptions and resultant file path as arguments
		try{
			PdfDevice pdf_device = new PdfDevice(pdf_options, Resultant_output);
			// Create HtmlRenderer object

			HtmlRenderer renderer = new HtmlRenderer();

			// Create HtmlDocument instance while passing path of already created HTML file
			HTMLDocument html_document = new HTMLDocument(html);

			// render the output using HtmlRenderer
			renderer.render(pdf_device, html_document);
		}
		finally
		{}
	}
}