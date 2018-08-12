package com.jonzarate.stvnews.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jonzarate.stvnews.R;
import com.jonzarate.stvnews.STVApplication;
import com.jonzarate.stvnews.contract.ArticleContract;
import com.jonzarate.stvnews.di.component.DaggerArticleComponent;
import com.jonzarate.stvnews.di.module.ArticleModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity implements ArticleContract.View {

    public static String EXTRA_ARTICLE_ID = "ARTICLE";

    @BindView(R.id.article_toolbar)
    Toolbar toolbar;

    @BindView(R.id.article_image)
    ImageView image;

    @BindView(R.id.article_webview)
    WebView web;

    @Inject
    ArticleContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ButterKnife.bind(this);

        DaggerArticleComponent.builder()
                .appComponent(((STVApplication)getApplicationContext()).appComponent)
                .articleModule(new ArticleModule(this))
                .build()
                .inject(this);

        setToolbar();
        readExtras();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void readExtras() {
        int articleId = getIntent().getIntExtra(EXTRA_ARTICLE_ID, 0);
        if (articleId == 0)
            finish();

        presenter.onStart(articleId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setImage(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
    }

    @Override
    public void setHtmlCode(String code) {
        web.loadData(code, "text/html", "UTF-8");
    }
}
