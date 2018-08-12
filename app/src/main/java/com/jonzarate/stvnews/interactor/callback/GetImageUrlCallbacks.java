package com.jonzarate.stvnews.interactor.callback;

import com.jonzarate.stvnews.data.model.Article;

public interface GetImageUrlCallbacks extends BaseCallback {

    void onGetImageUrl(Article article);

}
