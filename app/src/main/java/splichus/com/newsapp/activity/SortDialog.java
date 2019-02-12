package splichus.com.newsapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import splichus.com.newsapp.R;

public class SortDialog extends DialogFragment {
    private static final String TAG = "LoginDialog";

    RadioButton author, date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sort, container, false);
        author = view.findViewById(R.id.radio_author);
        date = view.findViewById(R.id.radio_date);
        return view;
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_author:
                if (checked){

                }
                break;
            case R.id.radio_date:
                if (checked){

                }
                break;
        }
    }
}
