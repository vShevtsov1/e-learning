package com.example.e_learning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_learning.user.DTO.loginDTO;
import com.example.e_learning.user.DTO.loginResponse;
import com.example.e_learning.user.help.Roles;
import com.example.e_learning.user.help.status;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.example.e_learning.user.retrofit.userApi;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout emailEdit;
    private TextInputLayout passEdit;
    private TextView incorrect;
    private userApi userApi;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        if(!sharedPreferences.getString("token","").equals("")){
            if(sharedPreferences.getString("role","").equals("ADMIN")){
                Intent i = new Intent(LoginActivity.this,AdminActivity.class);
                startActivity(i);
            }
            else {
                Intent i = new Intent(LoginActivity.this,StudentActivity.class);
                startActivity(i);
            }
        }
        emailEdit =  findViewById(R.id.question);
        passEdit =  findViewById(R.id.Password);
        incorrect = findViewById(R.id.Incorrect);
        connRetrofit userRetrofit = new connRetrofit();
        userApi = userRetrofit.getUserApi();
        emailEdit.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailEdit.setError(null);
                incorrect.setVisibility(View.INVISIBLE);
            }
        });
        passEdit.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                passEdit.setError(null);
                incorrect.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void Login(View view) {
        if(emailEdit.getEditText().getText().toString().equals("")){
            emailEdit.setError("Поле є обов'язковим");
        }
        if(passEdit.getEditText().getText().toString().equals("")){
            passEdit.setError("Поле є обов'язковим");
        }
        if(!emailEdit.getEditText().getText().toString().equals("") &&!passEdit.getEditText().getText().toString().equals("") ) {
            Call<loginResponse> call = userApi.login(new loginDTO(emailEdit.getEditText().getText().toString(), passEdit.getEditText().getText().toString()));
            call.enqueue(new Callback<loginResponse>() {

                @Override
                public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                    if (response.body().getStatus() == status.FAILED) {
                        incorrect.setVisibility(View.VISIBLE);
                    }
                    else {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", response.body().getToken());
                        editor.putLong("userID",response.body().getId());
                        editor.putString("role",response.body().getRoles().toString());
                        editor.apply();
                        if(response.body().getRoles() == Roles.USER){
                                   Intent i = new Intent(LoginActivity.this,StudentActivity.class);
                                    startActivity(i);
                               }
                                else {
                                   Intent i = new Intent(LoginActivity.this,AdminActivity.class);
                                    startActivity(i);
                                }
                    }
                }

                @Override
                public void onFailure(Call<loginResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
    public void register(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }


}