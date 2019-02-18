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

    public ArticleService(Settings settings, Database database, NewsAPI api, Sort sort) {
        this.settings = settings;
        this.articles = new ArrayList<>();
        this.database = database;
        this.api = api;
        this.sort = sort;
    }

    public void setActivity(ArticlesListener activity) {
        this.activity = activity;
    }

    public void getFromAPI() {
        articles.clear();
        if (settings.getApis().get(Constants.NEWSAPI_URL)) {
            api.getEverything(BuildConfig.API_KEY, 20, "android").enqueue(new CustomCallback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    for (int i = 0; i < response.body().getArticles().size() ; i++) {
                        if (isDownloaded(response.body().getArticles().get(i))) {
                            articles.add(database.articleDAO().getArticleByUrl(response.body().getArticles().get(i).getUrl()));
                        } else {
                            articles.add(response.body().getArticles().get(i));
                        }
                    }
                    activity.onArticles(articles);
                    activity.onDownloaded(false);
                }
            });
        }
    }

    public void getFromDB() {
        articles.clear();
        articles.addAll(database.articleDAO().getAllArticles());
        activity.onArticles(articles);
        activity.onDownloaded(true);
    }

    public void saveArticleToDB(Article article){
        article.setDownloaded(true);
        database.articleDAO().addArticle(article);
    }

    public Article getArticlefromDB(String articleURL) {
        return database.articleDAO().getArticleByUrl(articleURL);
    }

    public void deleteArticle(Article article) {
        article.setDownloaded(false);
        database.articleDAO().deleteArticleByUrl(article.getUrl());
    }

    public void sort(String sortBy) {
        activity.onArticles(sort.sort(sortBy, articles));
    }

    public List<Article> getArticles() {
        return articles;
    }

    public boolean isDownloaded(Article article){
        return database.articleDAO().getArticleByUrl(article.getUrl()) != null;
    }

    public void downloadArticle(Article article, String fileName){

        article.setFileName(fileName);
        article.setDownloaded(true);
        saveArticleToDB(article);

    }
}
