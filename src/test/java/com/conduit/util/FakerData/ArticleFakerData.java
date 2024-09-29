package com.conduit.util.FakerData;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleFakerData {

    static Faker faker = new Faker();

    public static final String GET_ARTICLE_DESC_DATA=  faker.lorem().sentence(5);
    public static final String GET_ARTICLE_TITLE_DATA=faker.book().title()+"validArticleTemplate"+ System.currentTimeMillis();;
    public static final String GET_ARTICLE_TITLE_DATA_SCHEMA=faker.book().title();
   // public static ThreadLocal<String> GET_threadLocalTitles =ThreadLocal.withInitial(() -> faker.book().title() + " validArticleTemplate " + System.currentTimeMillis());
    public static final String GET_ARTICLE_BODY_DATA= faker.lorem().paragraph();

   /* public static String getUniqueArticleTitles()
    {
        String title=faker.book().title()+"validArticleTemplate"+ System.currentTimeMillis();
        GET_threadLocalTitles.get().add(title);
        return title;
    }*/
/*

    public static String getUniqueArticleTitle()
    {

        return GET_threadLocalTitles.get();
    }
*/

}
