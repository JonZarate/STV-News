package com.jonzarate.stvnews.data.source.cache;

import com.jonzarate.stvnews.data.model.NewsList;

public interface CacheSource {

    NewsList getCachedNewsList();

    void saveNewsList(NewsList list);

}
