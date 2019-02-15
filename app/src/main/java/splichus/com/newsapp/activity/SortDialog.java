package splichus.com.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;
import splichus.com.newsapp.service.ArticlesListener;
import splichus.com.newsapp.service.Sort;

public class SortDialog extends DialogFragment {
    private static final String TAG = "LoginDialog";
    @BindView(R.id.radio_author)
    RadioButton author;
    @BindView(R.id.radio_date)
    RadioButton date;
    @BindView(R.id.dialog_save)
    TextView save;
    String sortBy;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sort, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.radio_date, R.id.radio_author, R.id.dialog_save})
    public void onClickListeners(View view) {
        switch (view.getId()){
            case R.id.radio_date:
                sortBy = Constants.SORT_DATE;
                break;
            case R.id.radio_author:
                sortBy = Constants.SORT_AUTHOR;
                break;
            case R.id.dialog_save:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.articleService.sort(sortBy);
                getDialog().dismiss();
        }
    }
}
