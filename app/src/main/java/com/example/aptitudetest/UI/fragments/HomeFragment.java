package com.example.aptitudetest.UI.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.activities.MainActivity;
import com.example.aptitudetest.info.QuestionData;
import com.example.aptitudetest.info.RegisterCallResponce;


public class HomeFragment extends Fragment {

    private RelativeLayout btStart;
    private MainActivity context;
    private TextView tvTotalMin;
    private TextView tvTotalQuestion, tvintrotext;
    private RegisterCallResponce registerCallResponce;
    private QuestionData questionData;

    public HomeFragment(MainActivity context, QuestionData questionData) {
        this.context = context;
        this.questionData = questionData;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvTotalMin = view.findViewById(R.id.min);
        tvTotalQuestion = view.findViewById(R.id.totalquestion);
        tvintrotext = view.findViewById(R.id.textintro);

        btStart = view.findViewById(R.id.start);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.questionFrag(questionData);
            }
        });



        tvTotalQuestion.setText(questionData.getTotalQuestion().toString());
        tvTotalMin.setText(questionData.getTotalTime()+" min");
        tvintrotext.setText("This is a mixed aptitude test. This test consists of " + String.valueOf(questionData.getTotalQuestion()) + " questions with a time limit of " + questionData.getTotalTime() + " minutes. You can skip a question and return to it later.");



        return view;
    }
}