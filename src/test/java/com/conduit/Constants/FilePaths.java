package com.conduit.Constants;

import java.io.File;

public enum FilePaths {


  //  private final String projectRootDir = System.getProperty("user.dir");
 
 //+ File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "logi
    LOGIN_USER_DATA(File.separator+"src" + File.separator + "test" + File.separator + "resources" + File.separator + "loginUserData.csv")
   , COMMENT_USER_DATA(File.separator+"src" + File.separator + "test" + File.separator + "resources" + File.separator +"commentData.csv");

    String path;
    FilePaths(String path) {

        this.path = System.getProperty("user.dir") + "/" + path;
    }

    public String getPath()
    {

        return path;
    }
}
