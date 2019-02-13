package splichus.com.newsapp.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import splichus.com.newsapp.activity.SortDialog;
import splichus.com.newsapp.fragment.DetailsFragment;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector()
    abstract DetailsFragment bindDetailsFragment();
}
