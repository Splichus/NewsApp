package splichus.com.newsapp.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;
import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.service.ArticleService;


public class DetailsActivity extends AppCompatActivity {

    @Inject
    ArticleService articleService;

    private static final String TAG = "DetailsActivity";
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.download_bar)
    ConstraintLayout downloadBar;
    Article article;
    String folderName;
    File folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        article = (Article) getIntent().getSerializableExtra(Constants.ARTICLE);
        folderName = getApplication().getFilesDir().getAbsoluteFile()+"/savedWebPages";
        folder = new File(folderName);
        if (article.isDownloaded()) {
            downloadBar.setVisibility(View.GONE);
            webView.loadUrl("file://" + folderName + "/" + article.getFileName());
        } else {
            webView.loadUrl(article.getUrl());
        }
    }

    @OnClick(R.id.download_bar)
    public void downloadArticle(View view) {
        if (!folder.exists()) {
            folder.mkdir();
        }
        String fileName = article.getUrl().replace("/", "")+".mht";
        articleService.downloadArticle(article, fileName);
        webView.saveWebArchive(folderName + "/" + fileName);
        downloadBar.setVisibility(View.GONE);
        Log.d(TAG, "downloadArticle: filename: "+folderName+"/"+fileName);
    }


}
