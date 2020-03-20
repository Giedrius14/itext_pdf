package com.example.itext_pdf.Aspose;

import com.aspose.pdf.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AsposeExample4
{
	public static void main(String[] args)
	{
		renderHTMLwithSVGData();
	}
	public static void renderHTMLwithSVGData()
	{
		String file = "Diagram.html";
		//String file = "diagramSVG.html";
//		String file = "Periodic_table_large.svg";
		com.aspose.pdf.LoadOptions options = new com.aspose.pdf.SvgLoadOptions();
		Document doc = new Document(file, options);
		doc.save("AsposeSVG.pdf");
	}

}