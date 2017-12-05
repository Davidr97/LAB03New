package com.example.david.lab03;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    @SerializedName("Search")
    private List<Movie> movies = new ArrayList<>();

    public List<Movie> getSearchResult(){
        return movies;
    }
}