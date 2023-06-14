package com.example.e_learning.studentFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.correctAnswersDTO;
import com.example.e_learning.questions.DTO.Questions;

import java.util.List;

public class correctAnswerAdapter extends ArrayAdapter<correctAnswersDTO> {
    private Context mContext;
    private int mResource;

    public correctAnswerAdapter(Context context, int resource, List<correctAnswersDTO> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(mResource, parent, false);
        }
        correctAnswersDTO correctAnswersDTO = getItem(position);
        TextView task_name_text = view.findViewById(R.id.task_name_text);
        TextView userAnswerText = view.findViewById(R.id.userAnswerText);
        TextView CorrectAnswerText = view.findViewById(R.id.CorrectAnswerText);
        task_name_text.setText(correctAnswersDTO.getCorectAnswer().getQuestions().getQuestionTask());
        userAnswerText.setText(correctAnswersDTO.getUserAnswer().getAnswer());
        CorrectAnswerText.setText(correctAnswersDTO.getCorectAnswer().getAnswer());



        return view;
    }
}
