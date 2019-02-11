package splichus.com.newsapp.persistency;

import android.arch.persistence.room.RoomDatabase;

import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.persistency.dao.ArticleDAO;

@android.arch.persistence.room.Database(entities = {Article.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract ArticleDAO articleDAO();
}
