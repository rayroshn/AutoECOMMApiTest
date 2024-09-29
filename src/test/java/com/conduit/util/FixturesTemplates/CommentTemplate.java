package com.conduit.util.FixturesTemplates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.conduit.request.Pojo.Request.Comment;
import com.conduit.request.Pojo.Request.CommentFactory;
import com.conduit.util.FakerData.FakerDataUtil;
import com.github.javafaker.Faker;

public class CommentTemplate implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(Comment.class).addTemplate("xyz",new Rule(){{
          add("body", FakerDataUtil.GET_COMMENT_DATA);

        }});
    }
}
