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
import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.retrofitConn.connRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class archivedExams extends Fragment {

  private ListView archivedList;
    private SharedPreferences sharedPreferences;
    private examApi examApi;

    public archivedExams() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archived_exams, container, false);
        archivedList = view.findViewById(R.id.archivedExams);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        connRetrofit connRetrofit = new connRetrofit();
        examApi = connRetrofit.getExamApi();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       Call<List<Exam>> call =  examApi.findAllUsersExamsCompleted("Bearer "+sharedPreferences.getString("token",""));
        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                examListAdapterUT adapter = new examListAdapterUT(getContext(), R.layout.usersexam, response.body());
                archivedList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {

            }
        });
    }
}