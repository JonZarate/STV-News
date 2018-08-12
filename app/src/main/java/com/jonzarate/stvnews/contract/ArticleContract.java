package com.jonzarate.stvnews.contract;

import android.graphics.Bitmap;

public interface ArticleContract {

    interface Presenter {

        void onStart(int articleId);

    }

    interface View {

        void setImage(Bitmap bitmap);

        void setHtmlCode(String code);

    }

}
