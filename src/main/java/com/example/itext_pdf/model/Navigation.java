
package com.example.itext_pdf.model;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Navigation {

    private String iconId;
    private String sectionId;
    private String text;
    private String type;
    private String viewId;
    private String viewName;

}
