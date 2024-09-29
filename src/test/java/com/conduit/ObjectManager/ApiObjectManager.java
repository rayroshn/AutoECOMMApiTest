package com.conduit.ObjectManager;

import com.conduit.request.Pojo.Request.Article;
import com.conduit.request.Pojo.Request.CommentPayloadRequest;
import com.conduit.request.Pojo.Request.NewArticlePayload;

public class ApiObjectManager {

    public ApiObjectManager() {

    }

    public static   CommentPayloadRequest getCommnentApiObject()
    {
        return new CommentPayloadRequest();
    }


    public static   NewArticlePayload getArticleApiObject()
    {
        return new  NewArticlePayload();
    }

    public static   NewArticlePayload getArticleApiObject(Article article)
    {
        return new  NewArticlePayload();
    }


}
