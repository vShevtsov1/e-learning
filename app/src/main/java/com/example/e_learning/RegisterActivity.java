package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e_learning.user.DTO.registerDTO;
import com.example.e_learning.user.DTO.registerResponse;
import com.example.e_learning.user.help.status;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.example.e_learning.user.retrofit.userApi;

import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private userApi userApi;
    private TextInputLayout name;
    private TextInputLayout surname;
    private TextInputLayout email;
    private TextInputLayout pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        connRetrofit userRetrofit = new connRetrofit();
        userApi = userRetrofit.getUserApi();
        name = findViewById(R.id.question);
        surname = findViewById(R.id.correctAnswer);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
    }
    public void login(View view){
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    public void register(View view){
        Call<registerResponse> call = userApi.register(new registerDTO(name.getEditText().getText().toString(),surname.getEditText().getText().toString(),email.getEditText().getText().toString(),pass.getEditText().getText().toString()));
        call.enqueue(new Callback<registerResponse>() {


            @Override
            public void onResponse(Call<registerResponse> call, Response<registerResponse> response) {
                if (response.body().getStatus() == status.FAILED) {
                  String a = "";
                }
                else{
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<registerResponse> call, Throwable t) {

            }
        });
    }
}