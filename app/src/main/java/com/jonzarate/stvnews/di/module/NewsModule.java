package com.jonzarate.stvnews.di.module;

import com.jonzarate.stvnews.contract.NewsContract;
import com.jonzarate.stvnews.di.scope.ActivityScope;
import com.jonzarate.stvnews.presenter.NewsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    private NewsContract.View view;

    public NewsModule(NewsContract.View view) {
        this.view = view;
    }

    @Provides @ActivityScope
    NewsContract.View provideActivity() {
        return view;
    }

    @Provides @ActivityScope
    NewsContract.Presenter providePresenter(NewsPresenter presenter){
        return presenter;
    }

}
