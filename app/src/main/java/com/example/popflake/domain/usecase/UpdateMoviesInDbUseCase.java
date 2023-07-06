package com.example.popflake.domain.usecase;

import com.example.popflake.domain.model.Movie;
import com.example.popflake.domain.repository.MoviesRepository;
import java.util.List;
import javax.inject.Inject;

public class UpdateMoviesInDbUseCase {
    MoviesRepository mMoviesRepository;
    @Inject
    public UpdateMoviesInDbUseCase(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }
    public void execute(List<Movie> movies) {
        mMoviesRepository.updateMovies(movies);
    }
}
