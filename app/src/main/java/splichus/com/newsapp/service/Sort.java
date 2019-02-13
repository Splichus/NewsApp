package splichus.com.newsapp.service;


import android.support.v4.app.NavUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import splichus.com.newsapp.Constants;
import splichus.com.newsapp.model.Article;

public class Sort implements Serializable {

    Map<String, Boolean> sorts;

    public Sort() {
        sorts = new HashMap<>();
    }

    public String getSortingKey() {
        for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
            if (entry.getValue()) return entry.getKey();
        }
        return "";
    }

    public void setSort(String sort) {
        for (Map.Entry<String, Boolean> entry : sorts.entrySet()) {
            entry.setValue(false);
        }
        sorts.put(sort, true);
    }

    public List<Article> sort(List<Article> articles) {
        if (getSortingKey().equals(Constants.SORT_AUTHOR)) {
            return sortByAuthor(articles);
        } else if (getSortingKey().equals(Constants.SORT_DATE)) {
            return sortByDate(articles);
        } else return articles;
    }

    private List<Article> sortByAuthor(List<Article> articles) {
        Collections.sort(articles, new SortByAuthor());
        return articles;
    }

    private List<Article> sortByDate(List<Article> articles) {
        Collections.sort(articles, new SortByDate());
        return articles;
    }


    private class SortByAuthor implements Comparator<Article>
    {
        @Override
        public int compare(Article article, Article article2) {
            return article.getAuthor().compareTo(article2.getAuthor());
        }
    }
    private class SortByDate implements Comparator<Article> {

        @Override
        public int compare(Article article1, Article article2) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            Date dateArticle1 = new Date();
            Date dateArticle2 = new Date();
            try {
                dateArticle1 = dateFormat.parse(article1.getPublishedAt());
                dateArticle2 = dateFormat.parse(article2.getPublishedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }


            return dateArticle1.compareTo(dateArticle2);
        }
    }
}
