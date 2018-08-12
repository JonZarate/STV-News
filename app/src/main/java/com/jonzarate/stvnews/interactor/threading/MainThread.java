package com.jonzarate.stvnews.interactor.threading;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;

public class MainThread extends Handler {

    @Inject
    public MainThread() {
        super(Looper.getMainLooper());
    }

}
