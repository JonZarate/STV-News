package com.jonzarate.stvnews.interactor.callback;

import com.jonzarate.stvnews.data.model.Article;

public interface GetArticleByIdCallbacks extends BaseCallback {

    void onGetArticle(Article article);

}
