package com.makaroni.chucknorrisjokes.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NorrisApi {
    @GET("random/{count}")
    Call<JokesArray> getJokes(@Path("count") int count);
}
