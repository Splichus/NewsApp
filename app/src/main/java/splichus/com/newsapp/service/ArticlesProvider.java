package splichus.com.newsapp.service;

import java.util.List;

import splichus.com.newsapp.model.Article;

public interface ArticlesProvider {

    void onArticles(List<Article> articles);
    void onDownloaded(Boolean bool);
}
