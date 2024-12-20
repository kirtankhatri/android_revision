package com.example.revision;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface ApiService {
    @GET("list_posts.php")
    Call<PostResponseModel> getListOfPosts();

    @POST("add_posts.php")
    @FormUrlEncoded
    Call<BaseResponseModel> addPost(@Field("title") String postTitle,@Field("description") String postDescription);
}
