package splichus.com.newsapp.api.model;

import java.util.List;

import splichus.com.newsapp.model.Article;

public class APIResponse {

    private String status;
    private int totalResults;
    private List<Article> articles;

    public APIResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public APIResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
