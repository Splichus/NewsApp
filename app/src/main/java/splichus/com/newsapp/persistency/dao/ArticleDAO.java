package splichus.com.newsapp.persistency.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import splichus.com.newsapp.model.Article;

@Dao
public interface ArticleDAO {

    @Insert
    public void addArticle(Article article);


}
