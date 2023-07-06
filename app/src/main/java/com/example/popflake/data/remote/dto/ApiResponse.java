package com.example.popflake.data.remote.dto;
import com.example.popflake.domain.model.Movie;

import java.util.List;

public class ApiResponse {
    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<Movie> upcoming;
        private List<Movie> opening;
        private Search search;

        public Data(List<Movie> upcoming, List<Movie> opening, Search search) {
            this.upcoming = upcoming;
            this.opening = opening;
            this.search = search;
        }


        public Search getSearch() {
            return search;
        }

        public void setSearch(Search search) {
            this.search = search;
        }

        public List<Movie> getUpcoming() {
            return upcoming;
        }

        public void setUpcoming(List<Movie> upcoming) {
            this.upcoming = upcoming;
        }

        public List<Movie> getOpening() {
            return opening;
        }

        public void setOpening(List<Movie> opening) {
            this.opening = opening;
        }
    }



    public static class Search {
        private List<Movie> movies;

        public Search(List<Movie> movies) {
            this.movies = movies;
        }

        public List<Movie> getMovies() {
            return movies;
        }

        public void setMovies(List<Movie> movies) {
            this.movies = movies;
        }
    }

    public static class UserRating {
        private int dtlLikedScore = 0;
        private int ratingCount = 0;
        private int dtlWtsCount = 0;
        private int dtlWtsScore = 0;
        private int dtlScoreCount = 0;
        private IconImage iconImage;

        public UserRating(int dtlLikedScore, int ratingCount, int dtlWtsCount, int dtlWtsScore, int dtlScoreCount, IconImage iconImage) {
            this.dtlLikedScore = dtlLikedScore;
            this.ratingCount = ratingCount;
            this.dtlWtsCount = dtlWtsCount;
            this.dtlWtsScore = dtlWtsScore;
            this.dtlScoreCount = dtlScoreCount;
            this.iconImage = iconImage;
        }

        public int getDtlLikedScore() {
            return dtlLikedScore;
        }

        public void setDtlLikedScore(int dtlLikedScore) {
            this.dtlLikedScore = dtlLikedScore;
        }

        public int getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }

        public int getDtlWtsCount() {
            return dtlWtsCount;
        }

        public void setDtlWtsCount(int dtlWtsCount) {
            this.dtlWtsCount = dtlWtsCount;
        }

        public int getDtlWtsScore() {
            return dtlWtsScore;
        }

        public void setDtlWtsScore(int dtlWtsScore) {
            this.dtlWtsScore = dtlWtsScore;
        }

        public int getDtlScoreCount() {
            return dtlScoreCount;
        }

        public void setDtlScoreCount(int dtlScoreCount) {
            this.dtlScoreCount = dtlScoreCount;
        }

        public IconImage getIconImage() {
            return iconImage;
        }

        public void setIconImage(IconImage iconImage) {
            this.iconImage = iconImage;
        }
    }

    public static class IconImage {
        private String url;

        public IconImage(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class PosterImage {
        private String url;
        private String type;
        private String width;
        private String height;

        public PosterImage(String url, String type, String width, String height) {
            this.url = url;
            this.type = type;
            this.width = width;
            this.height = height;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TomatoRating {
        private int tomatometer = 0;
        private int ratingCount = 0;
        private IconImage iconImage;

        public TomatoRating(int tomatometer, int ratingCount, IconImage iconImage) {
            this.tomatometer = tomatometer;
            this.ratingCount = ratingCount;
            this.iconImage = iconImage;
        }

        public int getTomatometer() {
            return tomatometer;
        }

        public void setTomatometer(int tomatometer) {
            this.tomatometer = tomatometer;
        }

        public int getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }

        public IconImage getIconImage() {
            return iconImage;
        }

        public void setIconImage(IconImage iconImage) {
            this.iconImage = iconImage;
        }
    }
}
