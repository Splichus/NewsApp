package splichus.com.newsapp.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import splichus.com.newsapp.BuildConfig;
import splichus.com.newsapp.R;
import splichus.com.newsapp.adapter.RecyclerAdapter;
import splichus.com.newsapp.api.model.APIResponse;
import splichus.com.newsapp.api.model.CustomCallback;
import splichus.com.newsapp.api.service.NewsAPI;

public class MainActivity extends AppCompatActivity {

    @Inject
    NewsAPI API;

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recycler_view);
        ctx = this;

        API.getEverything(BuildConfig.API_KEY, 100).enqueue(new CustomCallback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                adapter = new RecyclerAdapter(response.body().getArticles(),ctx);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            }
        });
    }
}
