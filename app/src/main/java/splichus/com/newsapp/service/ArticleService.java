package splichus.com.newsapp.service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import splichus.com.newsapp.BuildConfig;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.api.model.APIResponse;
import splichus.com.newsapp.api.model.CustomCallback;
import splichus.com.newsapp.api.service.NewsAPI;
import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.persistency.Database;

public class ArticleService {

    List<Article> articles;
    ArticlesListener activity;
    Settings settings;
    Database database;
    NewsAPI api;
    Sort sort;

    public ArticleService(ArticlesListener activity, Settings settings, Database database, NewsAPI api, Sort sort) {
        this.settings = settings;
        this.articles = new ArrayList<>();
        this.activity = activity;
        this.database = database;
        this.api = api;
        this.sort = sort;
    }

    public void getFromAPI() {
        articles.clear();
        if (settings.getApis().get(Constants.NEWSAPI_URL)) {
            api.getEverything(BuildConfig.API_KEY, 20, "android").enqueue(new CustomCallback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    articles.addAll((response.body().getArticles()));
                    activity.onArticles(articles);
                    activity.onDownloaded(false);
                }
            });
        }
    }

    public void getFromDB() {
        activity.onArticles(database.articleDAO().getAllArticles());
        activity.onDownloaded(true);
    }

    public void getFromCache() {
        activity.onArticles(articles);
        activity.onDownloaded(false);
    }

    public List<Article> getArticles() {
        return articles;
    }
}
