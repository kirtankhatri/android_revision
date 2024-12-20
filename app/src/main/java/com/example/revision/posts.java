package com.example.revision;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class posts extends AppCompatActivity {

    RecyclerView postsListView;
    ApiService apiService;
    CustomAdapter customAdapter;
    Button addPostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_posts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        postsListView = findViewById(R.id.recyclerView);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        addPostBtn = findViewById(R.id.addPosts);

        addPostBtn.setOnClickListener((e)->{
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(posts.this);
            View v = LayoutInflater.from(posts.this).inflate(R.layout.add_update_post,null);
            Button closeBtn = v.findViewById(R.id.closeBtn);
            Button submitBtn = v.findViewById(R.id.submitBtn);
            EditText titleInput = v.findViewById(R.id.postTitleInput);
            EditText descriptionInput = v.findViewById(R.id.postDescriptionInput);
            bottomSheetDialog.setContentView(v);
            bottomSheetDialog.show();

            submitBtn.setOnClickListener((e2)->{
                String titleText = titleInput.getText().toString();
                String descriptionText = descriptionInput.getText().toString();
                Log.d("debug","---------- 1");
                if(titleText.isEmpty() || descriptionText.isEmpty()){
                Log.d("debug","---------- 2");
                    Toast.makeText(posts.this, "Invalid form values", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("debug","---------- 3");
                addPost(titleText,descriptionText,titleInput,descriptionInput);
            });

            closeBtn.setOnClickListener((e2)->{
                bottomSheetDialog.hide();
            });
        });

        getPosts();
    }

    private void getPosts(){
        apiService.getListOfPosts().enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                    Log.d("success",call.request().url().toString());
                if(response.isSuccessful()){
                    PostResponseModel responseModel = response.body();
                    customAdapter = new CustomAdapter(responseModel,getApplicationContext());
                    postsListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    postsListView.setAdapter(customAdapter);
                }
            }

            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    private void addPost(String title, String description, EditText titleTV,EditText descriptionTV){
        apiService.addPost(title,description).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
            Log.d("success ---------------",String.valueOf(response.isSuccessful()));
                if(response.isSuccessful()){
                    Toast.makeText(posts.this, "Added Post Successfully", Toast.LENGTH_SHORT).show();
                    getPosts();
                    titleTV.setText("");
                    descriptionTV.setText("");
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                    Toast.makeText(posts.this, "Failed to add post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}