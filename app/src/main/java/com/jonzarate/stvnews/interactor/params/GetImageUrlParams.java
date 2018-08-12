package com.jonzarate.stvnews.interactor.params;

import com.jonzarate.stvnews.data.model.Article;

public class GetImageUrlParams extends BaseParams {

    Article article;

    public GetImageUrlParams(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
