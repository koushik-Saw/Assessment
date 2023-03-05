package com.example.assesment.api;





import com.example.assesment.model.RatesModel;
import com.example.assesment.model.SymModel;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("symbols")
    @Headers("apikey: vAc6t2qHmPugaumCvdqkTWg7BQPmGfSu")
    Call<SymModel> getSymbolList();

    @GET("latest")
    @Headers("apikey: vAc6t2qHmPugaumCvdqkTWg7BQPmGfSu")
    Call<RatesModel> geSymModelCall(@Query("symbols") String symbol, @Query("base") String base);

}
