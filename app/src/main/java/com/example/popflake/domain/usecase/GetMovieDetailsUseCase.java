package com.example.popflake.domain.usecase;

import com.example.popflake.data.remote.dto.MovieDetailsResponse;
import com.example.popflake.domain.repository.MoviesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

public class GetMovieDetailsUseCase {
    private MoviesRepository mMoviesRepository;
    @Inject
    public GetMovieDetailsUseCase(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }
    public Observable<Response<MovieDetailsResponse>> execute(String emsVersionId){
        return mMoviesRepository.getMovieDetail(emsVersionId);
    }
}
