package splichus.com.newsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import dagger.android.support.DaggerFragment;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;

public class DetailsFragment extends DaggerFragment {

    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_details, container, false);

        if (getArguments().containsKey(Constants.URL)) {
            webView = rootView.findViewById(R.id.webview);
            webView.loadUrl(getArguments().getString(Constants.URL));
        }
        return rootView;
    }
}
