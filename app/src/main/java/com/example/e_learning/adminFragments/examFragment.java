package com.example.e_learning.adminFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class examFragment extends Fragment {

    private FloatingActionButton addexam;
    private SharedPreferences sharedPreferences;
    private ListView examList;
    private examApi examApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        addexam =view.findViewById(R.id.addExam);
        examList = view.findViewById(R.id.examList);
        connRetrofit connRetrofit = new connRetrofit();
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        examApi = connRetrofit.getExamApi();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment newFragment = new CreateExam();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_content, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        Call<List<Exam>> call = examApi.getAll("Bearer "+sharedPreferences.getString("token",""));
        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                examListAdapter adapter = new examListAdapter(getContext(), R.layout.exam_item, response.body());
                examList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {

            }
        });
    }
}