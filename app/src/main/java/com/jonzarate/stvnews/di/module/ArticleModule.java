package com.jonzarate.stvnews.di.module;

import com.jonzarate.stvnews.contract.ArticleContract;
import com.jonzarate.stvnews.di.scope.ActivityScope;
import com.jonzarate.stvnews.presenter.ArticlePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ArticleModule {

    private ArticleContract.View view;

    public ArticleModule(ArticleContract.View view) {
        this.view = view;
    }

    @Provides @ActivityScope
    ArticleContract.View provideActivity() {
        return view;
    }

    @Provides @ActivityScope
    ArticleContract.Presenter providePresenter(ArticlePresenter presenter){
        return presenter;
    }

}
