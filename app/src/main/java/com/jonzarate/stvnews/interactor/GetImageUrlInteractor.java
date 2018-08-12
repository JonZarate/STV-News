package com.jonzarate.stvnews.interactor;

import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.repository.NewsRepository;
import com.jonzarate.stvnews.interactor.callback.GetImageUrlCallbacks;
import com.jonzarate.stvnews.interactor.params.GetImageUrlParams;
import com.jonzarate.stvnews.interactor.threading.BackgroundThread;
import com.jonzarate.stvnews.interactor.threading.MainThread;

import javax.inject.Inject;

public class GetImageUrlInteractor extends BaseInteractor<GetImageUrlParams, GetImageUrlCallbacks> {

    @Inject
    NewsRepository repo;

    @Inject
    protected GetImageUrlInteractor(MainThread main, BackgroundThread background) {
        super(main, background);
    }

    @Override
    protected Object runInBackground(GetImageUrlParams params) {
        Article article = params.getArticle();
        String url = repo.getArticleImageUrl(article.getImages().get(0).getId());
        article.setImageUrl(url);
        return article;
    }

    @Override
    protected void runInMain(Object result, GetImageUrlCallbacks callback) {
        Article article = (Article) result;
        String url = article.getImageUrl();
        if (url != null )
            callback.onGetImageUrl(article);
    }
}
