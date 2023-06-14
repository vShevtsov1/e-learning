package com.example.e_learning.adminFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.questions.DTO.Questions;

import java.util.List;

public class examListAdapter extends ArrayAdapter<Exam> {

    private Context mContext;
    private int mResource;

    public examListAdapter(Context context, int resource, List<Exam> objects) {
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

        Exam exam = getItem(position);

        TextView questionTextView = view.findViewById(R.id.examName);
        questionTextView.setText(exam.getName());

        TextView questionidView = view.findViewById(R.id.examID);
        questionidView.setText(String.valueOf(exam.getIdexam()));


        return view;
    }
}
