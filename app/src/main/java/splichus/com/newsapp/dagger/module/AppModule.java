package splichus.com.newsapp.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.adapter.RecyclerAdapter;
import splichus.com.newsapp.api.service.NewsAPI;

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
        return new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build().create(NewsAPI.class);
    }

}
