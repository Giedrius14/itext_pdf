package com.example.itext_pdf.Aspose;

import com.aspose.pdf.internal.html.HTMLDocument;
import com.aspose.pdf.internal.html.rendering.HtmlRenderer;
import com.aspose.pdf.internal.html.rendering.pdf.PdfDevice;
import com.aspose.pdf.internal.html.rendering.pdf.PdfRenderingOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

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

		HTMLDocument html = new HTMLDocument("Diagram.html");
		HtmlRenderer renderer = new HtmlRenderer();
		java.io.OutputStream fos = new FileOutputStream("fromFile.pdf");
		renderer.render(new PdfDevice(new PdfRenderingOptions(), fos), html);
	}
	public static void renderFromString() throws FileNotFoundException
	{

		String initialString = HTML;
		InputStream inputStream = new ByteArrayInputStream(initialString.getBytes());
		HTMLDocument html = new HTMLDocument(inputStream, "");
//		Document html = new Document(inputStream, "");
		HtmlRenderer renderer = new HtmlRenderer();
		renderer.render(new PdfDevice(new PdfRenderingOptions(), new FileOutputStream("asposeFromString.pdf")), html);
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

}