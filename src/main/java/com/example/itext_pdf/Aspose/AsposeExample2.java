package com.example.itext_pdf.Aspose;

import com.aspose.pdf.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AsposeExample2
{
	public static void main(String[] args)
	{
		renderHTMLwithSVGData();
	}
	public static void renderHTMLwithSVGData()
	{
		String file = "Periodic_table_large.svg";
		com.aspose.pdf.LoadOptions options = new com.aspose.pdf.SvgLoadOptions();
		com.aspose.pdf.Document doc = new Document(file, options);
		doc.save("AsposeSVG.pdf");
	}
}