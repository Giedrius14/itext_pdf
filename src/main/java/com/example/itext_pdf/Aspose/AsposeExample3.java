/*
package com.example.itext_pdf.Aspose;

import com.aspose.pdf.LoadOptions;
import com.aspose.pdf.internal.html.HTMLDocument;
import com.aspose.pdf.internal.html.rendering.HtmlRenderer;
import com.aspose.pdf.internal.html.rendering.pdf.PdfDevice;
import com.aspose.pdf.internal.html.rendering.pdf.PdfRenderingOptions;
import com.example.itext_pdf.Thymeleaf_PDF.TableDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.itext_pdf.Aspose.AsposeExample.getTemplateResolver;
import static com.example.itext_pdf.Thymeleaf_PDF.TreeTemplateApp.getTimeElapsed;
import static com.example.itext_pdf.pdfTron.PdfTronExample.populateData;

@Slf4j
@SpringBootApplication
public class AsposeExample3
{
	public static void main(String[] args) throws FileNotFoundException
	{
		renderHTMLwithSVGData();
//		renderFromString();
	}
	public static void renderHTMLwithSVGData() throws FileNotFoundException
	{
		//		com.aspose.pdf.LoadOptions options = new com.aspose.pdf.SvgLoadOptions();
//		com.aspose.pdf.Document doc = new Document("Diagram.html", options);

		HTMLDocument html = new HTMLDocument("Diagram.html"); // diagramSVG.html  Diagram.html
		HtmlRenderer renderer = new HtmlRenderer();
		java.io.OutputStream fos = new FileOutputStream("svgFromFile.pdf");
		renderer.render(new PdfDevice(new PdfRenderingOptions(), fos), html);
	}
	public void renderFromString() throws FileNotFoundException
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


		String initialString = renderedHtmlContent; // HTML;
		InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());

		HTMLDocument html = new HTMLDocument(inputStream, "");
//		Document html = new Document(inputStream, "");
		HtmlRenderer renderer = new HtmlRenderer();
		PdfRenderingOptions pdfRenderingOptions = new PdfRenderingOptions();
		renderer.render(new PdfDevice(pdfRenderingOptions, new FileOutputStream("asposeFromString.pdf")), html);

		long finish =System.currentTimeMillis();
		long timeElapsed = getTimeElapsed(start, finish);
		log.info("############### END RENDERING #####################");
		log.info(timeElapsed + "min");

	}
	private static LoadOptions.ResourceLoadingResult SamePictureLoader(String resourceURI)
	{
//		string dataDir = RunExamples.GetDataDir_AsposePdf_DocumentConversion();
//		byte[] resultBytes = File.readAllBytes(dataDir + "aspose-logo.jpg");
//		LoadOptions.ResourceLoadingResult result = new LoadOptions.ResourceLoadingResult(resultBytes);
		return null;
	}
	public static String HTML = "<!DOCTYPE html>\n" +
								"<html lang=\"en\"\n" +
								"\t  xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
								"\t  xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\"\n" +
								">\n" +
								"<head>\n" +
								"\t<title></title>\n" +
								"\t<meta charset=\"utf-8\"/>\n" +
								"\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n" +
								"</head>\n" +
								"<body>\n" +
								"\t\t<h3>Intro</h3>\n" +
								"\t</div>\n" +
								"\t<div class=\"diagram-body\">\n" +
								"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
								"\t</div>\n" +
								"</body>\n" +
								"</html>";

}*/
