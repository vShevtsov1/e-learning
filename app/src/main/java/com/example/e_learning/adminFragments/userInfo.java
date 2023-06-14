package com.example.e_learning.adminFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.e_learning.R;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.example.e_learning.user.DTO.updateDTO;
import com.example.e_learning.user.DTO.updateRoleDTO;
import com.example.e_learning.user.DTO.userDTO;
import com.example.e_learning.user.help.Roles;
import com.example.e_learning.user.retrofit.userApi;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class userInfo extends Fragment {

    private userDTO userDTO;
    private TextInputLayout name;
    private TextInputLayout surname;
    private TextInputLayout email;
    private Spinner role;
    private  CheckBox active;
    private  Button delete;
    private Button save;
    private userApi userApi;
    private SharedPreferences sharedPreferences;
    private String token;
    public userInfo(userDTO userDTO) {
        this.userDTO = userDTO;

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        connRetrofit conn = new connRetrofit();
        userApi = conn.getUserApi();
        name = view.findViewById(R.id.question);
        name.getEditText().setText(userDTO.getName());
        surname=view.findViewById(R.id.correctAnswer);
        surname.getEditText().setText(userDTO.getSurname());
        email=view.findViewById(R.id.incorrectAnswer1);
        email.getEditText().setText(userDTO.getEmail());
        role = view.findViewById(R.id.role);
        active =view.findViewById(R.id.Active);
        delete  = view.findViewById(R.id.delete);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        token = "Bearer "+sharedPreferences.getString("token","");
        save = view.findViewById(R.id.save);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);
        if(userDTO.getRole().equals(Roles.USER)){
            role.setSelection(1);
        }
        else {
            role.setSelection(0);
        }
        if(userDTO.getActive()){
            active.setChecked(true);
        }
        active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              Call<Void> call =  userApi.changeActive(token,userDTO.getIdusers());
              call.enqueue(new Callback<Void>() {
                  @Override
                  public void onResponse(Call<Void> call, Response<Void> response) {

                  }

                  @Override
                  public void onFailure(Call<Void> call, Throwable t) {

                  }
              });
            }
        });
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateRoleDTO updateRoleDTO = new updateRoleDTO(Roles.valueOf(parentView.getItemAtPosition(position).toString()),userDTO.getIdusers());
                Call<Void> call = userApi.updateROle(token,updateRoleDTO);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Code to be executed when nothing is selected
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> call = userApi.delete(token,userDTO.getIdusers());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDTO updateDTO = new updateDTO(name.getEditText().getText().toString(),
                        surname.getEditText().getText().toString(),
                        email.getEditText().getText().toString());
               Call<Void>call =  userApi.update(token,updateDTO,userDTO.getIdusers());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

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