package com.example.e_learning.adminFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.e_learning.R;
import com.example.e_learning.user.DTO.userDTO;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.example.e_learning.user.retrofit.userApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsersFragment extends Fragment {

    private userApi userApi;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        connRetrofit userRetrofit = new connRetrofit();
        userApi =userRetrofit.getUserApi();
        TableLayout tableLayout = view.findViewById(R.id.usersTable);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);


        userApi.getAll("Bearer "+sharedPreferences.getString("token","")).enqueue(new Callback<List<userDTO>>() {
            @Override
            public void onResponse(Call<List<userDTO>> call, Response<List<userDTO>> response) {
               if(response.body().isEmpty()){
                   TextView textView = new TextView(getActivity());
                   textView.setText("No users found.");
                   textView.setTextSize(20);
                   tableLayout.addView(textView);
               }
               else {
                   for (userDTO user : response.body()) {
                       TableRow tableRow = new TableRow(getActivity());
                       tableRow.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Fragment newFragment = new userInfo(user);
                               FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                               transaction.replace(R.id.fragment_content, newFragment);
                               transaction.addToBackStack(null);
                               transaction.commit();
                           }
                       });
                       TextView column1 = new TextView(getActivity());
                       column1.setText(String.valueOf(user.getIdusers()));
                       column1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                       column1.setTextSize(20);
                       TextView column2 = new TextView(getActivity());
                       column2.setText(user.getName());
                       column2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                       column2.setTextSize(20);
                       TextView column3 = new TextView(getActivity());
                       column3.setText(user.getSurname());
                       column3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                       column3.setTextSize(20);
                       TextView column4 = new TextView(getActivity());
                       column4.setText(user.getEmail());
                       column4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                       column4.setTextSize(20);
                       TextView column5 = new TextView(getActivity());
                       column5.setText(user.getRole().toString());
                       column5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                       column5.setTextSize(20);
                       TextView column6 = new TextView(getActivity());
                       column6.setText(user.getActive().toString());
                       column6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                       column6.setTextSize(20);
                       column4.setMaxWidth(100);
                       column4.setEllipsize(TextUtils.TruncateAt.END);
                       tableRow.addView(column1);
                       tableRow.addView(column2);
                       tableRow.addView(column3);
                       tableRow.addView(column4);
                       tableRow.addView(column5);
                       tableRow.addView(column6);
                       Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.tableborder);

                       tableRow.setBackground(drawable);

                       tableLayout.addView(tableRow);
                   }
               }
            }

            @Override
            public void onFailure(Call<List<userDTO>> call, Throwable t) {

            }
        });
        return view;
    }


}