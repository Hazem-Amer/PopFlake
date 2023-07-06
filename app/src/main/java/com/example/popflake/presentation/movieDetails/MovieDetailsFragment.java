package com.example.popflake.presentation.movieDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popflake.R;
import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.data.remote.dto.MovieDetailsResponse;
import com.example.popflake.presentation.AppAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@AndroidEntryPoint
public class MovieDetailsFragment extends Fragment {
    MovieDetailsViewModel mMovieDetailsViewModel;
    MaterialToolbar mMaterialToolbar;
    TextView mMovieNameTV, mMovieDescriptionTV, mMovieUserRatingCount, mMovieTomatoRatingCount,movieName,directedBy,duration,totalGross,releaseDate,availability;
    ImageView mMoviePoster, mMovieUserRatingIcon, mMovieTomatoRatingIcon;
    RecyclerView mMoviePhotosRecyclerView;
    AppAdapter mMoviePhotosAdapter;
    ShimmerFrameLayout mMoviePhotosShimmerLayout;
    private String mMovieId;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getClickedMovieId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        initializeViews(view);
        fetchRecyclerViewsRemote();
        listenToMoviePhotosUpdates();
        HandleButtonClick();
        handleSwipeForRefresh();
    }

    private void listenToMoviePhotosUpdates() {
        mMovieDetailsViewModel.getMoviesDetailsLiveData().observe(getViewLifecycleOwner(), new Observer<MovieDetailsResponse>() {
            @Override
            public void onChanged(MovieDetailsResponse movieDetailsResponse) {
                mMoviePhotosShimmerLayout.setVisibility(View.GONE);
                mMoviePhotosRecyclerView.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(false);
                setMovieDetailsPhotosRecyclerViewAdapter(movieDetailsResponse.getData().getMovie().getImages());
                showMovieInitialData(movieDetailsResponse.getData().getMovie().getName(),
                        movieDetailsResponse.getData().getMovie().getSynopsis());
                showMovieDetailsImages(movieDetailsResponse.getData().getMovie());
                showMovieDetails(movieDetailsResponse.getData().getMovie());
                showMovieDetailsRatings(movieDetailsResponse.getData().getMovie());
            }
        });
    }

    private void handleSwipeForRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchRecyclerViewsRemote();
            }
        });
    }

    private void HandleButtonClick() {
        mMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
    }

    private void initializeViews(View view) {
        mMaterialToolbar = view.findViewById(R.id.movieDetails_topAppBar);
        mMovieNameTV = view.findViewById(R.id.movieDetails_movieName_TV);
        mMovieDescriptionTV = view.findViewById(R.id.movieDetails_movieDescription_TV);
        mMovieUserRatingCount = view.findViewById(R.id.movieDetails_userRatingCount_Tv);
        mMovieTomatoRatingCount = view.findViewById(R.id.movieDetails_TomatoRatingCount_TV);
        mMoviePoster = view.findViewById(R.id.movieDetails_poster);
        mMovieUserRatingIcon = view.findViewById(R.id.movieDetails_userRatingIcon_IV);
        mMovieTomatoRatingIcon = view.findViewById(R.id.movieDetails_TomatoRatingIcon_IV);
        mMoviePhotosRecyclerView = view.findViewById(R.id.movieDetails_photos_RV);
        mMoviePhotosShimmerLayout = view.findViewById(R.id.movieDetails_photos_Shimmer);
        mSwipeRefreshLayout = view.findViewById(R.id.movieDetails_swipeRefreshLayout);
        movieName = view.findViewById(R.id.nameTextView_listItem);
        directedBy = view.findViewById(R.id.directedByTextView_listItem);
        duration = view.findViewById(R.id.durationTextView_listItem);
        totalGross = view.findViewById(R.id.totalGrossTextView_listItem);
        releaseDate = view.findViewById(R.id.releaseDateTextView_listItem);
        availability = view.findViewById(R.id.availabilityTextView_listItem);

    }

    private void fetchRecyclerViewsRemote() {
        if (mMovieId != null && mMovieId != null) {
            mMovieDetailsViewModel.getMovieDetails(mMovieId);
        }
    }


    private void setMovieDetailsPhotosRecyclerViewAdapter(List<ApiResponse.PosterImage> moviePhotos) {
        if (moviePhotos != null) {

            mMoviePhotosAdapter = new AppAdapter((ArrayList) moviePhotos, R.layout.movie_details_photos_list_item, null);
            mMoviePhotosRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            mMoviePhotosRecyclerView.setAdapter(mMoviePhotosAdapter);
        }
    }

    private void getClickedMovieId() {
        Bundle bundle = getArguments();
        if (bundle != null)
            mMovieId = bundle.getString("id");
    }


    public void showMovieInitialData(String name, String description) {
        mMovieNameTV.setText(name);
        mMaterialToolbar.setTitle(name);
        mMovieDescriptionTV.setText(description);
    }

    public void showMovieDetailsImages(MovieDetailsResponse.MovieDetails movieDetails) {
        if (movieDetails.getPosterImage() != null)
            Picasso.get().load(movieDetails.getPosterImage().getUrl()).into(mMoviePoster);
        if(movieDetails.getTomatoRating()!=null)
            Picasso.get().load(movieDetails.getTomatoRating().getIconImage().getUrl()).into(mMovieTomatoRatingIcon);
        if(movieDetails.getTomatoRating()!=null)
            Picasso.get().load(movieDetails.getUserRating().getIconImage().getUrl()).into(mMovieUserRatingIcon);
    }
    public void showMovieDetailsRatings(MovieDetailsResponse.MovieDetails movieDetails){
        if(movieDetails.getUserRating()!=null)
            mMovieUserRatingCount.setText(movieDetails.getUserRating().getDtlLikedScore());
        if(movieDetails.getTomatoRating()!=null)
            mMovieTomatoRatingCount.setText(movieDetails.getTomatoRating().getTomatometer());
    }
    public void showMovieDetails(MovieDetailsResponse.MovieDetails movieDetails){
        movieName.setText(movieDetails.getName());
        directedBy.setText(movieDetails.getDirectedBy());
        duration.setText(String.valueOf(movieDetails.getDurationMinutes()));
        availability.setText(movieDetails.getAvailabilityWindow());
        releaseDate.setText(movieDetails.getReleaseDate());
        totalGross.setText(movieDetails.getTotalGross());

    }

}