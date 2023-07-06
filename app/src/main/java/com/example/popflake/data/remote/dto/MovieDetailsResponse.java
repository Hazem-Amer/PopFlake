package com.example.popflake.data.remote.dto;

import java.util.List;

public class MovieDetailsResponse {
    MovieDetailsData data;

    public MovieDetailsData getData() {
        return data;
    }

    public void setData(MovieDetailsData data) {
        this.data = data;
    }

    public static class MovieDetailsData {
        MovieDetails movie;

        public MovieDetails getMovie() {
            return movie;
        }

        public void setMovie(MovieDetails movie) {
            this.movie = movie;
        }
    }

    public static class MovieDetails {
        private String emsId;
        private String fandangoId;
        private String rtMovieId;
        private String name;
        private int durationMinutes;
        private String synopsis;
        private String directedBy;
        private String releaseDate;
        private String showReleaseDate;
        private String availabilityWindow;
        private String ovdReleaseDate;
        private String totalGross;
        private ApiResponse.PosterImage posterImage;
        private ApiResponse.UserRating userRating;
        private ApiResponse.TomatoRating tomatoRating;
        private List<ApiResponse.PosterImage> images;

        public String getEmsId() {
            return emsId;
        }

        public void setEmsId(String emsId) {
            this.emsId = emsId;
        }

        public String getFandangoId() {
            return fandangoId;
        }

        public void setFandangoId(String fandangoId) {
            this.fandangoId = fandangoId;
        }

        public String getRtMovieId() {
            return rtMovieId;
        }

        public void setRtMovieId(String rtMovieId) {
            this.rtMovieId = rtMovieId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDurationMinutes() {
            return durationMinutes;
        }

        public void setDurationMinutes(int durationMinutes) {
            this.durationMinutes = durationMinutes;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getDirectedBy() {
            return directedBy;
        }

        public void setDirectedBy(String directedBy) {
            this.directedBy = directedBy;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getShowReleaseDate() {
            return showReleaseDate;
        }

        public void setShowReleaseDate(String showReleaseDate) {
            this.showReleaseDate = showReleaseDate;
        }

        public String getAvailabilityWindow() {
            return availabilityWindow;
        }

        public void setAvailabilityWindow(String availabilityWindow) {
            this.availabilityWindow = availabilityWindow;
        }

        public String getOvdReleaseDate() {
            return ovdReleaseDate;
        }

        public void setOvdReleaseDate(String ovdReleaseDate) {
            this.ovdReleaseDate = ovdReleaseDate;
        }

        public String getTotalGross() {
            return totalGross;
        }

        public void setTotalGross(String totalGross) {
            this.totalGross = totalGross;
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

        public List<ApiResponse.PosterImage> getImages() {
            return images;
        }

        public void setImages(List<ApiResponse.PosterImage> images) {
            this.images = images;
        }
    }


}
