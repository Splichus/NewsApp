package splichus.com.newsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;

import dagger.android.AndroidInjection;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        webView = findViewById(R.id.webview);
        webView.loadUrl(getIntent().getStringExtra(Constants.ARTICLE));
        Log.d(TAG, "onCreate: webview loaded");
    }
}
