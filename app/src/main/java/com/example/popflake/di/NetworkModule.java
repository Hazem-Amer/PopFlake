package com.example.popflake.di;

import static com.example.popflake.UTILS.Constants.API_HOST_KEY_HEADER;
import static com.example.popflake.UTILS.Constants.API_HOST_VALUE_HEADER;
import static com.example.popflake.UTILS.Constants.API_KEY_HEADER;
import static com.example.popflake.UTILS.Constants.API_VALUE_HEADER;
import static com.example.popflake.UTILS.Constants.BASE_URL;

import com.example.popflake.UTILS.Constants;
import com.example.popflake.data.remote.MoviesApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        request = request.newBuilder()
                                .addHeader(API_KEY_HEADER, API_VALUE_HEADER)
                                .addHeader(API_HOST_KEY_HEADER, API_HOST_VALUE_HEADER)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @Provides
    @Singleton
    public MoviesApi getMoviesApi(Retrofit retrofit) {
        return retrofit.create(MoviesApi.class);
    }

}
