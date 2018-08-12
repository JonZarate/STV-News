package com.jonzarate.stvnews.di.module;

import android.content.Context;

import com.jonzarate.stvnews.STVApplication;
import com.jonzarate.stvnews.data.repository.NewsRepository;
import com.jonzarate.stvnews.data.repository.NewsRepositoryImpl;
import com.jonzarate.stvnews.data.source.cache.CacheSource;
import com.jonzarate.stvnews.data.source.cache.CacheSourceImpl;
import com.jonzarate.stvnews.data.source.net.ApiSource;
import com.jonzarate.stvnews.data.source.net.STVApi;
import com.jonzarate.stvnews.interactor.threading.BackgroundThread;
import com.jonzarate.stvnews.interactor.threading.MainThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private STVApplication application;

    public AppModule(STVApplication application) {
        this.application = application;
    }

    @Provides
    Context provideContext(){
        return application;
    }

    @Provides @Singleton
    MainThread provideMainThread(MainThread main) {
        return main;
    }

    @Provides @Singleton
    BackgroundThread provideBackgroundThread(BackgroundThread background){
        return background;
    }


    @Provides @Singleton
    CacheSource provideCacheSource(CacheSourceImpl cacheSource){
        return cacheSource;
    }

    @Provides @Singleton
    STVApi provideApiSource(){
        return ApiSource.api;
    }


    @Provides @Singleton
    NewsRepository provideNewsRepository(NewsRepositoryImpl repository){
        return repository;
    }
}
