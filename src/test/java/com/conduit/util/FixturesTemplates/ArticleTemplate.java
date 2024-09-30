package com.conduit.util.FixturesTemplates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.conduit.request.Pojo.Request.Article;
import com.conduit.util.FakerData.ArticleFakerData;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ArticleTemplate implements TemplateLoader {




    private Object[] getFirstNames(){
        Faker faker= new Faker();
        List<String> list= new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(faker.name().firstName() + faker.name().lastName()+faker.name().title());
        }
        return list.toArray();
    }

    private  Object[] generaUniqueTitles() {
        Faker faker = new Faker();
        List<String> titlesList= new ArrayList<>();
        for(int i1 = 0; i1<=10;i1++)
        {
            titlesList.add(faker.book().title());
        }
        return  titlesList.toArray();
    }

   /* private  String randomTitle() {
        // Generate a unique title by appending timestamp
        return faker.book().title() + " validArticleTemplate " + System.nanoTime();
    }*/

    @Override
    public void load() {


        Fixture.of(Article.class).addTemplate("validArticle", new Rule() {{
            add("title", uniqueRandom(getFirstNames()));
            add("description", ArticleFakerData.GET_ARTICLE_DESC_DATA);
            add("body", ArticleFakerData.GET_ARTICLE_BODY_DATA);
            add("tagList", Arrays.asList("esse", "voluptatem"));
        }


        });

        Fixture.of(com.conduit.request.Pojo.Request.UpdateArticle.Article.class).addTemplate("updateArticle",new Rule(){{

            add("body",ArticleFakerData.GET_ARTICLE_BODY_DATA);

        }});

        // Article Schema validation fixture

        Fixture.of(Article.class).addTemplate("validArticleSchema").inherits("validArticle",new Rule(){{
            add("title", ArticleFakerData.GET_ARTICLE_TITLE_DATA_SCHEMA);

        }});



    }
}
