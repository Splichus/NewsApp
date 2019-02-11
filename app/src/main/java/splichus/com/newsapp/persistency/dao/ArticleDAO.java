package splichus.com.newsapp.persistency.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import java.util.Optional;

import splichus.com.newsapp.model.Article;

@Dao
public interface ArticleDAO {

    @Insert
    void addArticle(Article article);

    @Query("SELECT * FROM articles WHERE id=:id")
    Article getArticleById(int id);

    @Query("SELECT * FROM articles")
    List<Article> getAllArticles();

    @Update
    void updateArticle(Article article);

    @Delete
    void deleteArticle(Article article);

    @Query("DELETE FROM articles")
    void deleteAllArticles();


}
