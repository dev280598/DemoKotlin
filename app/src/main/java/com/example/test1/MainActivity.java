package com.example.test1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.adapter.PostAdapter;
import com.example.test1.databinding.ActivityMainBinding;
import com.example.test1.model.Data;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private MainViewModel mainViewModel;
  private PostAdapter postAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding activityMainBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_main);

    RecyclerView recyclerView = activityMainBinding.viewPost;
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);

    mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    postAdapter = new PostAdapter();
    recyclerView.setAdapter(postAdapter);
    getAllEmployee();
  }

  private void getAllEmployee() {
    mainViewModel.getAllEmployee().observe(this, new Observer<List<Data>>() {
      @Override
      public void onChanged(@Nullable List<Data> post) {
        postAdapter.setEmployeeList((ArrayList<Data>) post);
      }
    });
  }
}
