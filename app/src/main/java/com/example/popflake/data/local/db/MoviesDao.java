package com.example.popflake.data.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.popflake.domain.model.Movie;

import java.util.List;

@Dao
public interface MoviesDao {
    @Insert
    void insertMovie(List<Movie> movies);
    @Query("select * from Movie_Table")
    LiveData<List<Movie>> getAllMovies();
    @Query("SELECT * FROM Movie_Table WHERE emsVersionId=:movieId")
    Movie getMovieById(int movieId);
    @Update
    void updateMovies(List<Movie> movies);

}
