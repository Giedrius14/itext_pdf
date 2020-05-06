package com.example.itext_pdf.Aspose;

import com.aspose.pdf.ColumnAdjustment;
import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AsposeTable
{
	public static void main(String[] args)
	{
		renderHTMLwithSVGData();
	}
	public static void renderHTMLwithSVGData()
	{
		Document doc = new Document();
//Create the section in the PDF object
		Page page = doc.getPages().add();

//Instantiate a table object
		Table tab = new Table();
//Add the table in paragraphs collection of the desired section
		page.getParagraphs().add(tab);

//Set with column widths of the table
//		tab.setColumnWidths("50 50 50");
		tab.setColumnAdjustment(ColumnAdjustment.AutoFitToWindow);
		tab.setDefaultColumnWidth("70");
//Set default cell border using BorderInfo object
		tab.setDefaultCellBorder(new com.aspose.pdf.BorderInfo(com.aspose.pdf.BorderSide.All, 0.1F));

//Set table border using another customized BorderInfo object
		tab.setBorder(new com.aspose.pdf.BorderInfo(com.aspose.pdf.BorderSide.All, 1F));
//Create MarginInfo object and set its left, bottom, right and top margins
		com.aspose.pdf.MarginInfo margin = new com.aspose.pdf.MarginInfo();
		margin.setTop(5f);
		margin.setLeft(5f);
		margin.setRight(5f);
		margin.setBottom(5f);

//Set the default cell padding to the MarginInfo object
		tab.setDefaultCellPadding(margin);

//Create rows in the table and then cells in the rows
		com.aspose.pdf.Row row1 = tab.getRows().add();
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard14");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard");
		row1.getCells().add("Hazard20");

		com.aspose.pdf.Row row2 = tab.getRows().add();
		row2.getCells().add("B Electromagnetic energy(ESD) item1");
		row2.getCells().add("B Electromagnetic energy(ESD) item2");
		row2.getCells().add("B Electromagnetic energy(ESD) item3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) 14");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) col3");
		row2.getCells().add("B Electromagnetic energy(ESD) 20");


//Save the PDF
		doc.save( "Table.pdf");
	}
}
