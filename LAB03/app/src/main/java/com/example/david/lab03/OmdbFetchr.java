package com.example.david.lab03;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OmdbFetchr {

    private Map<String,String> attributes;

    public OmdbFetchr(String apikey){
        attributes = new HashMap<>();
        attributes.put("apikey",apikey);
    }

    private MoviesApi establishConnection(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MoviesApi.class);
    }

    public List<Movie> fetchMovies(String query) throws IOException{
        attributes.put("s",query);
        MoviesApi service = establishConnection();
        Call<SearchResult> call = service.getMovies(attributes);
        SearchResult result = call.execute().body();
        if(result!=null){
            return result.getSearchResult();
        }
        return null;
    }

    public MovieDetails fetchMovieDetails(String title) throws IOException{
        attributes.put("t",title);
        attributes.put("plot","full");
        MoviesApi service = establishConnection();
        Call<MovieDetails> call = service.getMovieDetails(attributes);
        MovieDetails movieDetails = call.execute().body();
        if(movieDetails != null){
            return movieDetails;
        }
        return null;
    }
}