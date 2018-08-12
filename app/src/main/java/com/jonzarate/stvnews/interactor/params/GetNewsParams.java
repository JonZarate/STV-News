package com.jonzarate.stvnews.interactor.params;

public class GetNewsParams extends BaseParams {

    public boolean refresh;

    public GetNewsParams(boolean refresh) {
        this.refresh = refresh;
    }

}
