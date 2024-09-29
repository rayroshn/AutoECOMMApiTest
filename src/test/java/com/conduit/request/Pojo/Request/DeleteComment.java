package com.conduit.request.Pojo.Request;

import com.conduit.Constants.FilePaths;
import com.conduit.util.CsvReaderUtill;

import java.io.IOException;

public class DeleteComment {
    private String slug;
    private Integer commentId;  // Assuming the comment ID is returned and it's an integer
    public static Integer getcommentId;
    String commentCsvFilePath = FilePaths.COMMENT_USER_DATA.getPath();

    // Getters and Setters

    public String getCommentId() throws IOException {
      //  return this.commentId = CsvReaderUtill.readCsvData(commentCsvFilePath).get(1).get("commentID");
        //this.commentId =String.valueOf(getcommentId);
        return String.valueOf(this.getcommentId);



    }

    public void setCommentId(Integer Id) {
        this.commentId = commentId;
    }

    public String getSlug() throws IOException {
        return this.slug =CsvReaderUtill.readCsvData(commentCsvFilePath).get(1).get("slug");
    }


}
