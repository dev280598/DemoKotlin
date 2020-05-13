package com.example.test1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.test1.model.Data;
import com.example.test1.model.PostRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
  private PostRepository postRepository;

  public MainViewModel(@NonNull Application application) {
    super(application);
    postRepository = new PostRepository();
  }

  public LiveData<List<Data>> getAllEmployee() {
    return postRepository.getMutableLiveData();
  }
}