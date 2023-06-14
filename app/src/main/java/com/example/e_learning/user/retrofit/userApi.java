package com.example.e_learning.user.retrofit;

import com.example.e_learning.user.DTO.loginDTO;
import com.example.e_learning.user.DTO.loginResponse;
import com.example.e_learning.user.DTO.newPasswordDTO;
import com.example.e_learning.user.DTO.registerDTO;
import com.example.e_learning.user.DTO.registerResponse;
import com.example.e_learning.user.DTO.updateDTO;
import com.example.e_learning.user.DTO.updateRoleDTO;
import com.example.e_learning.user.DTO.userDTO;
import com.example.e_learning.user.help.Roles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface userApi {
    @POST("login")
    Call<loginResponse> login(@Body loginDTO loginDTO);

    @POST("register")
    Call<registerResponse> register(@Body registerDTO registerDTO);

    @GET("allUsers")
    Call<List<userDTO>> getAll(@Header("Authorization") String authToken);

    @GET("allStudents")
    Call<List<userDTO>> getAllStudents(@Header("Authorization") String authToken);

    @POST("update-role")
    Call<Void> updateROle(@Header("Authorization") String authToken,@Body updateRoleDTO updateRoleDTO);

    @POST("changeActive/{id}")
    Call<Void> changeActive(@Header("Authorization") String authToken,@Path("id")Long id);

    @DELETE("delete/{id}")
    Call<Void> delete(@Header("Authorization") String authToken,@Path("id")Long id);

    @POST("update/{id}")
    Call<Void> update(@Header("Authorization") String authToken,@Body updateDTO updateDTO,@Path("id")Long id);

    @GET("getById/{id}")
    Call<userDTO>getById(@Header("Authorization") String authToken,@Path("id")  Long id);

    @POST("newPassword")
    Call<Void> setNewPassword(@Header("Authorization") String authToken,@Body newPasswordDTO newPasswordDTO);

    @GET("my-role")
    Call<String> getMyRole(@Header("Authorization") String authToken);
}
