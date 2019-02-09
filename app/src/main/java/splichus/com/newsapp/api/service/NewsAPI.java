package splichus.com.newsapp.api.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import splichus.com.newsapp.api.model.APIResponse;

public interface NewsAPI {

    @Headers("Accept: application/json")
    @GET("everything")
    Call<APIResponse> getEverything(@Header("X-Api-Key") String apiKey, @Query("pageSize") int pageSize);
}

