package splichus.com.newsapp.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.api.service.NewsAPI;
import splichus.com.newsapp.model.SavedArticles;
import splichus.com.newsapp.model.Settings;
import splichus.com.newsapp.persistency.Database;

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
    @Inject
    SavedArticles savedArticles(Application application) {
        return new SavedArticles(application);
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
        return Room.databaseBuilder(ctx, Database.class, "mydb").build();
    }
}
