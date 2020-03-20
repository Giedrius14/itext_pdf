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
	public static final String DIAGRAM_SVG2 = "<svg><image xlink:href=\"https://upload.wikimedia.org/wikipedia/commons/4/4d/Periodic_table_large.svg\" /></svg>";
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
	public static final String iFrame_SVG2 = "<html><body>" +
											"<iframe class=\"nm-bo-n section-iframe\" height=\"200\" width=\"100%\" sandbox=\"allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-top-navigation\" ng-src=\"https://www.delfi.lt\" src=\"https://www.delfi.lt\"></iframe>" +
											"</body></html>";
	public static final String object_SVG2 = "<html><body>" +
											 "<object data=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/movingcart_1.svg\" type=\"\"></object>" +
											 "</body></html>";
	public static final String embed_SVG3 = "<html><body>" +
											"<embed src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/3/movingcart_1.svg\" type=\"\" />" +
											"</body></html>";
	public static void main(String[] args) throws PDFNetException
	{
		new PdfTronExample().generatePdf();
	}

	private void generatePdf() throws PDFNetException
	{
		// PDFNet must always be initialized before any PDFTron
//todo VM options		-Djava.library.path="C:\\_Projects\\PDFTron\\PDFNetC64\\PDFNetC64\\Lib"
		PDFNet.initialize();

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver());

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

//			Convert.SVGOutputOptions svgOutputOptions = new Convert.SVGOutputOptions();
//			svgOutputOptions.setIndividualCharPlacement(true);
//			svgOutputOptions.setFlattenContent(0);
			HTML2PDF converter = new HTML2PDF();

//			converter.setImageDPI(25);

			// Our HTML data
			HTML2PDF.WebPageSettings settings = new HTML2PDF.WebPageSettings();
//			settings.setLoadImages(true);
//			settings.setIncludeInOutline(false);
//			settings.setPrintBackground(false);
			settings.setLoadErrorHandling(HTML2PDF.WebPageSettings.e_ignore);

			converter.insertFromHtmlString(convertToXhtml(renderedHtmlContent)); //convertToXhtml(renderedHtmlContent));
			if (converter.convert(doc))
			{

				doc.save("pdfTron_01.pdf", SDFDoc.SaveMode.LINEARIZED, null);

			} else {
				System.out.println("Conversion failed. HTTP Code: " + converter.getHTTPErrorCode() + "\n" + converter.getLog());
			}
			doc.close();
		} catch (PDFNetException | IOException e) {
			System.out.println(e);
			e.getStackTrace();
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
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	public static void populateData(List<TableDto> listData)
	{
		log.info("############### POPULATE #########################");

		while (listData.size() < 1)
		{
			listData.add(new TableDto("name", "paprastas textas"));
			listData.add(new TableDto("name", HTML_CSS));
			listData.add(new TableDto("name", DIAGRAM2));
			listData.add(new TableDto("DIAGRAM_SVG", DIAGRAM_SVG));
//			listData.add(new TableDto("_SVG", _SVG));
			listData.add(new TableDto("BASE_64", BASE_64));
//			listData.add(new TableDto("iFrame_SVG", iFrame_SVG));
//			listData.add(new TableDto("object_SVG2", object_SVG2));
//			listData.add(new TableDto("embed_SVG3", embed_SVG3));
//			listData.add(new TableDto("iFrame_SVG2", iFrame_SVG2));
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