package com.example.e_learning.studentFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.e_learning.R;
import com.example.e_learning.adminFragments.QuestionsListAdapter;
import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.exam.DTO.correctAnswersDTO;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.retrofitConn.connRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class archivedExamsGetAnswers extends Fragment {

   private Exam exam;
   private ListView testanswers;
    private SharedPreferences sharedPreferences;
    private com.example.e_learning.exam.retrofit.examApi examApi;

    public archivedExamsGetAnswers(Exam exam) {
        this.exam = exam;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_archived_exams_get_answers, container, false);
        testanswers = view.findViewById(R.id.testanswers);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        connRetrofit connRetrofit = new connRetrofit();
        examApi = connRetrofit.getExamApi();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Call<List<correctAnswersDTO>> call = examApi.userAnswers("Bearer "+sharedPreferences.getString("token",""),exam);
        call.enqueue(new Callback<List<correctAnswersDTO>>() {
            @Override
            public void onResponse(Call<List<correctAnswersDTO>> call, Response<List<correctAnswersDTO>> response) {
                correctAnswerAdapter adapter = new correctAnswerAdapter(getContext(), R.layout.examcorrect, response.body());
                testanswers.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<correctAnswersDTO>> call, Throwable t) {

            }
        });
    }
}