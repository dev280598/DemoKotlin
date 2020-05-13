package com.example.test1.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;

import com.example.test1.databinding.PostListItemBinding;
import com.example.test1.model.Data;

import java.util.ArrayList;

public class PostAdapter
    extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
  private ArrayList<Data> post;
  @NonNull
  @Override
  public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    PostListItemBinding postListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.post_list_item, viewGroup, false);
    return new PostViewHolder(postListItemBinding);
  }
  @Override
  public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
    Data currentStudent = post.get(i);
    postViewHolder.employeeListItemBinding.setEmployee(currentStudent);
  }
  @Override
  public int getItemCount() {
    if (post != null) {
      return post.size();
    } else {
      return 0;
    }
  }
  public void setEmployeeList(ArrayList<Data> post) {
    this.post = post;
    notifyDataSetChanged();
  }
  class PostViewHolder extends RecyclerView.ViewHolder {
    private PostListItemBinding employeeListItemBinding;
    public PostViewHolder(@NonNull PostListItemBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());
      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}