package com.example.popflake.presentation.movieDetails;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.data.remote.dto.MovieDetailsResponse;
import com.example.popflake.domain.usecase.GetMovieDetailsUseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@HiltViewModel
public class MovieDetailsViewModel extends ViewModel {
    GetMovieDetailsUseCase mGetMovieDetailsUseCase;
    private MutableLiveData<MovieDetailsResponse> moviesDetailsLiveData = new MutableLiveData<>();

    @Inject
    public MovieDetailsViewModel(GetMovieDetailsUseCase getMovieDetailsUseCase) {
        mGetMovieDetailsUseCase = getMovieDetailsUseCase;
    }


    public MutableLiveData<MovieDetailsResponse> getMoviesDetailsLiveData() {
        return moviesDetailsLiveData;
    }

    public void setMoviesDetailsLiveData(MutableLiveData<MovieDetailsResponse> moviesDetailsLiveData) {
        this.moviesDetailsLiveData = moviesDetailsLiveData;
    }

    public void getMovieDetails(String emsVersionId) {
            mGetMovieDetailsUseCase.execute(emsVersionId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(res -> {
                        if (res.code() == 200) {
                            Log.d("Coming soon movies", "success 200");
                            MovieDetailsResponse movieDetailsResponse = res.body();
                            moviesDetailsLiveData.setValue(movieDetailsResponse);
                        } else {
                            Log.d("Coming soon movies", "code not 200: " + res.code());
                        }
                    }, err -> {
                        Log.d("Coming soon movies", "error in response", err);
                    });
        }

}
