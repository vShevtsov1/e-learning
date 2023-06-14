package com.example.e_learning.adminFragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_learning.questions.DTO.Questions;

import java.util.List;

public class ExamQuestionAdapter extends ArrayAdapter<Questions> {

    public ExamQuestionAdapter(Context context, List<Questions> examQuestions) {
        super(context, android.R.layout.simple_list_item_multiple_choice, examQuestions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        Questions examQuestion = getItem(position);
        textView.setText(examQuestion.getQuestionTask()); // Display only the question field of the ExamQuestion object
        return textView;
    }
}
