package com.example.itext_pdf.Aspose;

import com.aspose.pdf.Document;
import com.aspose.pdf.Heading;
import com.aspose.pdf.HorizontalAlignment;
import com.aspose.pdf.License;
import com.aspose.pdf.Page;
import com.aspose.pdf.PageSize;
import com.aspose.pdf.Rectangle;
import com.aspose.pdf.Rotation;
import com.aspose.pdf.TextFragmentAbsorber;
import com.aspose.pdf.TextSegment;
import com.aspose.pdf.TextStamp;
import com.aspose.pdf.TocInfo;
import com.aspose.pdf.VerticalAlignment;
import com.aspose.pdf.facades.PdfPageEditor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TableOfContents
{
	public static void main(String[] args) {
		loadAsposeLicense();
		// Load an existing PDF files
		Document document = new Document("document_Automotive_FMEA.pdf");
		document.processParagraphs();
		Page tocPage = document.getPages().insert(1);
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
			document.getPages().accept(absorber);
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

		document.processParagraphs();

		TextStamp documentName = new TextStamp("Header 3");

		TextStamp pagination = new TextStamp("");
		pagination.setVerticalAlignment(VerticalAlignment.Bottom);
		pagination.setHorizontalAlignment(HorizontalAlignment.Center);
		pagination.getTextState().setFontSize(12);
		pagination.setBottomMargin(10);
		pagination.setRightMargin(10);

		String timeStamp = Instant.ofEpochMilli(new Date().getTime())
				.atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH));
		TextStamp date = new TextStamp(timeStamp);
		date.setVerticalAlignment(VerticalAlignment.Bottom);
		date.setHorizontalAlignment(HorizontalAlignment.Right);
		date.getTextState().setFontSize(12);
		date.setBottomMargin(10);
		date.setRightMargin(10);


		for(Page page: document.getPages())
		{
			pagination.setValue(String.valueOf(page.getNumber()) + '/' + document.getPages().size()); //`Page ${page.Number} of {pdfDoc.Pages.Count}`
			page.addStamp(pagination);
			page.addStamp(date);


//			page.setHeader(new HeaderFooter());
//			page.getHeader().getParagraphs().add(new HtmlFragment("<h1>Header</h1><br/><br/><p>header content</p>"));
//			page.setFooter(new HeaderFooter());
//			page.getFooter().getParagraphs().add(new HtmlFragment("<h1>Footer</h1><br/><br/><p>footer content</p>"));


		}
//		document.UpdatePageLayout();
//		document.updatePages();

		Document doc = rotateZoomDocument(document);

		doc.save("TOC_Output_Java2.pdf");
	}

	private static Document rotateZoomDocument(Document document)
	{
		PdfPageEditor pdfPageEditor = new PdfPageEditor(document);

		for (int i = 1; i <= document.getPages().size(); i++)
		{
			Page page = document.getPages().get_Item(i);
			float pageWidth = (float)page.getRect().getWidth();
			float pageHeight = (float)page.getRect().getHeight();
			PageSize pageLetter = PageSize.getPageLetter();

			if (pageWidth == pageLetter.getWidth() && pageHeight == pageLetter.getHeight())
				continue;
			float wScale = pageLetter.getWidth() / pageWidth;
			float hScale = pageLetter.getHeight() / pageHeight;
//			scale = new float[] { 1f, wScale, hScale };// .Min();
			Float scale = Collections.min(Arrays.asList(1f, wScale, hScale));

			pdfPageEditor.setProcessPages(new int[]{i});
			pdfPageEditor.setPageSize(pageLetter);
			pdfPageEditor.setVerticalAlignmentType(VerticalAlignment.Top);
			pdfPageEditor.setHorizontalAlignment(HorizontalAlignment.Left);
			pdfPageEditor.setZoom(scale);
			document.getPages().get_Item(i).setRotate(Rotation.on90);
			pdfPageEditor.applyChanges();
		}
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		pdfPageEditor.save(stream);
		Document doc = new Document(new ByteArrayInputStream(stream.toByteArray()));

		for (Page page : doc.getPages())
		{
			Rectangle r = page.getMediaBox();
			double newHeight = r.getWidth();
			double newWidth = r.getHeight();
			double newLLX = r.getLLX();
			//  We must to move page upper in order to compensate changing page size
			// (lower edge of the page is 0,0 and information is usually placed from the
			//  Top of the page. That's why we move lover edge upper on difference between
			//  Old and new height.
			double newLLY = r.getLLY() + (r.getHeight() - newHeight);
			page.setMediaBox(new Rectangle(newLLX, newLLY, newLLX + newWidth, newLLY + newHeight));
			// Sometimes we also need to set CropBox (if it was set in original file)
			page.setCropBox(new Rectangle(newLLX, newLLY, newLLX + newWidth, newLLY + newHeight));

			// Setting Rotation angle of page
			//page.Rotate = Rotation.on90;
		}
		return doc;
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
