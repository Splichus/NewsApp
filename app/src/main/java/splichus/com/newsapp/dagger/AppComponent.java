package splichus.com.newsapp.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import splichus.com.newsapp.NewsApp;
import splichus.com.newsapp.dagger.module.ActivityBuilder;
import splichus.com.newsapp.dagger.module.AppModule;
import splichus.com.newsapp.dagger.module.FragmentBuilder;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
                      AppModule.class,
                      ActivityBuilder.class,
                      FragmentBuilder.class})

public interface AppComponent extends AndroidInjector<NewsApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(NewsApp newsApp);
}
