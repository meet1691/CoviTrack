package com.meet.covitrack;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    // base url
    String BASE_URL = "https://disease.sh/v3/covid-19/";

    // use get method of retrofit
    // what we have to fetch that are we done here
    @GET("countries")
    Call<List<ModelClass>> getCountryData();
}
