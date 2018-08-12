package com.jonzarate.stvnews.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonzarate.stvnews.R;
import com.jonzarate.stvnews.data.model.Article;
import com.jonzarate.stvnews.data.model.NewsList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public interface ArticleClickListener {
        void onArticleClick (Article article);
    }

    private Context context;
    private ArticleClickListener listener;

    private NewsList newsList;

    public NewsAdapter(Context context, ArticleClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.viewholder_article, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = newsList.get(position);
        holder.setArticle(article);
    }

    @Override
    public int getItemCount() {
        return (newsList == null) ? 0 : newsList.size();
    }

    public void setNewsList(NewsList newsList) {
        this.newsList = newsList;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Article article;
        private TextView headline, subheadline;
        private ImageView image;

        NewsViewHolder(View itemView) {
            super(itemView);
            headline = itemView.findViewById(R.id.article_headline);
            subheadline = itemView.findViewById(R.id.article_subheadline);
            image = itemView.findViewById(R.id.article_image);

            itemView.setOnClickListener(this);
        }

        Article getArticle() {
            return article;
        }

        void setArticle(Article article) {
            this.article = article;
            headline.setText(article.getTitle());
            subheadline.setText(article.getSubHeadline());
            Bitmap bitmap = article.getImage();
            if (bitmap != null)
                image.setImageBitmap(bitmap);
            else{
                image.setImageResource(R.drawable.stv_logo);
            }
        }

        @Override
        public void onClick(View v) {
            listener.onArticleClick(article);
        }
    }

}
