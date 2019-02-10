package splichus.com.newsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import splichus.com.newsapp.R;

import dagger.android.AndroidInjection;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
