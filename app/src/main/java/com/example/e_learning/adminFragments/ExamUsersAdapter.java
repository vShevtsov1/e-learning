package com.example.e_learning.adminFragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_learning.questions.DTO.Questions;
import com.example.e_learning.user.DTO.userDTO;

import java.util.List;

public class ExamUsersAdapter extends ArrayAdapter<userDTO> {

    public ExamUsersAdapter(Context context, List<userDTO> examUsers) {
        super(context, android.R.layout.simple_list_item_multiple_choice, examUsers);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        userDTO userDTO = getItem(position);
        textView.setText(userDTO.getName()+" "+userDTO.getSurname()); // Display only the question field of the ExamQuestion object
        return textView;
    }
}



