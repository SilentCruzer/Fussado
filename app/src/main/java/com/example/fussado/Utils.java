package com.example.fussado;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.constraintlayout.utils.widget.MockView;

import com.example.fussado.Model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String ALL_MOVIES_KEY = "all_movies";
    private static final String ALL_TV_SHOWS_KEY = "all_tv_shows";
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALL_GAMES_KEY = "all_games";

    private SharedPreferences sharedPreferences;
    private static Utils instance;

    private Utils(Context context){
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        if(getMovies() == null){
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(getTvShows() == null){
            ArrayList<Movie> tvShows = new ArrayList<>();
            editor.putString(ALL_TV_SHOWS_KEY, gson.toJson(tvShows));
            editor.commit();
        }

        if(getBooks() == null){
            ArrayList<Movie> books = new ArrayList<>();
            editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
            editor.commit();
        }

        if(getGames() == null){
            ArrayList<Movie> games = new ArrayList<>();
            editor.putString(ALL_GAMES_KEY, gson.toJson(games));
            editor.commit();
        }

    }

    private void initData(){
        ArrayList<Movie> movies = new ArrayList<>();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_MOVIES_KEY,gson.toJson(movies));
        editor.commit();
    }

    public ArrayList<Movie> getMovies(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        ArrayList<Movie> movies = gson.fromJson(sharedPreferences.getString(ALL_MOVIES_KEY, null),type);
        return movies;
    }

    public ArrayList<Movie> getTvShows(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        ArrayList<Movie> tvShows= gson.fromJson(sharedPreferences.getString(ALL_TV_SHOWS_KEY, null),type);
        return tvShows;
    }

    public ArrayList<Movie> getBooks(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        ArrayList<Movie> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null),type);
        return books;
    }

    public ArrayList<Movie> getGames(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
        ArrayList<Movie> games = gson.fromJson(sharedPreferences.getString(ALL_GAMES_KEY, null),type);
        return games;
    }

    public  boolean addToMovies(Movie movie){
        ArrayList<Movie> movies = getMovies();
        if(movies != null){
            if(movies.add(movie)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALL_MOVIES_KEY);
                editor.putString(ALL_MOVIES_KEY, gson.toJson(movies));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromMovies(Movie movie){
        ArrayList<Movie> movies = getMovies();
        if(movies != null){
            for(Movie b: movies){
                if(b.getId().equals(movie.getId())){
                    if(movies.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALL_MOVIES_KEY);
                        editor.putString(ALL_MOVIES_KEY, gson.toJson(movies));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public  boolean addToTvShows(Movie tv){
        ArrayList<Movie> tvShows = getTvShows();
        if(tvShows != null){
            if(tvShows.add(tv)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALL_TV_SHOWS_KEY);
                editor.putString(ALL_TV_SHOWS_KEY, gson.toJson(tvShows));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromTvShows(Movie tv){
        ArrayList<Movie> tvShows = getTvShows();
        if(tvShows != null){
            for(Movie b: tvShows){
                if(b.getId().equals(tv.getId())){
                    if(tvShows.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALL_TV_SHOWS_KEY);
                        editor.putString(ALL_TV_SHOWS_KEY, gson.toJson(tvShows));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public  boolean addToBooks(Movie book){
        ArrayList<Movie> books = getBooks();
        if(books != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALL_BOOKS_KEY);
                editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromBooks(Movie book){
        ArrayList<Movie> books = getBooks();
        if(books != null){
            for(Movie b: books){
                if(b.getId().equals(book.getId())){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALL_BOOKS_KEY);
                        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public  boolean addToGames(Movie game){
        ArrayList<Movie> games = getGames();
        if(games != null){
            if(games.add(game)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALL_GAMES_KEY);
                editor.putString(ALL_GAMES_KEY, gson.toJson(games));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromGames(Movie game){
        ArrayList<Movie> games = getBooks();
        if(games != null){
            for(Movie b: games){
                if(b.getId().equals(game.getId())){
                    if(games.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALL_GAMES_KEY);
                        editor.putString(ALL_GAMES_KEY, gson.toJson(games));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Utils getInstance(Context context){
        if(null != instance){
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }
}
