package com.example.itext_pdf.itext7;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class itext7App
{
	public static void main(String[] args) throws IOException
	{

		File htmlSource = new File("full_with_svg.html");
		File pdfDest = new File("itext7.pdf");
		// pdfHTML specific code
		ConverterProperties converterProperties = new ConverterProperties();
		converterProperties.setBaseUri("");
		HtmlConverter.convertToPdf(new FileInputStream(htmlSource),
								   new FileOutputStream(pdfDest), converterProperties);
	}
}
