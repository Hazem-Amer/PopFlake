package com.example.popflake.domain.repository;

import androidx.lifecycle.LiveData;

import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.data.remote.dto.MovieDetailsResponse;
import com.example.popflake.domain.model.Movie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Query;

public interface MoviesRepository {
    Observable<Response<ApiResponse>> getComingSoonMovies();

    Observable<Response<ApiResponse>> getOpeningMovies();

    Observable<Response<ApiResponse>> searchMovie(String movieName);

    Observable<Response<MovieDetailsResponse>> getMovieDetail(String emsVersionId);

    void insertMovie(List<Movie> movies);

    LiveData<List<Movie>> getAllMovies();

    Movie getMovieById(int movieId);

    void updateMovies(List<Movie> movies);

}
