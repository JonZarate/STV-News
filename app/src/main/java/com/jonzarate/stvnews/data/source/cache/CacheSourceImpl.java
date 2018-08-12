package com.jonzarate.stvnews.data.source.cache;

import com.jonzarate.stvnews.data.model.NewsList;

import javax.inject.Inject;

public class CacheSourceImpl implements CacheSource {

    private NewsList list;

    @Inject
    public CacheSourceImpl() {

    }

    @Override
    public NewsList getCachedNewsList() {
        return list;
    }

    @Override
    public void saveNewsList(NewsList list) {
        this.list = list;
    }
}
