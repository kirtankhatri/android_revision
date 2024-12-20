package com.example.revision;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class PostViewHolder extends RecyclerView.ViewHolder{

    TextView postTitleTextView,postDescriptionTextView;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        postTitleTextView = itemView.findViewById(R.id.postTitle);
        postDescriptionTextView = itemView.findViewById(R.id.postDescription);
    }
}

public class CustomAdapter extends RecyclerView.Adapter<PostViewHolder> {

    Context context;
    PostResponseModel postResponseModel;

    public CustomAdapter(PostResponseModel postResponseModel,Context context){
        this.context=context;
        this.postResponseModel = postResponseModel;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.post_item,null,false);
        PostViewHolder postViewHolder = new PostViewHolder(v);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostsModel postsModel = postResponseModel.data.get(position);
        holder.postTitleTextView.setText(postsModel.getTitle());
        holder.postDescriptionTextView.setText(postsModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return postResponseModel.data.size();
    }
}
