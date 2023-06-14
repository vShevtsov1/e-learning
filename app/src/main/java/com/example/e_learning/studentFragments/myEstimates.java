package com.example.e_learning.studentFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.userEstimatesDTO;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.retrofitConn.connRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class myEstimates extends Fragment {

    private TableLayout tableLayout;
    private SharedPreferences sharedPreferences;
    private examApi examApi;

    public myEstimates() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_estimates, container, false);
        tableLayout = view.findViewById(R.id.estimatesTableUser);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        connRetrofit connRetrofit = new connRetrofit();
        examApi =connRetrofit.getExamApi();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Call<List<userEstimatesDTO>> call = examApi.getMyEstimates("Bearer "+sharedPreferences.getString("token",""));
        call.enqueue(new Callback<List<userEstimatesDTO>>() {
            @Override
            public void onResponse(Call<List<userEstimatesDTO>> call, Response<List<userEstimatesDTO>> response) {
                for(userEstimatesDTO userEstimatesDTO:response.body()){
                    TableRow row = new TableRow(getContext());


                    TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                    rowParams.setMargins(0, 0, 0, 16); // Optional: set margins


                    row.setLayoutParams(rowParams);


                    TextView column1 = new TextView(getContext());
                    TableRow.LayoutParams column1Params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    column1Params.setMargins(0, 0, 16, 0); // Optional: set margins
                    column1.setLayoutParams(column1Params);
                    column1.setText(userEstimatesDTO.getEstimate().toString());
                    column1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    column1.setTextSize(16);


                    TextView column2 = new TextView(getContext());
                    TableRow.LayoutParams column2Params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    column2Params.setMargins(0, 0, 16, 0); // Optional: set margins
                    column2.setLayoutParams(column2Params);
                    column2.setText(userEstimatesDTO.getExam().getName());
                    column2.setTextSize(16);
                    column2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    row.addView(column1);
                    row.addView(column2);

                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.tableborder);

                    row.setBackground(drawable);



                    tableLayout.addView(row);
                }
            }

            @Override
            public void onFailure(Call<List<userEstimatesDTO>> call, Throwable t) {

            }
        });

    }
}