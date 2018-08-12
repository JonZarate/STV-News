package com.jonzarate.stvnews.contract;

import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.model.NewsList;

public interface NewsContract {

    interface Presenter {

        void onStart();

        void onRefresh();

        void onArticleClicked(Article article);

    }

    interface View {

        void setNewsList(NewsList list);

        void refreshArticle(int position);

        void showLoading();

        void hideLoading();

        void enableSwipeToRefresh();

        void disableSwipeToRefresh();

        void startArticleActivity(int articleId);

    }

}
