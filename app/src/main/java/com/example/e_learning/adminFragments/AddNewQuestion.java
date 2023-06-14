package com.example.e_learning.adminFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.e_learning.R;
import com.example.e_learning.questions.DTO.questionAnswersDTO;
import com.example.e_learning.questions.DTO.questionDTO;
import com.example.e_learning.questions.retrofit.questionApi;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.example.e_learning.user.DTO.loginResponse;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNewQuestion extends Fragment {


    private Button createButton;
    private TextInputLayout task;
    private TextInputLayout correct;
    private TextInputLayout uncorrect1;
    private TextInputLayout uncorrect2;
    private SharedPreferences sharedPreferences;

    private questionApi questionApi;
    public AddNewQuestion() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_question, container, false);
        createButton = view.findViewById(R.id.createTask);
        task = view.findViewById(R.id.question);
        connRetrofit conn = new connRetrofit();
        questionApi = conn.getQuestionApi();
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        correct = view.findViewById(R.id.correctAnswer);
        uncorrect1 = view.findViewById(R.id.incorrectAnswer1);
        uncorrect2 = view.findViewById(R.id.incorrectAnswer2);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionDTO questionDTO = new questionDTO(task.getEditText().getText().toString(),null);
                List<questionAnswersDTO> list = new ArrayList<>();
                list.add(new questionAnswersDTO(correct.getEditText().getText().toString(),true));
                list.add(new questionAnswersDTO(uncorrect1.getEditText().getText().toString(),false));
                list.add(new questionAnswersDTO(uncorrect2.getEditText().getText().toString(),false));
                questionDTO.setAnswersDTOList(list);
                Call<Void> call = questionApi.create("Bearer "+sharedPreferences.getString("token",""),questionDTO);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        requireActivity().onBackPressed();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });
        return view;
    }


}