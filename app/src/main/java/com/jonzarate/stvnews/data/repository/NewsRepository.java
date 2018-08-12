package com.jonzarate.stvnews.data.repository;

import android.graphics.Bitmap;

import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.model.NewsList;

public interface NewsRepository {

    Article getArticle(int articleId);

    NewsList getNewsList(boolean refresh);

    String getArticleImageUrl(int imageId);

    Bitmap getImage(String url);
}
