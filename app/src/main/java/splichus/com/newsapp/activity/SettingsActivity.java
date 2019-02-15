package splichus.com.newsapp.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;
import splichus.com.newsapp.service.Settings;

public class SettingsActivity extends AppCompatActivity {

    @Inject
    Settings settings;
    @Inject
    SharedPreferences sharedPreferences;

    @BindView(R.id.settings_newsapi_switch)
    Switch newsAPISwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings.changeEntry(Constants.NEWSAPI_URL, sharedPreferences.getBoolean(Constants.NEWSAPI_URL, true));
        newsAPISwitch.setChecked(settings.getStatus(Constants.NEWSAPI_URL));
        newsAPISwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.edit().putBoolean(Constants.NEWSAPI_URL, isChecked).apply();
                settings.changeEntry(Constants.NEWSAPI_URL, isChecked);
                Toast.makeText(SettingsActivity.this, isChecked ? "NewsAPI enabled" : "NewsAPI disabled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
