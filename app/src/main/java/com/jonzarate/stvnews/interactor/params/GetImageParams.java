package com.jonzarate.stvnews.interactor.params;

import com.jonzarate.stvnews.data.model.Article;

public class GetImageParams extends BaseParams {

    Article article;

    public GetImageParams (Article article){
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
