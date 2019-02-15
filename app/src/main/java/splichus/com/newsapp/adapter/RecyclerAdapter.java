package splichus.com.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;
import splichus.com.newsapp.activity.DetailsActivity;
import splichus.com.newsapp.activity.MainActivity;
import splichus.com.newsapp.fragment.DetailsFragment;
import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.persistency.Database;
import splichus.com.newsapp.service.ArticleService;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private static final String TAG = "RecycylerViewAdapter";

    private MainActivity parent;
    private List<Article> articles;
    private ArticleService articleService;
    private Boolean twoPane;

    public RecyclerAdapter(MainActivity parent, ArticleService articleService, Boolean twoPane) {
        this.parent = parent;
        this.articles = new ArrayList<>();
        this.articleService = articleService;
        this.twoPane = twoPane;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");
        Picasso.get().load(articles.get(i).getUrlToImage()).into(viewHolder.picture);
        viewHolder.title.setText(articles.get(i).getTitle());
        viewHolder.author.setText(articles.get(i).getAuthor());
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!twoPane) {
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra(Constants.ARTICLE, articles.get(i).getUrl());
                    v.getContext().startActivity(intent);
                } else {
                    Bundle url = new Bundle();
                    url.putString(Constants.URL, articles.get(i).getUrl());
                    DetailsFragment fragment = new DetailsFragment();
                    fragment.setArguments(url);
                    parent.getSupportFragmentManager().beginTransaction().replace(R.id.dual_details, fragment).commit();
                }
            }
        });
        if (downloaded(i)) {
            viewHolder.arrow.setImageResource(R.drawable.delete);
        } else {
            viewHolder.arrow.setImageResource(R.drawable.not_downloaded);
        }
        viewHolder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloaded(i)){
                    viewHolder.arrow.setImageResource(R.drawable.not_downloaded);
                    articleService.deleteArticleByURL(articles.get(i).getUrl());
                } else {
                    viewHolder.arrow.setImageResource(R.drawable.delete);
                    articleService.saveArticleToDB(articles.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_parent)
        ConstraintLayout item;
        @BindView(R.id.list_item_picture)
        ImageView picture;
        @BindView(R.id.list_item_title)
        TextView title;
        @BindView(R.id.list_item_author)
        TextView author;
        @BindView(R.id.list_item_arrow)
        ImageView arrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private boolean downloaded(int i) {
        return articleService.getArticlefromDB(articles.get(i).getUrl()) != null;
    }

}
