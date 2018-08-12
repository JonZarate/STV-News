package com.jonzarate.stvnews.interactor;

import com.jonzarate.stvnews.data.model.NewsList;
import com.jonzarate.stvnews.data.repository.NewsRepository;
import com.jonzarate.stvnews.interactor.callback.GetNewsCallbacks;
import com.jonzarate.stvnews.interactor.params.GetNewsParams;
import com.jonzarate.stvnews.interactor.threading.BackgroundThread;
import com.jonzarate.stvnews.interactor.threading.MainThread;

import javax.inject.Inject;

public class GetNewsInteractor extends BaseInteractor<GetNewsParams, GetNewsCallbacks> {

    @Inject
    NewsRepository repo;

    @Inject
    public GetNewsInteractor(MainThread main, BackgroundThread background) {
        super (main, background);
    }


    @Override
    protected Object runInBackground(GetNewsParams params){
        return repo.getNewsList(params.refresh);
    }

    @Override
    protected void runInMain(Object result, GetNewsCallbacks callback) {
        NewsList newsList = (NewsList) result;
        if (newsList != null)
            callback.onNewsLoaded(newsList);
        else
            callback.onNewsNotLoaded();
    }

}
