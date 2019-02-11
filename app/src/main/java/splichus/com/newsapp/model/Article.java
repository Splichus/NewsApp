package splichus.com.newsapp.model;

import java.io.Serializable;
import java.util.UUID;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Article implements Serializable {

    @PrimaryKey
    private int id;
    private String title;
    private String author;
    private String url;
    private String urlToImage;

    public Article(String title, String author, String url, String urlToImage) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public Article() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
