package com.example.popflake.domain.usecase;

import androidx.lifecycle.LiveData;

import com.example.popflake.domain.model.Movie;
import com.example.popflake.domain.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class InsertMovieToDbUseCase {
    MoviesRepository mMoviesRepository;
    @Inject
    public InsertMovieToDbUseCase(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }
    public void execute(List<Movie> movies) {
        mMoviesRepository.insertMovie(movies);
    }


}
