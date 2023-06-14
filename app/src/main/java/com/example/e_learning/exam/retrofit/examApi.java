package com.example.e_learning.exam.retrofit;

import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.exam.DTO.correctAnswersDTO;
import com.example.e_learning.exam.DTO.createExamDTO;
import com.example.e_learning.exam.DTO.examDTO;
import com.example.e_learning.exam.DTO.userEstimatesDTO;
import com.example.e_learning.exam.DTO.usersEstimatesDTO;
import com.example.e_learning.exam.DTO.usersExamsDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface examApi {

    @POST("create")
    Call<Void> create(@Header("Authorization") String authToken,@Body createExamDTO createExamDTO);



    @GET("allExams")
    Call<List<Exam>> getAll(@Header("Authorization") String authToken);

    @POST("usersEstimates")
    Call<List<usersEstimatesDTO>> usersEstimate(@Header("Authorization") String authToken,@Body Exam exam);

    @GET("users-exams")
    Call<List<Exam>>findAllUsersExamsNotCompleted(@Header("Authorization") String authToken);

    @GET("archive-exams")
    Call<List<Exam>>findAllUsersExamsCompleted(@Header("Authorization") String authToken);

    @POST("count")
    Call<Integer>getExamCount(@Header("Authorization") String authToken,@Body Exam exam);

    @POST("get-exam-questions")
    Call<List<examDTO>> allQuestByExam(@Header("Authorization") String authToken,@Body Exam exam);
    @POST("save-users-answers")
    Call<Void> saveUserAnswers(@Header("Authorization") String authToken,@Body usersExamsDTO usersExamsDTO);

    @POST("users-answers")
    Call<List<correctAnswersDTO>> userAnswers(@Header("Authorization") String authToken,@Body Exam exam);
    @GET("my-estimates")
    Call<List<userEstimatesDTO>> getMyEstimates(@Header("Authorization") String authToken);
 }
