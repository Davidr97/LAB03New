package com.example.david.lab03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView mTextViewMovieTitle;
    private TextView mTextViewMovieYear;
    private TextView mTextViewMovieGenre;
    private TextView mTextViewMovieActors;
    private TextView mTextViewMoviePlot;
    private ImageView mImageViewMoviePoster;

    private static final String EXTRA_TITLE="EXTRA_TITLE";
    private BroadcastReceiver onShowMovieNotification = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            MovieDetails movieDetails = extras.getParcelable("MovieDetails");
            mTextViewMovieTitle = findViewById(R.id.text_view_movie_title);
            mTextViewMovieTitle.setText(movieDetails.Title);
            mTextViewMovieYear = findViewById(R.id.text_view_movie_year);
            mTextViewMovieYear.setText(movieDetails.Year);
            mTextViewMovieGenre = findViewById(R.id.text_view_movie_genre);
            mTextViewMovieGenre.setText(movieDetails.Genre);
            mTextViewMovieActors = findViewById(R.id.text_view_movie_actors);
            mTextViewMovieActors.setText(movieDetails.Actors);
            mTextViewMoviePlot = findViewById(R.id.text_view_movie_plot);
            mTextViewMoviePlot.setText(movieDetails.Plot);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            setContentView(R.layout.activity_movie_details);
            Intent i = MovieDetailsIntentService.newIntent(getApplicationContext(),title);
            startService(i);
        }
        else{
            setContentView(R.layout.activity_movie_details_secondary);
            mImageViewMoviePoster = findViewById(R.id.image_view_movie_poster_secondary);
            mTextViewMovieTitle = findViewById(R.id.text_view_movie_title_secondary);
            mTextViewMovieYear = findViewById(R.id.text_view_movie_year_secondary);
            DBMovieFetchr movieFetchr = new DBMovieFetchr(title,mImageViewMoviePoster,mTextViewMovieTitle,mTextViewMovieYear);
            movieFetchr.execute(getApplicationContext());
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(MovieDetailsIntentService.ACTION_SHOW_NOTIFICATION);
        registerReceiver(onShowMovieNotification,filter);
    }
    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(onShowMovieNotification);
    }

    public static Intent newIntent(Context packageContext,String title){
        Intent i = new Intent(packageContext,MovieDetailsActivity.class);
        i.putExtra(EXTRA_TITLE,title);
        return i;
    }
}
