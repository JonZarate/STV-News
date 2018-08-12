package com.jonzarate.stvnews.interactor.params;

public class GetArticleByIdParams extends BaseParams {

    int articleId;

    public GetArticleByIdParams (int articleId) {
        this.articleId = articleId;
    }

    public int getArticleId() {
        return articleId;
    }
}
