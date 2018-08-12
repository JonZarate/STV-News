package com.jonzarate.stvnews.interactor;

import android.graphics.Bitmap;

import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.repository.NewsRepository;
import com.jonzarate.stvnews.interactor.callback.GetImageCallback;
import com.jonzarate.stvnews.interactor.params.GetImageParams;
import com.jonzarate.stvnews.interactor.threading.BackgroundThread;
import com.jonzarate.stvnews.interactor.threading.MainThread;

import javax.inject.Inject;

public class GetImageInteractor extends BaseInteractor<GetImageParams, GetImageCallback> {

    @Inject
    NewsRepository repo;

    @Inject
    protected GetImageInteractor(MainThread main, BackgroundThread background) {
        super(main, background);
    }

    @Override
    protected Object runInBackground(GetImageParams params) {
        Article article = params.getArticle();
        Bitmap bitmap = repo.getImage(article.getImageUrl());
        article.setImage(bitmap);
        return article;
    }

    @Override
    protected void runInMain(Object result, GetImageCallback callback) {
        Article article = (Article) result;
        Bitmap bitmap = article.getImage();
        if (bitmap != null)
            callback.onGetImageCallback(article);
    }
}
