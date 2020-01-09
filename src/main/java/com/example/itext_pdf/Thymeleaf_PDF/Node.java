package com.example.itext_pdf.Thymeleaf_PDF;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Node {
	public String description;
	public List<Node> children;
}