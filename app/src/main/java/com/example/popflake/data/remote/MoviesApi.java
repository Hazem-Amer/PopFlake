package com.example.popflake.data.remote;

import static com.example.popflake.UTILS.Constants.GET_COMING_SOON_MOVIES_PATH;
import static com.example.popflake.UTILS.Constants.GET_MOVIE_DETAIL_BY_emsVersionId;
import static com.example.popflake.UTILS.Constants.GET_OPENING_MOVIES_PATH;
import static com.example.popflake.UTILS.Constants.SEARCH_MOVIE_PATH;

import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.data.remote.dto.MovieDetailsResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET(GET_COMING_SOON_MOVIES_PATH)
    Observable<Response<ApiResponse>>getComingSoonMovies();
    @GET(GET_OPENING_MOVIES_PATH)
    Observable<Response<ApiResponse>>getOpeningMovies();
    @GET(SEARCH_MOVIE_PATH)
    Observable<Response<ApiResponse>>searchMovie(@Query("query") String movieName);

    @GET(GET_MOVIE_DETAIL_BY_emsVersionId)
    Observable<Response<MovieDetailsResponse>>getMovieDetail(@Query("emsVersionId") String emsVersionId);

}
