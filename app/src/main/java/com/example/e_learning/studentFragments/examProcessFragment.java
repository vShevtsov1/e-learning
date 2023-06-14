package com.example.e_learning.studentFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.exam.DTO.examDTO;
import com.example.e_learning.exam.DTO.usersExamsDTO;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.questions.DTO.Questions;
import com.example.e_learning.questionsAnswers.DTO.QuestionAnswers;
import com.example.e_learning.retrofitConn.connRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class examProcessFragment extends Fragment {


    private List<examDTO> exam;
    private int currentQuestionIndex = 0;
    private TextView taskTextView;
    private Button submitButton;
    private RadioButton option1RadioButton, option2RadioButton, option3RadioButton;
    private List<QuestionAnswers> usersAnswers;
    private int countOfTasks = 0;
    private com.example.e_learning.exam.retrofit.examApi examApi;
    private SharedPreferences sharedPreferences;
    public examProcessFragment(List<examDTO> exam) {
        this.exam = exam;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exam_process, container, false);
        taskTextView = view.findViewById(R.id.taskText);
        option1RadioButton = view.findViewById(R.id.radioButton);
        option2RadioButton = view.findViewById(R.id.radioButton2);
        option3RadioButton = view.findViewById(R.id.radioButton3);
        submitButton = view.findViewById(R.id.nextButton);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        usersAnswers = new ArrayList<>();
        countOfTasks = exam.size();
        connRetrofit connRetrofit = new connRetrofit();
        examApi = connRetrofit.getExamApi();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        displayQuestion();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

    }
    private void checkAnswer() {
       
        if(option1RadioButton.isChecked() || option2RadioButton.isChecked()||option3RadioButton.isChecked()){
            if (option1RadioButton.isChecked()) {
                usersAnswers.add(exam.get(currentQuestionIndex).getQuestionAnswers().get(0));
            } else if (option2RadioButton.isChecked()) {
                usersAnswers.add(exam.get(currentQuestionIndex).getQuestionAnswers().get(1));
            } else if (option3RadioButton.isChecked()) {
                usersAnswers.add(exam.get(currentQuestionIndex).getQuestionAnswers().get(2));
            }
            currentQuestionIndex++;
            if(currentQuestionIndex<countOfTasks) {
                displayQuestion();
            }
            else {
               Call<Void> call =  examApi.saveUserAnswers("Bearer "+sharedPreferences.getString("token",""),new usersExamsDTO(usersAnswers,exam.get(0).getExam()));
               call.enqueue(new Callback<Void>() {
                   @Override
                   public void onResponse(Call<Void> call, Response<Void> response) {
                       ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                       Fragment newFragment = new examsFragment();
                       FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                       FragmentTransaction transaction = fragmentManager.beginTransaction();
                       transaction.replace(R.id.fragment_contentS, newFragment);
                       transaction.addToBackStack(null);
                       transaction.commit();
                   }

                   @Override
                   public void onFailure(Call<Void> call, Throwable t) {

                   }
               });

            }
        }
        else {
            Toast.makeText(getContext(), "оберіть відповідь", Toast.LENGTH_SHORT).show();
        }

       
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
    private void displayQuestion() {
        examDTO question = exam.get(currentQuestionIndex);
        taskTextView.setText(question.getQuestions().getQuestionTask());
        option1RadioButton.setText(question.getQuestionAnswers().get(0).getAnswer());
        option1RadioButton.setChecked(false);
        option2RadioButton.setText(question.getQuestionAnswers().get(1).getAnswer());
        option2RadioButton.setChecked(false);
        option3RadioButton.setText(question.getQuestionAnswers().get(2).getAnswer());
        option3RadioButton.setChecked(false);

    }
}