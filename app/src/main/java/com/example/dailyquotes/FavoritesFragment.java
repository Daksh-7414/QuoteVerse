package com.example.dailyquotes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesFragment extends Fragment {

    RecyclerView recyclerView;
    FavoritesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = v.findViewById(R.id.favoritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<QuoteModel> list = SharedPrefManager.getFavorites(getContext());
        adapter = new FavoritesAdapter(list, getContext(), new OnFavoriteDeleteListener() {
            @Override
            public void onFavoriteDeleted(int position) {

                new AlertDialog.Builder(getContext())
                        .setTitle("Remove Favorite")
                        .setMessage("Are you sure you want to remove this quote from favorites?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            SharedPrefManager.removeFavorite(getContext(), position);
                            list.remove(position);
                            adapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                        .setCancelable(true)
                        .show();
            }
        });
        recyclerView.setAdapter(adapter);

        return v;
    }
}