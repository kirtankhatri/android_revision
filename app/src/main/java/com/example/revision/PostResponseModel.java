package com.example.revision;

import java.util.List;

public class PostResponseModel extends BaseResponseModel{
    List<PostsModel> data;

    public PostResponseModel(boolean success,String message,List<PostsModel> data){
        super(success,message);
        this.data = data;
    }

    public List<PostsModel> getData() {
        return data;
    }

    public void setData(List<PostsModel> data) {
        this.data = data;
    }
}
