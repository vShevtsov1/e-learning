package com.example.e_learning.studentFragments;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.Exam;

import java.util.List;

public class examListAdapterUT extends ArrayAdapter<Exam> {

    private Context mContext;
    private int mResource;

    public examListAdapterUT(Context context, int resource, List<Exam> objects) {
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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                archivedExamsGetAnswers examFragment = new archivedExamsGetAnswers(exam);
                fragmentTransaction.replace(R.id.fragment_contentS, examFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
