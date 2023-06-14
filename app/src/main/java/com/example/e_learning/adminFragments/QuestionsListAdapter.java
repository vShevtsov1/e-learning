package com.example.e_learning.adminFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_learning.R;
import com.example.e_learning.questions.DTO.Questions;

import java.util.List;

public class QuestionsListAdapter extends ArrayAdapter<Questions> {

    private Context mContext;
    private int mResource;

    public QuestionsListAdapter(Context context, int resource, List<Questions> objects) {
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

        Questions question = getItem(position);

        TextView questionTextView = view.findViewById(R.id.tasktext);
        questionTextView.setText(question.getQuestionTask());

        TextView questionidView = view.findViewById(R.id.taskId);
        questionidView.setText(String.valueOf(question.getIdquestions()));


        return view;
    }
}
