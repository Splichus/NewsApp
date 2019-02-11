package splichus.com.newsapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "articles")
public class Article implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;
    private String url;
    private String urlToImage;

    @Ignore
    public Article(String title, String author, String url, String urlToImage) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
