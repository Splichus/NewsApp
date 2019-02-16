package splichus.com.newsapp.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.download_bar)
    ConstraintLayout downloadBar;
    @BindView(R.id.details_download_text)
    TextView downloadText;
    @BindView(R.id.details_download_icon)
    ImageView downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        webView.loadUrl(getIntent().getStringExtra(Constants.ARTICLE));
        Log.d(TAG, "onCreate: webview loaded");
    }
}
