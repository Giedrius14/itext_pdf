package com.example.itext_pdf.FOP;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.fop.svg.PDFTranscoder;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SvgToPdfConverter
{
	private ArrayList<byte[]> pdfs = new ArrayList<byte[]>();

	public void convertSVG2PDF(File svg) throws IOException, TranscoderException
	{
		// Create transcoder
		Transcoder transcoder = new PDFTranscoder();
		// Input
		FileInputStream bais = new FileInputStream(svg);
		// Output
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TranscoderInput input = new TranscoderInput(bais);
		TranscoderOutput output = new TranscoderOutput(baos);
		// Do the transformation
		transcoder.transcode(input, output);
		pdfs.add(baos.toByteArray());
	}

	// TODO PDF box
	public void merge(File mergedPDF) throws IOException {
		PDFMergerUtility ut = new PDFMergerUtility();
		for (byte[] bytes : pdfs) {
			ut.addSource(new ByteArrayInputStream(bytes));
		}
		ut.setDestinationStream(new FileOutputStream(mergedPDF));
		ut.mergeDocuments();
	}
}
