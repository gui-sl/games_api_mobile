package com.example.gamesapi;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private ArrayList<GameModelList> games;

    public GameAdapter(ArrayList<GameModelList> dataSet) {
        games = dataSet;
    }


    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_game, parent, false);
        return new GameViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        double rating = Double.parseDouble(games.get(position).getRating());
        String dateString = games.get(position).getReleased();

        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = inputFormatter.parse(dateString);
            String formattedDate = outputFormatter.format(date);
            holder.getGameYearView().setText(formattedDate);
            Log.i("data", formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (rating > 4) {
            holder.gameRatingTextView.setBackgroundColor(Color.parseColor("#008000"));
        } else if (rating > 3) {
            holder.gameRatingTextView.setBackgroundColor(Color.parseColor("#90EE90"));
            holder.gameRatingTextView.setTextColor(Color.parseColor("#000000"));
        } else if (rating > 2) {
            holder.gameRatingTextView.setBackgroundColor(Color.parseColor("#FFFF00"));
            holder.gameRatingTextView.setTextColor(Color.parseColor("#000000"));
        } else if (rating > 1) {
            holder.gameRatingTextView.setBackgroundColor(Color.parseColor("#FFA500"));
            holder.gameRatingTextView.setTextColor(Color.parseColor("#000000"));
        } else {
            holder.gameRatingTextView.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        holder.getGameNameTextView().setText(games.get(position).getName());
        holder.getGameRatingTextView().setText(games.get(position).getRating());
        Picasso.get().load(games.get(position).getBackground_image()).into(holder.imageViewPoster);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        private TextView gameNameTextView;
        private TextView gameYearTextView;
        private TextView gameRatingTextView;
        private ImageView imageViewPoster;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameNameTextView = itemView.findViewById(R.id.gameNameTextView);
            gameYearTextView = itemView.findViewById(R.id.gameYearTextView);
            gameRatingTextView = itemView.findViewById(R.id.gameRatingTextView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        }

        public TextView getGameNameTextView(){
            return gameNameTextView;
        }
        public TextView getGameYearView(){return gameYearTextView;}
        public TextView getGameRatingTextView(){return gameRatingTextView;}

    }
}

