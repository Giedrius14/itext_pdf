/*
package com.example.itext_pdf;

import com.example.itext_pdf.model.PresentationElement;
import com.example.itext_pdf.model.Row2;
import com.google.gson.Gson;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootApplication
public class ItextPdfApplication
{

//	public static final String HTML = "<html><body> This is my Project <table width= '50%' border='0' align='left' cellpadding='0' cellspacing='0'><tr><td>{VERTICALTEXT}</td></tr></table></body></html>";
//	public static final String HTML = "<html>↵<body>↵    <p>↵paveiksliukas↵↵    </p>↵↵    <p>↵      ↵<img border=\"0\" src=\"mdp://4a3889ae-fe2a-401a-be01-db74e2f526fc\" />↵↵    </p>↵↵</body>↵</html>";
	public static final String HTML = "<html> <head> <style>p {padding:0px; margin:0px; color:blue} </style> </head> <body> <p>paveiksliukas </p> <p> <img border=\"0\" src=\"https://webstockreview.net/images/small-house-png-2.png\" /></p></body></html>";
	public static final String LOCATION = "./html_in_cell.pdf";//"results/xmlworker/html_in_cell.pdf";

	public static final String JSON = String.format("{\n    \"id\": \"3e5529ee-e9b0-43f8-b708-a77718ce1db9\",\n    \"type\": \"table\",\n    \"commentTargetId\": \"T_MD_8a7fbb42-d335-41de-9e26-c249bc3f0708\",\n    \"viewId\": \"Containment_DiagramMainImage__8a7fbb42-d335-41de-9e26-c249bc3f0708\",\n    \"hideHeader\": false,\n    \"rowCount\": 3,\n    \"title\": \"table\",\n    \"columns\": [\n      {\n        \"id\": \"ae042166-7126-4411-a4de-d36b1523daff\",\n        \"width\": 0.1,\n        \"text\": \"#\",\n        \"type\": \"flex\"\n      },\n      {\n        \"id\": \"37d79cef-62c9-4fb7-8089-829f8de7e66f\",\n        \"width\": 0.44,\n        \"text\": \"Name\",\n        \"type\": \"flex\"\n      },\n      {\n        \"id\": \"dc89c75a-130e-4a11-bcf7-8b595aa226be\",\n        \"width\": 0.44,\n        \"text\": \"Documentation\",\n        \"type\": \"flex\"\n      }\n    ],\n    \"rows\": [\n      [\n        {\n          \"number\": \"1\",\n          \"type\": \"numberParagraph\"\n        },\n        {\n          \"fieldName\": \"name\",\n          \"elementUUID\": \"5bc195b2-1487-482d-ab23-b06e2ca41831\",\n          \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n          \"text\": \"b1\",\n          \"type\": \"elementParagraph\",\n          \"navigation\": [\n            {\n              \"text\": \"b1\",\n              \"sectionId\": \"5bc195b2-1487-482d-ab23-b06e2ca41831\",\n              \"viewId\": \"3a287f76-9d4f-49d6-8890-dcbfdc688e44\",\n              \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n              \"viewName\": \"Model\",\n              \"type\": \"model\"\n            }\n          ],\n          \"commentTargetId\": \"C_-362082966_T_MD__5bc195b2-1487-482d-ab23-b06e2ca41831QPROP__Element__name\"\n        },\n        {\n          \"textType\": \"text/plain\",\n          \"text\": \"paprastas tekstas\",\n          \"type\": \"textParagraph\",\n          \"commentTargetId\": \"C_-362082966_T_MD__5bc195b2-1487-482d-ab23-b06e2ca41831QPROP__Element__documentation\"\n        }\n      ],\n      [\n        {\n          \"number\": \"2\",\n          \"type\": \"numberParagraph\"\n        },\n        {\n          \"fieldName\": \"name\",\n          \"elementUUID\": \"b252917e-c30d-49d6-9501-cbebe9effbff\",\n          \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n          \"text\": \"b2\",\n          \"type\": \"elementParagraph\",\n          \"navigation\": [\n            {\n              \"text\": \"b2\",\n              \"sectionId\": \"b252917e-c30d-49d6-9501-cbebe9effbff\",\n              \"viewId\": \"3a287f76-9d4f-49d6-8890-dcbfdc688e44\",\n              \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n              \"viewName\": \"Model\",\n              \"type\": \"model\"\n            }\n          ],\n          \"commentTargetId\": \"C_-362082966_T_MD__b252917e-c30d-49d6-9501-cbebe9effbffQPROP__Element__name\"\n        },\n        {\n          \"textType\": \"text/html\",\n          \"text\": \"<html>\\n  <head>\\n\\t\\t<style>\\n\\t\\t\\tp {padding:0px; margin:0px;}\\n\\t\\t</style>\\n\\t</head>\\n  <body>\\n    <p>\\n<b><span style=\\\"color:#3333ff; font-size:18pt; font-family:Yu Gothic UI Light;\\\">HTML&#160;tekstas, ar tiks?</span><span style=\\\"color:#3333ff; font-family:Yu Gothic UI Light;\\\"> </span></b>\\n\\n    </p>\\n\\n    <p>\\n<b><span style=\\\"color:#3333ff;\\\">&#160;</span></b>\\n\\n    </p>\\n\\n</body>\\n</html>\",\n          \"type\": \"textParagraph\",\n          \"commentTargetId\": \"C_-362082966_T_MD__b252917e-c30d-49d6-9501-cbebe9effbffQPROP__Element__documentation\"\n        }\n      ],\n      [\n        {\n          \"number\": \"3\",\n          \"type\": \"numberParagraph\"\n        },\n        {\n          \"fieldName\": \"name\",\n          \"elementUUID\": \"b61e15b1-716d-4dc0-9778-023070d3602c\",\n          \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n          \"text\": \"b3\",\n          \"type\": \"elementParagraph\",\n          \"navigation\": [\n            {\n              \"text\": \"b3\",\n              \"sectionId\": \"b61e15b1-716d-4dc0-9778-023070d3602c\",\n              \"viewId\": \"3a287f76-9d4f-49d6-8890-dcbfdc688e44\",\n              \"iconId\": \"6d1a0ce2-80b2-4439-b6f7-3d6d6fef1ca3\",\n              \"viewName\": \"Model\",\n              \"type\": \"model\"\n            }\n          ],\n          \"commentTargetId\": \"C_-362082966_T_MD__b61e15b1-716d-4dc0-9778-023070d3602cQPROP__Element__name\"\n        },\n        {\n          \"textType\": \"text/html\",\n          \"text\": \"<html>\\n  <head>\\n\\t\\t<style>\\n\\t\\t\\tp {padding:0px; margin:0px;}\\n\\t\\t</style>\\n\\t</head>\\n  <body>\\n    <p>\\npaveiksliukas\\n\\n    </p>\\n\\n    <p>\\n      \\n<img border=\\\"0\\\" src=\\\"mdp://4a3889ae-fe2a-401a-be01-db74e2f526fc\\\" />\\n\\n    </p>\\n\\n</body>\\n</html>\",\n          \"type\": \"textParagraph\",\n          \"commentTargetId\": \"C_-362082966_T_MD__b61e15b1-716d-4dc0-9778-023070d3602cQPROP__Element__documentation\"\n        }\n      ]\n    ]\n  }");


	public static void main(String[] args) throws IOException, DocumentException
	{

		File file = new File(LOCATION);
		file.getParentFile().mkdirs();
		Gson g = new Gson();
		PresentationElement presentationElement = g.fromJson(JSON, PresentationElement.class);
		new ItextPdfApplication().createPdf(LOCATION, presentationElement);
	}
	public void createPdf(String file, PresentationElement presentationElement) throws IOException, DocumentException
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(file));
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();

		PdfPTable table = new PdfPTable(presentationElement.getColumns().size());

		createHeader(table, presentationElement);
		createRow(presentationElement, table);

		document.add(table);
		document.close();
		log.info("Done");
	}

	private void createRow(PresentationElement presentationElement, PdfPTable table) throws IOException, BadElementException
	{
		for(List<Row2> row : presentationElement.getRows())
		{
			for(Row2 column : row)
			{
				if(column.getType().equals("numberParagraph"))
				{
					table.addCell(column.getNumber());
					continue;
				}
				if(column.getText().contains("<html>"))
				{

					PdfPCell cell = new PdfPCell();
					for(Element element : XMLWorkerHelper.parseToElementList(column.getText(), null))
					{
						cell.addElement(element);
					}

					table.addCell(cell);
					continue;
				}

				table.addCell(new PdfPCell());
			}
		}



		table.addCell("#");
		table.addCell("html su image:");
		PdfPCell cell = new PdfPCell();
//		String html = presentationElement.getRows().get(1).get(2).getText();
		for(Element element : XMLWorkerHelper.parseToElementList(HTML, null)) {
			cell.addElement(element);
		}
		table.addCell(cell);


		table.addCell("#");
		table.addCell("img:");
		Image image = Image.getInstance("testimage.jpg");
		table.addCell(new PdfPCell(image, true));
	}

	private void createHeader(PdfPTable table, PresentationElement presentationElement)
	{
//		table.addCell("text:"); // todo itext7 naudot addHeaderCell
		presentationElement.getColumns().forEach(column -> table.addCell(column.getText()));
	}
}
*/
