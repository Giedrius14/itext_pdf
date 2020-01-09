
package com.example.itext_pdf.model;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class PresentationElement {

    private List<Column> columns;
    private String commentTargetId;
    private Boolean hideHeader;
    private String id;
    private long rowCount;
    private List<List<Row2>> rows;
    private String title;
    private String type;
    private String viewId;

}
