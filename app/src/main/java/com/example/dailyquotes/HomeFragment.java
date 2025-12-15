package com.example.dailyquotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    TextView quoteText, authorText;
    MaterialButton nextBtn, favBtn;


    ArrayList<QuoteModel> list = new ArrayList<>();
    Random random = new Random();
    int currentIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        quoteText = v.findViewById(R.id.quoteText);
        authorText = v.findViewById(R.id.quoteAuthor);
        nextBtn = v.findViewById(R.id.nextBtn);
        favBtn = v.findViewById(R.id.addFavBtn);

        loadQuotesFromJson();
        showRandomQuote();

        nextBtn.setOnClickListener(view -> showRandomQuote());

        favBtn.setOnClickListener(view -> {
            if (!list.isEmpty() && currentIndex < list.size()) {

                QuoteModel currentQuote = list.get(currentIndex);

                if (SharedPrefManager.isFavorite(getContext(), currentQuote.getText())) {
                    Toast.makeText(getContext(), "Already added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPrefManager.saveFavorite(getContext(), currentQuote);
                    Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void loadQuotesFromJson() {
        try {
            // JSON file read करें
            InputStream is = getActivity().getAssets().open("quotes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            // Gson का use करके JSON को ArrayList में convert करें
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<QuoteModel>>(){}.getType();
            list = gson.fromJson(json, listType);

        } catch (Exception e) {
            e.printStackTrace();
            // Error case में default quotes add करें
            loadDefaultQuotes();
        }
    }

    private void loadDefaultQuotes() {
        list.add(new QuoteModel("The only way to do great work is to love what you do.", "Steve Jobs"));
        list.add(new QuoteModel("Strive not to be a success, but rather to be of value.", "Albert Einstein"));
        list.add(new QuoteModel("The mind is everything. What you think you become.", "Buddha"));
    }

    private void showRandomQuote() {
        if (!list.isEmpty()) {
            currentIndex = random.nextInt(list.size());
            QuoteModel q = list.get(currentIndex);

            quoteText.setText(q.getText());
            authorText.setText("— " + q.getAuthor());
        }
    }
}