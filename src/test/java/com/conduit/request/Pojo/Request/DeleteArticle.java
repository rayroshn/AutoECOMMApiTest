package com.conduit.request.Pojo.Request;

import com.conduit.Constants.FilePaths;
import com.conduit.util.CsvReaderUtill;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteArticle {

    String commentCsvFilePath= FilePaths.COMMENT_USER_DATA.getPath();
    private String slug;


    // Method to return the slug (e.g., "Chris-4180")
    public String getSlug() throws IOException {
        return this.slug =CsvReaderUtill.readCsvData(commentCsvFilePath).get(1).get("slug");
    }

    public void setSlug() throws IOException {
        this.slug = CsvReaderUtill.readCsvData(commentCsvFilePath).get(1).get("slug");
    }

    @Test
    public void slugData() throws IOException {
        System.out.println("slug = " + getSlug());

    }
}

