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

import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;
import splichus.com.newsapp.service.Sort;

public class SortDialog extends DialogFragment {
    private static final String TAG = "LoginDialog";

    Boolean authorCheck, dateCheck;
    RadioButton author, date;
    TextView save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sort, container, false);
        author = view.findViewById(R.id.radio_author);
        date = view.findViewById(R.id.radio_date);
        save = view.findViewById(R.id.dialog_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }
}
