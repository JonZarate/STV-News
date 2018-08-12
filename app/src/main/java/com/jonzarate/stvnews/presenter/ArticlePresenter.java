package com.jonzarate.stvnews.presenter;

import com.jonzarate.stvnews.contract.ArticleContract;
import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.interactor.GetArticleByIdInteractor;
import com.jonzarate.stvnews.interactor.callback.GetArticleByIdCallbacks;
import com.jonzarate.stvnews.interactor.params.GetArticleByIdParams;

import javax.inject.Inject;

public class ArticlePresenter implements ArticleContract.Presenter, GetArticleByIdCallbacks{

    @Inject
    ArticleContract.View view;

    @Inject
    GetArticleByIdInteractor getArticleById;


    @Inject
    public ArticlePresenter() {

    }

    @Override
    public void onStart(int articleId) {
        getArticleById.execute(new GetArticleByIdParams(articleId), this);
    }

    @Override
    public void onGetArticle(Article article) {
        view.setImage(article.getImage());
        view.setHtmlCode(article.getContentHTML());
    }
}
