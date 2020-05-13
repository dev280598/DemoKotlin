package com.example.test1.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
  private static Retrofit retrofit;
  private static final String BASE_URL ="https://test-es-api.hahalolo.com/";

  public static APIService getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit
          .Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }

    return retrofit.create(APIService.class);
  }
}