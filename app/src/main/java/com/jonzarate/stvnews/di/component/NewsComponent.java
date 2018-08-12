package com.jonzarate.stvnews.di.component;

import com.jonzarate.stvnews.di.module.NewsModule;
import com.jonzarate.stvnews.di.scope.ActivityScope;
import com.jonzarate.stvnews.view.activity.NewsActivity;

import dagger.Component;

@ActivityScope
@Component(modules = NewsModule.class, dependencies = AppComponent.class)
public interface NewsComponent {
    void inject(NewsActivity activity);
}
