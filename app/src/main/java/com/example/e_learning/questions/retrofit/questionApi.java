package com.example.e_learning.questions.retrofit;

import com.example.e_learning.questions.DTO.Questions;
import com.example.e_learning.questions.DTO.questionDTO;
import com.example.e_learning.user.DTO.userDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface questionApi {

    @GET("all")
    Call<List<Questions>> getAll(@Header("Authorization") String authToken);

    @POST("create")
    Call<Void> create(@Header("Authorization") String authToken,@Body questionDTO questionDTO);
}
