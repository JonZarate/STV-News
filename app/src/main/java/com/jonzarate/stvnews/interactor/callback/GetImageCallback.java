package com.jonzarate.stvnews.interactor.callback;

import com.jonzarate.stvnews.data.model.Article;

public interface GetImageCallback extends BaseCallback {

    void onGetImageCallback(Article article);

}
