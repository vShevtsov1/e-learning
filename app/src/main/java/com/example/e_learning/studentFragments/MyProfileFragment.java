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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.e_learning.R;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.example.e_learning.user.DTO.newPasswordDTO;
import com.example.e_learning.user.DTO.userDTO;
import com.example.e_learning.user.retrofit.userApi;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyProfileFragment extends Fragment {

    private userApi userApi;
    private TextView name;
    private TextView surname;
    private TextView email;
    private SharedPreferences sharedPreferences;
    private EditText newPasswordEditText;
    private Button button;

    public MyProfileFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        connRetrofit connRetrofit = new connRetrofit();
        userApi  = connRetrofit.getUserApi();
        name = view.findViewById(R.id.nameTextView);
        surname = view.findViewById(R.id.surnameTextView);
        email = view.findViewById(R.id.emailTextView);
        newPasswordEditText = view.findViewById(R.id.newPasswordEditText);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        button = view.findViewById(R.id.newPassword);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Call<userDTO> call = userApi.getById("Bearer "+sharedPreferences.getString("token",""),sharedPreferences.getLong("userID",0));
        call.enqueue(new Callback<userDTO>() {
            @Override
            public void onResponse(Call<userDTO> call, Response<userDTO> response) {
                name.setText(response.body().getName());
                surname.setText(response.body().getSurname());
                email.setText(response.body().getEmail());
            }

            @Override
            public void onFailure(Call<userDTO> call, Throwable t) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newPasswordEditText.getText().toString().equals(""))
                {
                   Call<Void> call1 =  userApi.setNewPassword("Bearer "+sharedPreferences.getString("token",""),new newPasswordDTO(newPasswordEditText.getText().toString()));
                    call1.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                        newPasswordEditText.setText("");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}