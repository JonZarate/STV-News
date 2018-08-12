package com.jonzarate.stvnews.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.View;

import com.jonzarate.stvnews.R;
import com.jonzarate.stvnews.STVApplication;
import com.jonzarate.stvnews.contract.NewsContract;
import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.model.NewsList;
import com.jonzarate.stvnews.di.component.DaggerNewsComponent;
import com.jonzarate.stvnews.di.module.NewsModule;
import com.jonzarate.stvnews.view.adapter.NewsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class NewsActivity extends AppCompatActivity implements NewsContract.View, NewsAdapter.ArticleClickListener,
        SwipeRefreshLayout.OnRefreshListener{

    //region Members

    @BindView(R.id.news_layout)
    ConstraintLayout layout;

    @BindView(R.id.news_progress)
    MaterialProgressBar progressBar;

    @BindView(R.id.news_toolbar)
    Toolbar toolbar;

    @BindView(R.id.news_swipe)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.news_recycler)
    RecyclerView recycler;

    @Inject
    NewsContract.Presenter presenter;

    private NewsAdapter adapter;

    //endregion

    //region Activity initialization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ButterKnife.bind(this);

        DaggerNewsComponent.builder()
                .appComponent(((STVApplication) getApplicationContext()).appComponent)
                .newsModule(new NewsModule(this))
                .build()
                .inject(this);

        initializeRecyclerView();
        initializeViews();

        presenter.onStart();
    }

    private void initializeRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this, layoutManager.getOrientation());

        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(dividerItemDecoration);

        adapter = new NewsAdapter(this, this);
        recycler.setAdapter(adapter);
    }

    private void initializeViews() {
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(this);

        progressBar.setVisibility(View.GONE);
    }

    //endregion

    //region MVP View implementation

    @Override
    public void setNewsList(NewsList list) {
        adapter.setNewsList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshArticle(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void showLoading() {
        updateProgressBar(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        updateProgressBar(View.GONE);
    }

    @Override
    public void enableSwipeToRefresh() {
        swipeRefresh.setEnabled(true);
    }

    @Override
    public void disableSwipeToRefresh() {
        swipeRefresh.setEnabled(false);
    }

    @Override
    public void startArticleActivity(int articleId) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra(ArticleActivity.EXTRA_ARTICLE_ID, articleId);
        startActivity(intent);
    }

    //endregion

    //region Helpers

    private void updateProgressBar(int visibility) {
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        set.setVisibility(R.id.news_progress, visibility);

        TransitionManager.beginDelayedTransition(layout);
        set.applyTo(layout);
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(false);
        presenter.onRefresh();
    }

    @Override
    public void onArticleClick(Article article) {
        presenter.onArticleClicked(article);
    }

    //endregion
}
