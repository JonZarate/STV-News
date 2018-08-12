# STV News

![](images/main.png)   ![](images/article.png)



## Architecture

This app uses MVP with interactors to execute background tasks and repository pattern to decouple all data management. All this is easily managed through dependency injection.

Configuration changes are handled through a cache, avoiding unnecessary network calls when the Activity is recreated.

## Libraries

* [MaterialProgressBar](https://github.com/DreaminginCodeZH/MaterialProgressBar)

* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Retrofit 2](https://github.com/square/retrofit)
* [Dagger 2](https://github.com/google/dagger)
* [Glide](https://github.com/bumptech/glide)