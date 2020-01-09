
package com.example.itext_pdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Row2 {

    private String commentTargetId;
    private String elementUUID;
    private String fieldName;
    private String iconId;
    private List<Navigation> navigation;
    private String text;
    private String type;
    private String number;

}
