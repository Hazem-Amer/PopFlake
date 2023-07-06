package com.example.popflake.data.repository;

import androidx.lifecycle.LiveData;

import com.example.popflake.data.local.db.MoviesDao;
import com.example.popflake.data.remote.MoviesApi;
import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.data.remote.dto.MovieDetailsResponse;
import com.example.popflake.domain.model.Movie;
import com.example.popflake.domain.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

public class MoviesRepositoryImpl implements MoviesRepository {
    MoviesApi mMoviesApi;
    MoviesDao mMoviesDao;

    @Inject
    public MoviesRepositoryImpl(MoviesApi moviesApi, MoviesDao mMoviesDao) {
        this.mMoviesApi = moviesApi;
        this.mMoviesDao = mMoviesDao;
    }

    @Override
    public Observable<Response<ApiResponse>> getComingSoonMovies() {
        return mMoviesApi.getComingSoonMovies();
    }

    @Override
    public Observable<Response<ApiResponse>> getOpeningMovies() {
        return mMoviesApi.getOpeningMovies();
    }

    @Override
    public Observable<Response<ApiResponse>> searchMovie(String movieName) {
        return mMoviesApi.searchMovie(movieName);
    }

    @Override
    public Observable<Response<MovieDetailsResponse>> getMovieDetail(String emsVersionId) {
        return mMoviesApi.getMovieDetail(emsVersionId);
    }

    @Override
    public void insertMovie(List<Movie> movies) {
        mMoviesDao.insertMovie(movies);
    }

    @Override
    public LiveData<List<Movie>> getAllMovies() {
        return mMoviesDao.getAllMovies();
    }

    @Override
    public Movie getMovieById(int movieId) {
        return mMoviesDao.getMovieById(movieId);
    }

    @Override
    public void updateMovies(List<Movie> movies) {
        mMoviesDao.updateMovies(movies);
    }
}
