package com.example.dailyquotes;

import android.content.Context;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    List<QuoteModel> list;
    Context context;
    OnFavoriteDeleteListener listener;

    public FavoritesAdapter(List<QuoteModel> list, Context context,OnFavoriteDeleteListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QuoteModel q = list.get(position);
        holder.quote.setText(q.getText());
        holder.author.setText("— " + q.getAuthor());

        holder.deleteBtn.setOnClickListener(v -> {
            listener.onFavoriteDeleted(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView quote, author;
        ImageView deleteBtn;

        public ViewHolder(View v) {
            super(v);
            quote = v.findViewById(R.id.itemQuote);
            author = v.findViewById(R.id.itemAuthor);
            deleteBtn = v.findViewById(R.id.deleteBtn);
        }
    }
}