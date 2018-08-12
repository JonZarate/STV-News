package com.jonzarate.stvnews.di.component;

import com.jonzarate.stvnews.STVApplication;
import com.jonzarate.stvnews.data.repository.NewsRepository;
import com.jonzarate.stvnews.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(STVApplication application);

    NewsRepository newsRepository();
}
