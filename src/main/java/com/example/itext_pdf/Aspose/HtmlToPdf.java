package com.example.itext_pdf.Aspose;

import com.aspose.html.HTMLDocument;
import com.aspose.html.rendering.HtmlRenderer;
import com.aspose.html.rendering.pdf.PdfDevice;
import com.aspose.html.rendering.pdf.PdfRenderingOptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class HtmlToPdf
{
	public static void main(String[] args) throws FileNotFoundException
	{
//		com.aspose.pdf.internal.html.HTMLDocument html = new com.aspose.pdf.internal.html.HTMLDocument("diagramSVG.html"); // diagramSVG.html  Diagram.html
//		com.aspose.pdf.internal.html.rendering.HtmlRenderer renderer2 = new com.aspose.pdf.internal.html.rendering.HtmlRenderer();
//		java.io.OutputStream fos2 = new FileOutputStream("fromFile.pdf");
//		renderer2.render(new com.aspose.pdf.internal.html.rendering.pdf.PdfDevice(new com.aspose.pdf.internal.html.rendering.pdf.PdfRenderingOptions(), fos2), html);
		// File name for resultant PDF file
//		String Resultant_output = "simple-any-page_out.pdf"; //StringExtensions.concat(dataDir, "simple-any-page_out.pdf");
		java.io.OutputStream fos = new FileOutputStream("NEW.pdf");

		String InputHtml = "fullDoc.html"; //StringExtensions.concat(dataDir, "test.html");
/*		FileStream fs = File.create(InputHtml);
		try
		{
			StreamWriter sw = new StreamWriter(fs);
			try
			{
				sw.write(
						"<style>\n                    .st\n                    {\n                    color: green;\n                    }\n                    </style>\n                    <div id=id1>com.aspose.html rendering Text in Black Color</div>\n                    <div id=id2 class=''st''>com.aspose.html rendering Text in Green Color</div><div id=id3 class=''st'' style='color: blue;'>com.aspose.html rendering Text in Blue Color</div>\n                    <div id=id3 class=''st'' style='color: red;'><font face='Arial'>com.aspose.html rendering Text in Red Color</font></div>");
			}
			finally
			{
                if(sw != null)
                {
                    sw.dispose();
                }
			}
		}
		finally
		{
            if(fs != null)
            {
                fs.dispose();
            }
		}*/

		// Create PdfRendering Options object
		PdfRenderingOptions pdf_options = new PdfRenderingOptions();
		// The PageSetup also provides different properties i.e. FirstPage, LastPage, LeftPage, RightPage and they are used to setup (PageSize, Margin) for every page.
		// In most cases, usage of setup any page is enough, but in some complicated cases, you may need to fine tune page settings. It can be done either by CSS styles or by using rendering options.
		// the size for drawing is in pixels
//		pdf_options.getPageSetup().setAnyPage(new Page(new Size(400, 100)));
		// Instantiate PdfDevice object while passing PdfRenderingOptions and resultant file path as arguments
		PdfDevice pdf_device = new PdfDevice(pdf_options, fos);//Resultant_output);
		try
		{
			// Create HtmlRenderer object
			HtmlRenderer renderer = new HtmlRenderer();
			try
			{
				// Create HtmlDocument instance while passing path of already created HTML file
				HTMLDocument html_document = new HTMLDocument(InputHtml);
				try
				{
					// Render the output using HtmlRenderer
					renderer.render(pdf_device, html_document);
				}
				finally
				{
                    if(html_document != null)
                    {
                        html_document.dispose();
                    }
				}
			}
			finally
			{
                if(renderer != null)
                {
                    renderer.dispose();
                }
			}
		}
		finally
		{
			if(pdf_device != null) pdf_device.dispose();
		}
		// ExEnd:HtmlToPdf
	}
}
