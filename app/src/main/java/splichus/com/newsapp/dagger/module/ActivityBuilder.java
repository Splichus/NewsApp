package splichus.com.newsapp.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import splichus.com.newsapp.activity.MainActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

}
