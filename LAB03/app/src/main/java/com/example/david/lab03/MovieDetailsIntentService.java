package com.example.david.lab03;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.util.List;


public class MovieDetailsIntentService extends IntentService {

    private static String EXTRA_TITLE = "Extra_Title";
    public static final String ACTION_SHOW_NOTIFICATION =
            "com.example.david.lab03.MovieDetailsIntentService_SHOW_NOTIFICATION";

    public MovieDetailsIntentService(){
        super("MovieDetailsIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        OmdbFetchr omdbFetchr = new OmdbFetchr("922d558d");
        String title = intent.getStringExtra(EXTRA_TITLE);
        try {
            MovieDetails movieDetails = omdbFetchr.fetchMovieDetails(title);
            Intent i = new Intent(ACTION_SHOW_NOTIFICATION);
            Bundle extras = new Bundle();
            extras.putParcelable("MovieDetails",movieDetails);
            i.putExtras(extras);
            sendBroadcast(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Intent newIntent(Context packageContext, String title){
        Intent i = new Intent(packageContext,MovieDetailsIntentService.class);
        i.putExtra(EXTRA_TITLE,title);
        return i;
    }
}