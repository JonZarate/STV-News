package com.jonzarate.stvnews.interactor;

import com.jonzarate.stvnews.interactor.callback.BaseCallback;
import com.jonzarate.stvnews.interactor.params.BaseParams;
import com.jonzarate.stvnews.interactor.threading.BackgroundThread;
import com.jonzarate.stvnews.interactor.threading.MainThread;

public abstract class BaseInteractor<P extends BaseParams, T extends BaseCallback> {

    protected MainThread main;
    protected BackgroundThread background;

    protected BaseInteractor(MainThread main, BackgroundThread background) {
        this.main = main;
        this.background = background;
    }

    protected abstract Object runInBackground(P params);
    protected abstract void runInMain(Object result, T callback);

    public void execute(final P params, final T callback){
        background.submit(new Runnable() {
            @Override
            public void run() {
                final Object result = BaseInteractor.this.runInBackground(params);

                main.post(new Runnable() {
                    @Override
                    public void run() {
                        BaseInteractor.this.runInMain(result, callback);
                    }
                });
            }
        });
    }
}
