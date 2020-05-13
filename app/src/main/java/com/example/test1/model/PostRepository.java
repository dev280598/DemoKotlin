package com.example.test1.model;

import androidx.lifecycle.MutableLiveData;

import com.example.test1.Retrofit.APIClient;
import com.example.test1.Retrofit.APIService;
import com.example.test1.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
  private ArrayList<Data> post = new ArrayList<>();
  private MutableLiveData<List<Data>> mutableLiveData = new MutableLiveData<>();

  public PostRepository() {
  }

  public MutableLiveData<List<Data>> getMutableLiveData() {

    final APIService userDataService = APIClient.getClient();

    Call<Post> call = userDataService.getPost();
    call.enqueue(new Callback<Post>() {
      @Override
      public void onResponse(Call<Post> call, Response<Post> response) {
        Post Post = response.body();
        if (Post != null && Post.getHits().getHits() != null) {
          post = (ArrayList<Data>) Util.convertData(Post.getHits().getHits());
          mutableLiveData.setValue(post);
        }
      }

      @Override
      public void onFailure(Call<Post> call, Throwable t) {
      }
    });

    return mutableLiveData;
  }
}
