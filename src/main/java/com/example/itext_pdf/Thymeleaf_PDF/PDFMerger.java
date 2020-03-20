//package com.example.itext_pdf.Thymeleaf_PDF;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfCopy;
//import com.itextpdf.text.pdf.PdfImportedPage;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfSmartCopy;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class PDFMerger {
//
//	static void mergePdfFiles(List<InputStream> inputPdfList,
//							  OutputStream outputStream) throws Exception{
//		//Create document and pdfReader objects.
//		Document document = new Document();
//		document.setPageSize(PageSize.A0);
//		List<PdfReader> readers = new ArrayList<>();
//		int totalPages = 0;
//
//		//Create pdf Iterator object using inputPdfList.
//		Iterator<InputStream> pdfIterator = inputPdfList.iterator();
//
//		// Create reader list for the input pdf files.
//		while (pdfIterator.hasNext()) {
//			InputStream pdf = pdfIterator.next();
//			PdfReader pdfReader = new PdfReader(pdf);
//			readers.add(pdfReader);
//			totalPages = totalPages + pdfReader.getNumberOfPages();
//		}
//
//		// Create writer for the outputStream
//		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
//
//		//Open document.
//		document.open();
//
//		//Contain the pdf data.
//		PdfContentByte pageContentByte = writer.getDirectContent();
//
//		PdfImportedPage pdfImportedPage;
//		int currentPdfReaderPage = 1;
//		Iterator<PdfReader> iteratorPDFReader = readers.iterator();
//
//		// Iterate and process the reader list.
//		while (iteratorPDFReader.hasNext()) {
//			PdfReader pdfReader = iteratorPDFReader.next();
//			//Create page and add content.
//			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
//				document.newPage();
//				pdfImportedPage = writer.getImportedPage(
//						pdfReader,currentPdfReaderPage);
//				pageContentByte.addTemplate(pdfImportedPage, 0, 0);
//				currentPdfReaderPage++;
//			}
//			currentPdfReaderPage = 1;
//		}
//
//		//Close document and outputStream.
//		outputStream.flush();
//		document.close();
//		outputStream.close();
//
//		System.out.println("Pdf files merged successfully.");
//	}
//
//	public static void main(String args[]){
//		try {
//			//Prepare input pdf file list as list of input stream.
//			List<FileInputStream> inputPdfList = new ArrayList<>();
//			inputPdfList.add(new FileInputStream("MergeFile_Big1.pdf"));
//			inputPdfList.add(new FileInputStream("MergeFile_Big1.pdf"));
//
//
//			//Prepare output stream for merged pdf file.
//			OutputStream outputStream = new FileOutputStream("MergeBigTest.pdf");
//
//			//call method to merge pdf files.
////			mergePdfFiles(inputPdfList, outputStream);
//			mergePdf(inputPdfList, outputStream);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void mergePdf(List<FileInputStream> listOfPdfFiles, OutputStream outputStream) throws DocumentException, IOException
//	{
//		Document document = new Document();
//		PdfCopy copy = new PdfSmartCopy(document, outputStream);
//		document.open();
//
//		// Create reader list for the input pdf files.
//		for(FileInputStream pdf : listOfPdfFiles)
//		{
//			PdfReader reader = new PdfReader(pdf);
//			copy.addDocument(reader);
//			copy.freeReader(reader);
//			reader.close();
//		}
//		document.close();
//	}
//}