package com.example.itext_pdf.itext_html2pdf;

import com.example.itext_pdf.Thymeleaf_PDF.TableDto;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Html2Pdf
{

	public static final String HTML_CSS = "<html>\n  <head>\n\t\t<style>\n\t\t\tp {padding:0px; margin:0px;}\n\t\t</style>\n\t</head>\n  <body>\n    <p>\n<b><span style=\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\">HTML&#160;tekstas, ar tiks?</span><span style=\"color:#3333ff; font-family:Yu Gothic UI Light;\"> </span></b>\n\n    </p>\n\n    <p>\n<b><span style=\"color:#3333ff;\">&#160;</span></b>\n\n    </p>\n\n</body>\n</html>";
	public static final String DIAGRAM2 = "<html> <head> </head> <body><p> <img src=\"https://support.content.office.net/en-us/media/3ac1da3e-ab76-41a8-85ba-5e48752138db.png\" /></p></body></html>";
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
	public static final String _SVG2 = "<html><body>" +
									  "<svg height=\"30\" width=\"200\">\n" +
									  "  <text x=\"0\" y=\"15\" fill=\"red\">I love SVG!</text>\n" +
									  "</svg>" +
									  "</body></html>";
	public static void main(String[] args) throws IOException
	{
		new Html2Pdf().generatePdf();
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

		try {
			String dest = "Test_Itext.pdf";
			HtmlConverter.convertToPdf(renderedHtmlContent, new FileOutputStream(dest));
//			return new InputStreamResource(new FileInputStream(dest));

		} catch (IOException e) {
			log.error(e.getMessage());
		}
		long finish =System.currentTimeMillis();
		long timeElapsed = getTimeElapsed(start, finish);
		log.info("############### END RENDERING #####################");
		log.info(timeElapsed + "min");
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
			listData.add(new TableDto("PNG", DIAGRAM2));
			listData.add(new TableDto("_SVG", _SVG));
			listData.add(new TableDto("_SVG2", _SVG2));
			listData.add(new TableDto("DIAGRAM_SVG", DIAGRAM_SVG));
			listData.add(new TableDto("BASE_64", BASE_64));

		}
		log.info("############### END POPULATE #########################");

	}
	private long getTimeElapsed(long start, long finish)
	{
		return ((finish - start) / 1000) / 60;
	}
}