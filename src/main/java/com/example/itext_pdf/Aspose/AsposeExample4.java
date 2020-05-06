package com.example.itext_pdf.Aspose;

import com.aspose.pdf.Document;
import com.aspose.pdf.FontRepository;
import com.aspose.pdf.HtmlLoadOptions;
import com.aspose.pdf.MarginInfo;
import com.aspose.pdf.text.FontSourceCollection;
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
//		String file = "aspose.html";
		String file = "bigTable.html";

		HtmlLoadOptions options = new HtmlLoadOptions(file);

//		options.getPageInfo().setHeight(PageSize.getA4().getHeight());//PageSize.getA4().getHeight()); //1050
//		options.getPageInfo().setWidth(PageSize.getA4().getWidth());//PageSize.getA4().getWidth());  // 800
		options.setRenderToSinglePage(true);
		options.getPageInfo().setMargin(new MarginInfo(0, 0, 0, 0));
		options.setInputEncoding("UTF-8");
//		options.setHtmlMediaType(HtmlMediaType.Print);

		//FontSettings.getAllFonts()
		FontSourceCollection sources = FontRepository.getSources();
		Document document = new Document(file, options);
		document.setIgnoreCorruptedObjects(true);
		document.setFitWindow(true);
		document.setLayersAdded(true);
//		doc.setCenterWindow(true);

//		double w = 0;
//		double h = 0;
//		for(Page page : doc.getPages())
//		{
////			w = page.getRect().getWidth();
////			h = page.getRect().getHeight();
////			page.setMediaBox(new Rectangle(0, 0, h * h / w, h));
////			page.setCropBox(new Rectangle(0, 0, h * h / w, h));
//		}
		document.save("bigTableAspose.pdf");






/*		// Instantiate Document object
		Document doc = new Document();
// Add a page to pages collection of PDF file
		Page page = doc.Pages.Add();
// Instantiate HtmlFragment with HTML contnets
		HtmlFragment titel = new HtmlFragment("<fontsize=10><b><i>Table</i></b></fontsize>");
// Set bottom margin information
		titel.Margin.Bottom = 10;
// Set top margin information
		titel.Margin.Top = 200;
// Add HTML Fragment to paragraphs collection of page
		page.Paragraphs.Add(titel);

		dataDir = dataDir + "AddHTMLUsingDOM_out.pdf";
// Save PDF file
		doc.Save(dataDir);*/
	}

}
