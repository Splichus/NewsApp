package splichus.com.newsapp.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import splichus.com.newsapp.activity.DetailsActivity;
import splichus.com.newsapp.activity.MainActivity;
import splichus.com.newsapp.activity.SettingsActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract DetailsActivity bindDetailsActivity();

    @ContributesAndroidInjector
    abstract SettingsActivity bindSettingsActivity();

}
