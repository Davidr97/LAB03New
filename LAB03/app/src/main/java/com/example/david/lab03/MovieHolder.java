package com.example.david.lab03;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieHolder  extends RecyclerView.ViewHolder {


    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieYear;
    private Context context;

    public MovieHolder(View itemView) {
        super(itemView);
        moviePoster = itemView.findViewById(R.id.moviePoster);
        movieTitle = itemView.findViewById(R.id.movieTitle);
        movieYear = itemView.findViewById(R.id.movieYear);
        this.context = itemView.getContext();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(MovieDetailsActivity.newIntent(context,movieTitle.getText().toString()));
            }
        });
    }

    public void bindMovie(Movie movie) {
        Picasso.with(context)
                .load(movie.Poster)
                .into(moviePoster);
        movieTitle.setText(movie.Title);
        movieYear.setText(movie.Year);
    }
}