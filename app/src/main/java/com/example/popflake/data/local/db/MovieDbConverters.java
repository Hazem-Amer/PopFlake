package com.example.popflake.data.local.db;

import androidx.room.TypeConverter;

import com.example.popflake.data.remote.dto.ApiResponse;
import com.google.gson.Gson;

public class MovieDbConverters {
    @TypeConverter
    public String fromPosterImageToString(ApiResponse.PosterImage posterImage){
        return new Gson().toJson(posterImage);
    }
    @TypeConverter
    public ApiResponse.PosterImage fromStringToPosterImage(String poster){
        return new Gson().fromJson(poster, ApiResponse.PosterImage.class);
    }

    @TypeConverter
    public String fromTomatoRatingToString(ApiResponse.TomatoRating tomatoRating){
        return new Gson().toJson(tomatoRating);
    }
    @TypeConverter
    public ApiResponse.TomatoRating fromStringToTomatoRating(String tomatoRating){
        return new Gson().fromJson(tomatoRating, ApiResponse.TomatoRating.class);
    }

    @TypeConverter
    public String fromUserRatingToString(ApiResponse.UserRating userRating){
        return new Gson().toJson(userRating);
    }
    @TypeConverter
    public ApiResponse.UserRating fromStringToUserRating(String userRating){
        return new Gson().fromJson(userRating, ApiResponse.UserRating.class);
    }

    @TypeConverter
    public String fromIconImageToString(ApiResponse.IconImage iconImage){
        return new Gson().toJson(iconImage);
    }
    @TypeConverter
    public ApiResponse.IconImage fromStringToIconImage(String iconImage){
        return new Gson().fromJson(iconImage, ApiResponse.IconImage.class);
    }

}
