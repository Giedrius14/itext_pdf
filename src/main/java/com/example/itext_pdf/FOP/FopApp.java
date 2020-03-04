package com.example.itext_pdf.FOP;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class FopApp
{
	public static void main(String[] args) {
		File exampleSVGFile = new File("Periodic_table_large.svg");
		File exampleOutputFile = new File("FOPexample.pdf");
		SvgToPdfConverter converter = new SvgToPdfConverter();
		try {
			converter.convertSVG2PDF(exampleSVGFile);
			converter.convertSVG2PDF(exampleSVGFile);
			converter.convertSVG2PDF(exampleSVGFile);
		} catch (Exception e) {
			log.error("Converting svg failed. ", e);
		}
		try {
			converter.merge(exampleOutputFile);
		} catch (Exception e) {
			log.error("Merge failed. ", e);
		}
	}
}
