package com.example.popflake.domain.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.example.popflake.data.remote.dto.ApiResponse;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Movie_Table")
public class Movie {
    @Ignore
    private String emsId;
    @PrimaryKey
    @NotNull
    private String emsVersionId;
    private String name;
    private ApiResponse.PosterImage posterImage;
    private ApiResponse.UserRating userRating;
    private ApiResponse.TomatoRating tomatoRating;
    @Ignore
    private String releaseDate;
    @Ignore
    private String sortEms;

    public Movie(String emsId, String emsVersionId, String name, ApiResponse.PosterImage posterImage,
                 ApiResponse.UserRating userRating, ApiResponse.TomatoRating tomatoRating, String releaseDate, String sortEms) {
        this.emsId = emsId;
        this.emsVersionId = emsVersionId;
        this.name = name;
        this.posterImage = posterImage;
        this.userRating = userRating;
        this.tomatoRating = tomatoRating;
        this.releaseDate = releaseDate;
        this.sortEms = sortEms;
    }

    public Movie(String emsVersionId, String name, ApiResponse.PosterImage posterImage, ApiResponse.UserRating userRating, ApiResponse.TomatoRating tomatoRating) {
        this.emsVersionId = emsVersionId;
        this.name = name;
        this.posterImage = posterImage;
        this.userRating = userRating;
        this.tomatoRating = tomatoRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "emsId='" + emsId + '\'' +
                ", emsVersionId='" + emsVersionId + '\'' +
                ", name='" + name + '\'' +
                ", posterImage=" + posterImage +
                ", userRating=" + userRating +
                ", tomatoRating=" + tomatoRating +
                ", releaseDate='" + releaseDate + '\'' +
                ", sortEms='" + sortEms + '\'' +
                '}';
    }

    public String getEmsId() {
        return emsId;
    }

    public void setEmsId(String emsId) {
        this.emsId = emsId;
    }

    public String getEmsVersionId() {
        return emsVersionId;
    }

    public void setEmsVersionId(String emsVersionId) {
        this.emsVersionId = emsVersionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiResponse.PosterImage getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(ApiResponse.PosterImage posterImage) {
        this.posterImage = posterImage;
    }

    public ApiResponse.UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(ApiResponse.UserRating userRating) {
        this.userRating = userRating;
    }

    public ApiResponse.TomatoRating getTomatoRating() {
        return tomatoRating;
    }

    public void setTomatoRating(ApiResponse.TomatoRating tomatoRating) {
        this.tomatoRating = tomatoRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSortEms() {
        return sortEms;
    }

    public void setSortEms(String sortEms) {
        this.sortEms = sortEms;
    }
}