package splichus.com.newsapp.dagger.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.activity.MainActivity;
import splichus.com.newsapp.api.service.NewsAPI;
import splichus.com.newsapp.service.ArticleService;
import splichus.com.newsapp.service.ArticlesListener;
import splichus.com.newsapp.service.Settings;
import splichus.com.newsapp.persistency.Database;
import splichus.com.newsapp.service.Sort;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences sharedPreferences(Application application) {
        return application.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    NewsAPI api() {
        return new Retrofit.Builder().baseUrl(Constants.NEWSAPI_URL).addConverterFactory(GsonConverterFactory.create()).build().create(NewsAPI.class);
    }

    @Provides
    @Singleton
    Settings settings() {
        return new Settings();
    }

    @Provides
    @Singleton
    @Inject
    Database database(Context ctx) {
        return Room.databaseBuilder(ctx, Database.class, Constants.DATABASE).allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    Sort sort() {
        return new Sort();
    }

    @Provides
    @Singleton
    @Inject
    ArticleService articleService(Settings settings, Database database, NewsAPI api, Sort sort) {
        return new ArticleService(settings, database, api, sort);
    }
}
