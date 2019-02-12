package splichus.com.newsapp.persistency.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import java.util.Optional;

import splichus.com.newsapp.model.Article;

@Dao
public interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addArticle(Article article);

    @Query("SELECT * FROM articles WHERE id=:id")
    Article getArticleById(int id);

    @Query("SELECT * FROM articles")
    List<Article> getAllArticles();

    @Update
    void updateArticle(Article article);

    @Query("DELETE FROM articles WHERE id=:id")
    void deleteArticleById(int id);

    @Query("DELETE FROM articles")
    void deleteAllArticles();

    @Query("SELECT * FROM articles WHERE url=:url")
    Article getArticleByUrl(String url);

    @Query("DELETE FROM articles WHERE url=:url")
    void deleteArticleByUrl(String url);


}
