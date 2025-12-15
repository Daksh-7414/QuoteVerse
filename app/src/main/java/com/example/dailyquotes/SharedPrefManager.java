package com.example.dailyquotes;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {

    private static final String PREF_NAME = "quotes_pref";
    private static final String KEY_FAVORITES = "favorite_quotes";

    public static void saveFavorite(Context context, QuoteModel quote) {
        List<QuoteModel> list = getFavorites(context);
        list.add(quote);

        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        Gson gson = new Gson();
        editor.putString(KEY_FAVORITES, gson.toJson(list));
        editor.apply();
    }

    public static List<QuoteModel> getFavorites(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = pref.getString(KEY_FAVORITES, null);
        Type type = new TypeToken<ArrayList<QuoteModel>>() {}.getType();

        List<QuoteModel> list = gson.fromJson(json, type);
        return list == null ? new ArrayList<>() : list;
    }

    public static boolean isFavorite(Context context, String quoteText) {
        List<QuoteModel> list = getFavorites(context);

        for (QuoteModel q : list) {
            if (q.getText().equals(quoteText)) {
                return true;
            }
        }
        return false;
    }


    public static void removeFavorite(Context context, int position) {
        List<QuoteModel> list = getFavorites(context);
        list.remove(position);

        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();

        Gson gson = new Gson();
        editor.putString(KEY_FAVORITES, gson.toJson(list));
        editor.apply();
    }
}