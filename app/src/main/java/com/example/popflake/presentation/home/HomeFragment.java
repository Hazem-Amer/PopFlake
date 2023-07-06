package com.example.popflake.presentation.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popflake.R;
import com.example.popflake.UTILS.NavigationUtils;
import com.example.popflake.domain.model.Movie;
import com.example.popflake.presentation.AppAdapter;
import com.example.popflake.presentation.MoviesRecyclerViewListener;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements MoviesRecyclerViewListener {
    HomeFragmentViewModel mHomeFragmentViewModel;
    private AppAdapter mComingSoonAdapter, mOpeningAdapter;
    private RecyclerView mComingSoonRecyclerView, mOpeningRecyclerView;
    private ShimmerFrameLayout mComingSoonShimmerFrameLayout, mOpeningShimmerFrameLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);
        fetchRecyclerViewsRemote();
        listenComingSoonMoviesChanges();
        listenOpeningMoviesChanges();
        handleSwipeForRefresh();
    }


    private void fetchRecyclerViewsRemote() {
        mHomeFragmentViewModel.getComingSoonMoviesRemote();
        mHomeFragmentViewModel.getOpeningMoviesRemote();
    }


    private void listenComingSoonMoviesChanges() {

            mHomeFragmentViewModel.mComingSoonMovies.observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
                @Override
                public void onChanged(ArrayList<Movie> movies) {
                    mHomeFragmentViewModel.updateMovies(movies);
                    mComingSoonRecyclerView.setVisibility(View.VISIBLE);
                    mComingSoonShimmerFrameLayout.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(false);
                    mComingSoonAdapter = new AppAdapter((ArrayList) movies,
                            R.layout.movie_list_item, HomeFragment.this);
                    mComingSoonRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                            LinearLayoutManager.HORIZONTAL, false));
                    mComingSoonRecyclerView.setAdapter(mComingSoonAdapter);

                }
            });


    }

    private void listenOpeningMoviesChanges() {

        mHomeFragmentViewModel.mOpeningMovies.observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mOpeningRecyclerView.setVisibility(View.VISIBLE);
                mOpeningShimmerFrameLayout.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);
                mOpeningAdapter = new AppAdapter((ArrayList) movies, R.layout.opening_movie_list_item, HomeFragment.this);
                mOpeningRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                        LinearLayoutManager.HORIZONTAL, false));
                mOpeningRecyclerView.setAdapter(mOpeningAdapter);
            }
        });


    }


    private void initializeView(View view) {
        mComingSoonRecyclerView = view.findViewById(R.id.comingSoonMovies_RV);
        mOpeningRecyclerView = view.findViewById(R.id.openingMovies_RV);
        mComingSoonShimmerFrameLayout = view.findViewById(R.id.comingSoonMovies_Shimmer);
        mOpeningShimmerFrameLayout = view.findViewById(R.id.openingMovies_Shimmer);
        mSwipeRefreshLayout = view.findViewById(R.id.home_swipeRefreshLayOut);
    }



    @Override
    public void onMoviePosterClick(int position, int layoutResource) {
        if(layoutResource == R.layout.movie_list_item){
            NavigationUtils.openMovieDetailsFragment(mComingSoonAdapter,position,requireActivity());
        }else if(layoutResource == R.layout.opening_movie_list_item){
            NavigationUtils.openMovieDetailsFragment(mOpeningAdapter,position,requireActivity());
        }

    }


    private void handleSwipeForRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchRecyclerViewsRemote();
            }
        });
    }
}