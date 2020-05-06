package com.example.itext_pdf.Aspose;

import com.aspose.pdf.Document;
import com.aspose.pdf.Heading;
import com.aspose.pdf.License;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragmentAbsorber;
import com.aspose.pdf.TextSegment;
import com.aspose.pdf.TocInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TableOfContents
{
	public static void main(String[] args) {
		loadAsposeLicense();
		// Load an existing PDF files
		Document doc = new Document("AddTOCToExistingPDF.pdf");
		doc.processParagraphs();
		Page tocPage = doc.getPages().insert(1);
		TocInfo tocInfo = new TocInfo();
		tocPage.setTocInfo(tocInfo);
// allHeadings is a List<string> which contains all heading from HTML
		List<String> allHeadings = new ArrayList<>();
		allHeadings.add("1 withPropertiesaq");
		allHeadings.add("2 literal");
		allHeadings.add("3 opaque1");
		allHeadings.add("4 stereotype");


		for (int i = 0; i < allHeadings.size(); i++)
		{
			TextFragmentAbsorber absorber = new TextFragmentAbsorber(allHeadings.get(i));
			doc.getPages().accept(absorber);
			if(absorber.getTextFragments().size() > 0)
			{
				Heading heading2 = new Heading(1+ i);
				TextSegment segment2 = new TextSegment();
				heading2.setTocPage(tocPage);
				heading2.getSegments().add(segment2);
				heading2.setDestinationPage(absorber.getTextFragments().get_Item(1).getPage());
				heading2.setTop(absorber.getTextFragments().get_Item(1).getPage().getRect().getHeight());
				segment2.setText(absorber.getTextFragments().get_Item(1).getText());
				tocPage.getParagraphs().add(heading2);
			}
		}
		doc.save("TOC_Output_Java2.pdf");
	}

	private static void loadAsposeLicense()
	{
		InputStream licenseStream = TableOfContents.class.getClassLoader().getResourceAsStream("Aspose.Pdf.lic");
		try(licenseStream)
		{
			License license = new License();
			license.setLicense(licenseStream);
		}
		catch(Exception e)
		{
//			LOGGER.error("Aspose license not found: "+e.getMessage());
		}
	}
}
