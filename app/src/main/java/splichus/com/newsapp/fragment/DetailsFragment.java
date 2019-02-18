package splichus.com.newsapp.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerFragment;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;
import splichus.com.newsapp.activity.MainActivity;
import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.service.ArticleService;

import static android.content.ContentValues.TAG;

public class DetailsFragment extends DaggerFragment {

    @Inject
    ArticleService articleService;

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.download_bar)
    ConstraintLayout downloadBar;
    Article article;
    String folderName;
    File folder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_details, container, false);
        ButterKnife.bind(this, rootView);
        article = (Article) getArguments().getSerializable(Constants.ARTICLE);
        folderName = getActivity().getApplication().getFilesDir().getAbsoluteFile()+"/savedWebPages";
        folder = new File(folderName);
        if (article.isDownloaded()) {
            downloadBar.setVisibility(View.GONE);
            webView.loadUrl("file://" + folderName + "/" + article.getFileName());
        } else {
            webView.loadUrl(article.getUrl());
        }
        return rootView;
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
