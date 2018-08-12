package com.jonzarate.stvnews.interactor;

import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.repository.NewsRepository;
import com.jonzarate.stvnews.interactor.callback.GetArticleByIdCallbacks;
import com.jonzarate.stvnews.interactor.params.GetArticleByIdParams;
import com.jonzarate.stvnews.interactor.threading.BackgroundThread;
import com.jonzarate.stvnews.interactor.threading.MainThread;

import javax.inject.Inject;

public class GetArticleByIdInteractor extends BaseInteractor<GetArticleByIdParams, GetArticleByIdCallbacks> {

    @Inject
    NewsRepository repo;

    @Inject
    protected GetArticleByIdInteractor(MainThread main, BackgroundThread background) {
        super(main, background);
    }

    @Override
    protected Object runInBackground(GetArticleByIdParams params) {
        return repo.getArticle(params.getArticleId());
    }

    @Override
    protected void runInMain(Object result, GetArticleByIdCallbacks callback) {
        Article article = (Article) result;
        if (article != null)
            callback.onGetArticle(article);
    }
}
