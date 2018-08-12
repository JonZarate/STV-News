package com.jonzarate.stvnews;

import android.app.Application;

import com.jonzarate.stvnews.di.component.AppComponent;
import com.jonzarate.stvnews.di.component.DaggerAppComponent;
import com.jonzarate.stvnews.di.module.AppModule;

public class STVApplication extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
