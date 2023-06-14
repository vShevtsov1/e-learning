package com.example.e_learning.adminFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.createExamDTO;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.questions.DTO.Questions;
import com.example.e_learning.questions.retrofit.questionApi;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.example.e_learning.user.DTO.userDTO;
import com.example.e_learning.user.retrofit.userApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateExam extends Fragment {


    private EditText examNameEditText;
    private ListView examQuestion;
    private ListView userListView;
    private Button saveButton;

    private List<Questions> examQuestions;
    private List<userDTO> users;
    private boolean isAllSelectedExams = false;
    private boolean isAllSelectedUsers = false;
    private TextView selectAllQuest;
    private TextView selectAllUsers;
    private questionApi questionApi;
    private userApi userApi;
    private ArrayAdapter<userDTO> userAdapter;
    private ArrayAdapter<Questions> examAdapter;
    private List<userDTO> selectedUsers;
    private List<Questions> selectedExamQuestions;
    private examApi examApi;
    private SharedPreferences sharedPreferences;


    public CreateExam() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_exam, container, false);
        examNameEditText = view.findViewById(R.id.examNameEditText);
        examQuestion = view.findViewById(R.id.examQuestions);
        userListView = view.findViewById(R.id.users);
        saveButton = view.findViewById(R.id.saveButton);
        selectAllQuest = view.findViewById(R.id.selectAllExamQuest);
        selectAllUsers = view.findViewById(R.id.SelectAllUsers);
        connRetrofit connRetrofit = new connRetrofit();
        questionApi = connRetrofit.getQuestionApi();
        userApi = connRetrofit.getUserApi();
        examApi = connRetrofit.getExamApi();
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        examQuestions = new ArrayList<>();
        // Initialize exam questions and users
        Call<List<Questions>> call = questionApi.getAll("Bearer "+sharedPreferences.getString("token",""));
        call.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                examQuestions = response.body();
                examAdapter = new ExamQuestionAdapter(getContext(),examQuestions);
                examQuestion.setAdapter(examAdapter);
                examQuestion.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                selectAllQuest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isAllSelectedExams = !isAllSelectedExams;
                        for (int i = 0; i < examQuestion.getCount(); i++) {
                            examQuestion.setItemChecked(i, isAllSelectedExams);
                        }
                        selectAllQuest.setText(isAllSelectedExams ? "Зняти вибір із усіх" : "Вибрати все");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {

            }
        });

        Call<List<userDTO>> call1 = userApi.getAllStudents("Bearer "+sharedPreferences.getString("token",""));
        call1.enqueue(new Callback<List<userDTO>>() {
            @Override
            public void onResponse(Call<List<userDTO>> call, Response<List<userDTO>> response) {
                users = response.body();
                userAdapter = new  ExamUsersAdapter(getContext(),users);
                userListView.setAdapter(userAdapter);
                userListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                selectAllUsers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isAllSelectedUsers = !isAllSelectedUsers;
                        for (int i = 0; i < userAdapter.getCount(); i++) {
                            userListView.setItemChecked(i, isAllSelectedUsers);
                        }
                        selectAllUsers.setText(isAllSelectedUsers ? "Зняти вибір із усіх" : "Вибрати все");

                    }
                });
            }

            @Override
            public void onFailure(Call<List<userDTO>> call, Throwable t) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedExamQuestions = new ArrayList<>();
                SparseBooleanArray examQuestionSelections = examQuestion.getCheckedItemPositions();
                int examQuestionCount = examQuestion.getCount();
                for (int i = 0; i < examQuestionCount; i++) {
                    if (examQuestionSelections.get(i)) {
                        selectedExamQuestions.add(examQuestions.get(i));
                    }
                }
                selectedUsers = new ArrayList<>();
                SparseBooleanArray userSelections = userListView.getCheckedItemPositions();
                int userCount = userListView.getCount();
                for (int i = 0; i < userCount; i++) {
                    if (userSelections.get(i)) {
                        selectedUsers.add(users.get(i));
                    }
                }
                createExamDTO createExamDTO = new createExamDTO(examNameEditText.getText().toString(),selectedUsers,selectedExamQuestions);
                System.out.println(createExamDTO);
                Call<Void> call2 = examApi.create("Bearer "+sharedPreferences.getString("token",""),createExamDTO);
                call2.enqueue(new Callback<Void>() {
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




    }
}