package com.example.e_learning.adminFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.e_learning.R;
import com.example.e_learning.questions.DTO.Questions;
import com.example.e_learning.questions.retrofit.questionApi;
import com.example.e_learning.user.DTO.loginDTO;
import com.example.e_learning.user.DTO.loginResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.e_learning.retrofitConn.connRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionsFragment extends Fragment {

    private ListView questionList;
    private questionApi questionApi;
    private SharedPreferences sharedPreferences;
    public QuestionsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        FloatingActionButton addquest =view.findViewById(R.id.addQuest);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        addquest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment newFragment = new AddNewQuestion();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_content, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        questionList = view.findViewById(R.id.questionsList);
        connRetrofit questRetrofit = new connRetrofit();
        questionApi = questRetrofit.getQuestionApi();
        Call<List<Questions>> call = questionApi.getAll("Bearer "+sharedPreferences.getString("token",""));
        call.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                QuestionsListAdapter adapter = new QuestionsListAdapter(getContext(), R.layout.questitem, response.body());
                questionList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {

            }
        });

        return view;
    }

}