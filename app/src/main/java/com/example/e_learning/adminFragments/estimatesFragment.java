package com.example.e_learning.adminFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_learning.R;
import com.example.e_learning.exam.DTO.Exam;
import com.example.e_learning.exam.DTO.usersEstimatesDTO;
import com.example.e_learning.exam.retrofit.examApi;
import com.example.e_learning.retrofitConn.connRetrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class estimatesFragment extends Fragment {

    private examApi examApi;
    private Spinner exams;
    private TableLayout tableLayout;
    private List<Exam> examList;
    private FloatingActionButton pdfButton;
    private SharedPreferences sharedPreferences;
    public estimatesFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estimates, container, false);
        connRetrofit connRetrofit = new connRetrofit();
        examApi = connRetrofit.getExamApi();
        exams = view.findViewById(R.id.examsSpinner);
        tableLayout = view.findViewById(R.id.estimatesTableUser);
        pdfButton = view.findViewById(R.id.pdfButton);
        sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePDF();
            }
        });
        Call<List<Exam>> call = examApi.getAll("Bearer "+sharedPreferences.getString("token",""));
        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
                examList = response.body();
                for (Exam exams : examList) {
                    adapter.add(exams.getName());
                }
                exams.setAdapter(adapter);
                exams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Call<List<usersEstimatesDTO>> call1 = examApi.usersEstimate("Bearer "+sharedPreferences.getString("token",""),examList.get(i));
                        call1.enqueue(new Callback<List<usersEstimatesDTO>>() {
                            @Override
                            public void onResponse(Call<List<usersEstimatesDTO>> call, Response<List<usersEstimatesDTO>> response) {
                                int rowCount = tableLayout.getChildCount();
                                for (int i = 1; i < rowCount; i++) {
                                    View rowView = tableLayout.getChildAt(i);
                                    tableLayout.removeView(rowView);
                                }
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

                                for(usersEstimatesDTO usersEstimatesDTO:response.body()){
                                    // Create the table row
                                    TableRow row = new TableRow(getContext());


                                    TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                                    rowParams.setMargins(0, 0, 0, 16); // Optional: set margins


                                    row.setLayoutParams(rowParams);


                                    TextView column1 = new TextView(getContext());
                                    TableRow.LayoutParams column1Params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                                    column1Params.setMargins(0, 0, 16, 0); // Optional: set margins
                                    column1.setLayoutParams(column1Params);
                                    column1.setText(usersEstimatesDTO.getUsers().getName()+" "+usersEstimatesDTO.getUsers().getSurname());
                                    column1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    column1.setTextSize(16);


                                    TextView column2 = new TextView(getContext());
                                    TableRow.LayoutParams column2Params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                                    column2Params.setMargins(0, 0, 16, 0); // Optional: set margins
                                    column2.setLayoutParams(column2Params);
                                    column2.setText(usersEstimatesDTO.getEstimate().toString());
                                    column2.setTextSize(16);
                                    column2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                                    TextView column3 = new TextView(getContext());
                                    TableRow.LayoutParams column3Params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                                    column3.setLayoutParams(column3Params);
                                    column3.setText(usersEstimatesDTO.getExam().getName());
                                    column3.setTextSize(16);
                                    column3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                                    row.addView(column1);
                                    row.addView(column2);
                                    row.addView(column3);
                                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.tableborder);

                                    row.setBackground(drawable);



                                    tableLayout.addView(row);


                                }

                            }

                            @Override
                            public void onFailure(Call<List<usersEstimatesDTO>> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {

            }
        });
    }
    private void savePDF() {
        // Create an instance of the PdfDocument class
        PdfDocument pdfDocument = new PdfDocument();

        // Create a page
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(tableLayout.getWidth(), tableLayout.getHeight(), 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        // Draw the table layout on the page
        tableLayout.draw(page.getCanvas());

        // Finish the page
        pdfDocument.finishPage(page);

        // Get the download directory
        File downloadDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        // Create a file name for the PDF
        String fileName = "estimates.pdf";

        // Create a file with the specified name in the download directory
        File file = new File(downloadDirectory, fileName);

        try {
            // Create an output stream to write the PDF data to the file
            FileOutputStream outputStream = new FileOutputStream(file);

            // Write the PDF data to the output stream
            pdfDocument.writeTo(outputStream);

            // Close the output stream
            outputStream.close();

            // Show a toast message indicating that the PDF was saved successfully
            Toast.makeText(getContext(), "PDF saved to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // Show a toast message indicating that there was an error saving the PDF
            Toast.makeText(getContext(), "Error saving PDF", Toast.LENGTH_SHORT).show();
        }

        // Close the PdfDocument
        pdfDocument.close();
    }



}