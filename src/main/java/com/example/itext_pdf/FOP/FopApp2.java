package com.example.itext_pdf.FOP;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class FopApp2
{

	public static void main(String[] args)
	{
		FileInputStream input = null;
		try
		{
			// open file
			//if (args.length != 2) {
			//   System.out.println("Usage: Html2Pdf htmlFile styleSheet");
			//  System.exit(1);
			//}
			//String htmlFileName = args[0];
			String htmlFileName = "C:/fop-1.0/examples/html2pdf/hello.html";
			//try {
			input = new FileInputStream(htmlFileName);
			Tidy tidy = new Tidy();
			//Document xmlDoc = tidy.parseDOM(input, null);
			Document xmlDoc = tidy.parseDOM(input, null);
			String stylesheet = "C:/fop-1.0/examples/html2pdf/xhtml2fo.xsl";
			Document foDoc = xml2FO(xmlDoc, stylesheet);
			String pdfFileName = htmlFileName.substring(0, htmlFileName.indexOf(".")) + ".pdf";
			try
			{
				OutputStream pdf = new FileOutputStream(new File(pdfFileName));
				pdf.write(fo2PDF(foDoc));
			}
			catch(java.io.FileNotFoundException e)
			{
				System.out.println("Error creating PDF: " + pdfFileName);
			}
			catch(java.io.IOException e)
			{
				System.out.println("Error writing PDF: " + pdfFileName);
			}

		}

		catch(FileNotFoundException ex)
		{
			log.error(ex.getMessage());
		}
		finally
		{
			try
			{
				input.close();
			}
			catch(IOException ex)
			{
				log.error(ex.getMessage());
			}
		}

	}

	/*
	 *  Applies stylesheet to input.
	 *
	 *  @param xml  The xml input Document
	 *
	 *  @param stylesheet Name of the stylesheet
	 *
	 *  @return Document  Result of the transform
	 */
	private static Document xml2FO(Document xml, String styleSheet)
	{

		DOMSource xmlDomSource = new DOMSource(xml);
		DOMResult domResult = new DOMResult();

		Transformer transformer = getTransformer(styleSheet);
		if(transformer == null)
		{
			System.out.println("Error creating transformer for " + styleSheet);
			System.exit(1);
		}
		try
		{
			transformer.transform(xmlDomSource, domResult);
		}
		catch(javax.xml.transform.TransformerException e)
		{
			return null;
		}
		return (Document) domResult.getNode();

	}

	/*
	 *  Apply FOP to XSL-FO input
	 *
	 *  @param foDocument  The XSL-FO input
	 *
	 *  @return byte[]  PDF result
	 */
	private static byte[] fo2PDF(Document foDocument)
	{

//		DocumentInputSource fopInputSource = new DocumentInputSource(foDocument);

		try
		{

			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			Logger log = new ConsoleLogger(ConsoleLogger.LEVEL_WARN);

//			Driver driver = new Driver(fopInputSource, out);
//			driver.setLogger(log);
//			driver.setRenderer(RENDER_PDF);
//			driver.run();

			return out.toByteArray();

		}
		catch(Exception ex)
		{
			return null;
		}
	}

	/*
	 *  Create and return a Transformer for the specified stylesheet.
	 *
	 *  Based on the DOM2DOM.java example in the Xalan distribution.
	 */
	private static Transformer getTransformer(String styleSheet)
	{

		try
		{

			TransformerFactory tFactory = TransformerFactory.newInstance();

			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();

			dFactory.setNamespaceAware(true);

			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			Document xslDoc = dBuilder.parse(styleSheet);
			DOMSource xslDomSource = new DOMSource(xslDoc);

			return tFactory.newTransformer(xslDomSource);

		}
		catch(javax.xml.transform.TransformerException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(java.io.IOException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(javax.xml.parsers.ParserConfigurationException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(org.xml.sax.SAXException e)
		{
			e.printStackTrace();
			return null;
		}

	}
}
