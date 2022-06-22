package com.meet.covitrack;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// this code also be done in main Activity but this is good practice
public class ApiUtilities
{
    public static Retrofit retrofit=null ; //we declare null so by default it contain null / so when ever user want to create object it simply create new object
    public static ApiInterface getApiInterface(){

        if( retrofit==null)
                retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(ApiInterface.class);
    }
}
