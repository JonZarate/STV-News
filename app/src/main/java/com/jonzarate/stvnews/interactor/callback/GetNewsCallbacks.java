package com.jonzarate.stvnews.interactor.callback;

import com.jonzarate.stvnews.data.model.NewsList;

public interface GetNewsCallbacks extends BaseCallback {
    void onNewsLoaded(NewsList list);
    void onNewsNotLoaded();
}