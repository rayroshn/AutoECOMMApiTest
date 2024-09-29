package com.conduit.util.FixturesTemplates;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.conduit.Factory.PojoFactory;
import com.conduit.request.Pojo.Request.Article;
import com.conduit.request.Pojo.Request.Comment;
import com.conduit.request.Pojo.Request.CommentFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataProvider {
    @BeforeClass
    public void loadCommentTemplate()
    {
        FixtureFactoryLoader.loadTemplates("com.conduit.util.FixturesTemplates");


    }


    @org.testng.annotations.DataProvider(name = "getComments")
    public  static Object[][] getComment()
     {
             return new Object[][]{
                     { PojoFactory.getObject("xyz", Comment.class)}

                };
     }

    @org.testng.annotations.DataProvider(name = "getArticleTemplate")
    public  static Object[][] getArticleTemplate()
    {
        return new Object[][]{
                { PojoFactory.getObject("validArticle", Article.class)}

        };
    }

    @org.testng.annotations.DataProvider(name = "getArticleTemplateSchema")
    public  static Object[][] getArticleTemplateSchema()
    {
        return new Object[][]{
                { PojoFactory.getObject("validArticleSchema", Article.class)}

        };
    }

    @org.testng.annotations.DataProvider(name = "updateArticleData")
    public static Object[][] getUpdateArticleTemplate()
    {
        return new Object[][]
                {
                        {
                            PojoFactory.getObject("updateArticle",com.conduit.request.Pojo.Request.UpdateArticle.Article.class)
                        }
                };
    }



    @Test(dataProvider = "getComment", dataProviderClass = DataProvider.class)
    public void testMethod(Comment comment) {
        System.out.println(comment); // Print the body of the Comment object
    }




}
