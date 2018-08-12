package com.jonzarate.stvnews.presenter;

import com.jonzarate.stvnews.contract.NewsContract;
import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.model.ArticleImage;
import com.jonzarate.stvnews.data.model.NewsList;
import com.jonzarate.stvnews.interactor.GetImageInteractor;
import com.jonzarate.stvnews.interactor.GetImageUrlInteractor;
import com.jonzarate.stvnews.interactor.GetNewsInteractor;
import com.jonzarate.stvnews.interactor.callback.GetImageCallback;
import com.jonzarate.stvnews.interactor.callback.GetImageUrlCallbacks;
import com.jonzarate.stvnews.interactor.callback.GetNewsCallbacks;
import com.jonzarate.stvnews.interactor.params.GetImageParams;
import com.jonzarate.stvnews.interactor.params.GetImageUrlParams;
import com.jonzarate.stvnews.interactor.params.GetNewsParams;

import java.util.List;

import javax.inject.Inject;

public class NewsPresenter implements NewsContract.Presenter, GetNewsCallbacks, GetImageUrlCallbacks, GetImageCallback {

    @Inject
    NewsContract.View view;

    @Inject
    GetNewsInteractor getNews;

    @Inject
    GetImageUrlInteractor getImageUrl;

    @Inject
    GetImageInteractor getImage;

    private NewsList newsList;
    private boolean isPageLoading;

    @Inject
    public NewsPresenter(){
        isPageLoading = false;
    }

    @Override
    public void onStart() {
        getNews.execute(new GetNewsParams(false), this);
    }

    @Override
    public void onRefresh() {
        if (isPageLoading) {
            // todo: show error
        } else {
            setPageLoading(true);
            getNews.execute(new GetNewsParams(true), this);
        }
    }

    @Override
    public void onArticleClicked(Article article) {
        view.startArticleActivity(article.getId());
    }

    @Override
    public void onNewsLoaded(NewsList list) {
        newsList = list;
        setPageLoading(false);
        view.setNewsList(list);

        for (Article article : list) {
            manageArticleImage(article);
        }
    }

    @Override
    public void onNewsNotLoaded() {
        setPageLoading(false);
    }

    private void setPageLoading(boolean loading){
        isPageLoading = loading;

        if (isPageLoading) {
            view.showLoading();
            view.disableSwipeToRefresh();
        } else {
            view.hideLoading();
            view.enableSwipeToRefresh();
        }
    }

    private void manageArticleImage(Article article){
        List<ArticleImage> images = article.getImages();
        if (images != null && images.size() > 0) {
            if (article.getImageUrl() == null){
                getImageUrl.execute(new GetImageUrlParams(article), this);
            } else if (article.getImage() == null) {
                getImage.execute(new GetImageParams(article), this);
            }
        }
    }

    @Override
    public void onGetImageUrl(Article article) {
        getImage.execute(new GetImageParams(article), this);
    }

    @Override
    public void onGetImageCallback(Article article) {
        int position = newsList.indexOf(article);
        view.refreshArticle(position);
    }
}
