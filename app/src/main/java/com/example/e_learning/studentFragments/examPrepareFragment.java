package com.example.e_learning.studentFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.exam.DTO.examDTO;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.retrofitConn.connRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class examPrepareFragment extends Fragment {

    private TextView countQuestions;
    private TextView examNamePrepare;
    private Button startExam;
    private Exam exam;
    private examApi examApi;
    private SharedPreferences sharedPreferences;

    public examPrepareFragment(Exam exam) {
        // Required empty public constructor
        this.exam = exam;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_prepare, container, false);
        countQuestions = view.findViewById(R.id.counquest);
        examNamePrepare = view.findViewById(R.id.examNamePrepare);
        startExam = view.findViewById(R.id.startExam);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        connRetrofit connRetrofit = new connRetrofit();
        examApi = connRetrofit.getExamApi();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        examNamePrepare.setText(exam.getName());
        Call<Integer> call = examApi.getExamCount("Bearer "+sharedPreferences.getString("token",""),exam);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                countQuestions.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Call<List<examDTO>> call1 =   examApi.allQuestByExam("Bearer "+sharedPreferences.getString("token",""),exam);
                call1.enqueue(new Callback<List<examDTO>>() {
                    @Override
                    public void onResponse(Call<List<examDTO>> call, Response<List<examDTO>> response) {
                        Fragment newFragment = new examProcessFragment(response.body());


                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();


                        transaction.replace(R.id.fragment_contentS, newFragment);


                        transaction.addToBackStack(null);
                        transaction.commit();
                    }

                    @Override
                    public void onFailure(Call<List<examDTO>> call, Throwable t) {

                    }
                });
            }
        });
    }
}