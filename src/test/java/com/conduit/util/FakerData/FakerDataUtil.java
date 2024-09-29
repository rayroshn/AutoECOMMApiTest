package com.conduit.util.FakerData;

import com.github.javafaker.Faker;
import groovy.transform.Final;
import jdk.dynalink.beans.StaticClass;

import java.util.List;

public class FakerDataUtil {

    static Faker faker= new Faker();

    public static String getCommentBodyData()
    {
       return faker.lorem().sentence().trim();
    }
     public static final String GET_COMMENT_DATA=faker.lorem().sentence().trim();
    public static final List<String> GET_DESCRIPTION_DATA=faker.lorem().paragraphs(3);
}
