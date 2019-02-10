package splichus.com.newsapp.api.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class CustomCallback<T> implements Callback<T> {
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        Log.d("HttpRequestFailure", call.request().toString());
    }
}
