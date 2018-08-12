package com.jonzarate.stvnews.di.component;

import com.jonzarate.stvnews.di.module.ArticleModule;
import com.jonzarate.stvnews.di.scope.ActivityScope;
import com.jonzarate.stvnews.view.activity.ArticleActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ArticleModule.class, dependencies = AppComponent.class)
public interface ArticleComponent {
    void inject(ArticleActivity activity);
}
