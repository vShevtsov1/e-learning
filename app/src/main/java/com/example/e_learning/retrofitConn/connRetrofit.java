package com.example.e_learning.retrofitConn;

import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.questions.retrofit.questionApi;
import com.example.e_learning.user.retrofit.userApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class connRetrofit {

    private userApi userApi;
    private questionApi questionApi;
    private examApi examApi;


    public connRetrofit() {
        createConn();
    }
    public void createConn(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080/api/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retrofit retrofitquest = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080/api/questions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retrofit retrofitexam = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080/api/exam/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(userApi.class);
        questionApi = retrofitquest.create(questionApi.class);
        examApi = retrofitexam.create(examApi.class);
    }

    public userApi getUserApi() {
        return userApi;
    }

    public questionApi getQuestionApi() {
        return questionApi;
    }

    public examApi getExamApi() {
        return examApi;
    }
}
