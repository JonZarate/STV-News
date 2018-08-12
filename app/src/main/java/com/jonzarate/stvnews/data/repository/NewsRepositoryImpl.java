package com.jonzarate.stvnews.data.repository;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.model.ImageUrl;
import com.jonzarate.stvnews.data.model.NewsList;
import com.jonzarate.stvnews.data.source.cache.CacheSource;
import com.jonzarate.stvnews.data.source.net.STVApi;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class NewsRepositoryImpl implements NewsRepository {

    @Inject
    CacheSource cache;

    @Inject
    STVApi api;

    @Inject
    Context context;

    @Inject
    public NewsRepositoryImpl() {

    }

    @Override
    public Article getArticle(int articleId) {
        NewsList list = cache.getCachedNewsList();
        for (Article article : list) {
            if (article.getId() == articleId)
                return article;
        }
        return null;
    }

    @Override
    public NewsList getNewsList(boolean refresh) {
        NewsList list = null;

        if (!refresh) {
            list = cache.getCachedNewsList();
        }

        if (list == null) {
            try {
                Response<NewsList> response = api.getNews().execute();
                if (response.isSuccessful())
                    list = response.body();

            } catch (IOException ignore) { }

            cache.saveNewsList(list);
        }

        return list;
    }

    @Override
    public String getArticleImageUrl(int imageId) {
        String url = null;
        try {
            Response<ImageUrl> response = api.getImageUrl(imageId).execute();
            if (response.isSuccessful())
                url = response.body().getUrl();

        } catch (IOException ignore) { }
        return url;
    }

    @Override
    public Bitmap getImage(String url) {
        try {
            return Glide.with(context).asBitmap().load(url).submit().get();
        } catch (Exception ignore) {
            return null;
        }
    }
}
