package com.example.popflake.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popflake.R;
import com.example.popflake.data.remote.dto.ApiResponse;
import com.example.popflake.domain.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AppAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected int layoutResourceId;
    private MoviesRecyclerViewListener mMoviesRecyclerViewListener;
    private ArrayList<T> dataList;

    public ArrayList<T> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<T> dataList) {
        this.dataList = dataList;
    }

    public AppAdapter(ArrayList<T> dataList, int layoutResourceId,
                      MoviesRecyclerViewListener mMoviesRecyclerViewListener) {
        this.dataList = dataList;
        this.layoutResourceId = layoutResourceId;
        this.mMoviesRecyclerViewListener = mMoviesRecyclerViewListener;
    }
    public void clearAdapter(){
        dataList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutResourceId, parent, false);
        if (layoutResourceId == R.layout.movie_list_item)
            return new MovieItemViewHolder(view, mMoviesRecyclerViewListener,layoutResourceId);
        else if (layoutResourceId == R.layout.movie_details_photos_list_item)
            return new MovieDetailsPhotosViewHolder(view);
        else if(layoutResourceId == R.layout.opening_movie_list_item)
            return new OpeningMovieItemViewHolder(view, mMoviesRecyclerViewListener,layoutResourceId);
        else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieItemViewHolder) {
            Movie movie = (Movie) this.dataList.get(position);
            ((MovieItemViewHolder) holder).mMovieNameTv.setText(movie.getName());
            if (movie.getReleaseDate() != null) {
                ((MovieItemViewHolder) holder).mMovieReleaseDateTv.setText(movie.getReleaseDate());
            }
            if (movie.getPosterImage() != null) {
                Picasso.get().load(movie.getPosterImage().getUrl()).into(((MovieItemViewHolder) holder).mMoviePosterIv);
            }
            if (movie.getTomatoRating() != null && movie.getTomatoRating().getIconImage() != null) {
                Picasso.get().load(movie.getTomatoRating().getIconImage().getUrl()).into(((MovieItemViewHolder) holder).mRottenTomattoRatingIconIv);
            }
            if (movie.getUserRating() != null && movie.getUserRating().getIconImage() != null)
                Picasso.get().load(movie.getUserRating().getIconImage().getUrl()).into(((MovieItemViewHolder) holder).mUserRatingIconIv);
        }
        else if (holder instanceof MovieDetailsPhotosViewHolder) {
            ApiResponse.PosterImage posterImage = (ApiResponse.PosterImage) this.dataList.get(position);
            if (posterImage.getUrl() != null)
                Picasso.get().load(posterImage.getUrl())
                        .into(((MovieDetailsPhotosViewHolder) holder).movieDetailsPhoto);
        }
        else if(holder instanceof OpeningMovieItemViewHolder){
            Movie movie = (Movie) this.dataList.get(position);
            ((OpeningMovieItemViewHolder) holder).mMovieNameTv.setText(movie.getName());
            if (movie.getPosterImage() != null) {
                Picasso.get().load(movie.getPosterImage().getUrl()).into(((OpeningMovieItemViewHolder) holder).mMoviePosterIv);
            }
            if (movie.getTomatoRating() != null && movie.getTomatoRating().getIconImage() != null) {
                Picasso.get().load(movie.getTomatoRating().getIconImage().getUrl()).into(((OpeningMovieItemViewHolder) holder).mRottenTomattoRatingIconIv);
            }
            if (movie.getUserRating() != null && movie.getUserRating().getIconImage() != null)
                Picasso.get().load(movie.getUserRating().getIconImage().getUrl()).into(((OpeningMovieItemViewHolder) holder).mUserRatingIconIv);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MovieDetailsPhotosViewHolder extends RecyclerView.ViewHolder {
        private ImageView movieDetailsPhoto;

        public MovieDetailsPhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            movieDetailsPhoto = itemView.findViewById(R.id.movieDetails_photos_listItem_IV);
        }
    }

    public static class MovieItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mMoviePosterIv, mRottenTomattoRatingIconIv, mUserRatingIconIv;
        TextView mMovieNameTv, mMovieReleaseDateTv;

        public MovieItemViewHolder(@NonNull View itemView, MoviesRecyclerViewListener moviesRecyclerViewListener,int layoutResource) {
            super(itemView);
            mMoviePosterIv = itemView.findViewById(R.id.MoviePoster_IV);
            mRottenTomattoRatingIconIv = itemView.findViewById(R.id.rottenTomattoRating_IV);
            mUserRatingIconIv = itemView.findViewById(R.id.userRating_IV);
            mMovieNameTv = itemView.findViewById(R.id.movieName_Tv);
            mMovieReleaseDateTv = itemView.findViewById(R.id.movieReleaseDate_Tv);
            if (moviesRecyclerViewListener != null) {
                mMoviePosterIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION)
                            moviesRecyclerViewListener.onMoviePosterClick(getAdapterPosition(),layoutResource);
                    }
                });
            }
        }

    }
    public static class OpeningMovieItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mMoviePosterIv, mRottenTomattoRatingIconIv, mUserRatingIconIv;
        TextView mMovieNameTv;

        public OpeningMovieItemViewHolder(@NonNull View itemView, MoviesRecyclerViewListener moviesRecyclerViewListener,int layoutResource) {
            super(itemView);
            mMoviePosterIv = itemView.findViewById(R.id.MoviePoster_IV);
            mRottenTomattoRatingIconIv = itemView.findViewById(R.id.rottenTomattoRating_IV);
            mUserRatingIconIv = itemView.findViewById(R.id.userRating_IV);
            mMovieNameTv = itemView.findViewById(R.id.movieName_Tv);
            if (moviesRecyclerViewListener != null) {
                mMoviePosterIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION)
                            moviesRecyclerViewListener.onMoviePosterClick(getAdapterPosition(),layoutResource);
                    }
                });
            }
        }

    }

}
