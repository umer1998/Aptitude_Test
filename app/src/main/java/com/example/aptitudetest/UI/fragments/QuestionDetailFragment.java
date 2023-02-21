package com.example.aptitudetest.UI.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.activities.MainActivity;
import com.example.aptitudetest.info.Questions;
import com.example.aptitudetest.utils.FragmentCallBack;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.HashMap;


public class QuestionDetailFragment extends Fragment {

    MainActivity context;
    private int postion;
    private ImageView ivQuestionImage;
    RelativeLayout rl1, rl2, rl3, rl4, rlnextQuestion;
    TextView questionno , nextQuestion, totalQuestion;
    private Questions questions;
    private int size;
    private HashMap<Integer, String> map;
    private TextView tvQ1, tvQ2, tvQ3, tvQ4;
    private HtmlTextView qStatement;
    private String answer;

    public QuestionDetailFragment(MainActivity context, int postion, Questions questions, int size, HashMap<Integer, String> map) {
        this.context = context;
        this.postion = postion;
        this.questions = questions;
        this.size = size;
        this.map = map;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_detail, container, false);


        tvQ1 = view.findViewById(R.id.op1);
        tvQ1.setText(questions.getOption1());

        tvQ2 = view.findViewById(R.id.op2);
        tvQ2.setText(questions.getOption2());

        tvQ3 = view.findViewById(R.id.op3);
        tvQ3.setText(questions.getOption3());

        tvQ4 = view.findViewById(R.id.op4);
        tvQ4.setText(questions.getOption4());

        qStatement = view.findViewById(R.id.qstatement);
        qStatement.setHtml(questions.getQuestionStatement());

        ivQuestionImage = view.findViewById(R.id.image);

        if (questions.getQuestionImage()!= null &&
                !questions.getQuestionImage().equalsIgnoreCase("")) {
            ivQuestionImage.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) qStatement.getLayoutParams();
            params.leftMargin = 15; params.topMargin = 00;
            params.rightMargin= 15; params.bottomMargin = 20;
            qStatement.setLayoutParams(params);

            Picasso.get().load(questions.getQuestionImage()).into(ivQuestionImage);
        } else {
            ivQuestionImage.setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) qStatement.getLayoutParams();
            params.leftMargin = 15; params.topMargin = 00;
            params.rightMargin= 15; params.bottomMargin = 40;
            qStatement.setLayoutParams(params);
        }


        rl1 = view.findViewById(R.id.q1);
        rl2 = view.findViewById(R.id.q2);
        rl3 = view.findViewById(R.id.q3);
        rl4 = view.findViewById(R.id.q4);

        questionno = view.findViewById(R.id.questionno);
        totalQuestion = view.findViewById(R.id.totalquestion);

        questionno.setText(postion+1+"");
        totalQuestion.setText("/" + size);


        rl1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                answer = questions.getOption1();
                map.put(questions.getQuestionId() , questions.getOption1());

                rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                rl1.setElevation(10f);

                rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl2.setElevation(0f);

                rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl3.setElevation(0f);

                rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl4.setElevation(0f);
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer = questions.getOption2();
                map.put(questions.getQuestionId() , questions.getOption2());

                rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                rl2.setElevation(10f);

                rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl1.setElevation(0f);

                rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl3.setElevation(0f);


                rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl4.setElevation(0f);
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer = questions.getOption3();
                map.put(questions.getQuestionId() , questions.getOption3());

                rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                rl3.setElevation(10f);

                rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl2.setElevation(0f);

                rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl1.setElevation(0f);

                rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl4.setElevation(0f);
            }
        });

        rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer = questions.getOption4();
                map.put(questions.getQuestionId() , questions.getOption4());

                rl4.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.question_background));
                rl4.setElevation(10f);

                rl2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl2.setElevation(0f);

                rl3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl3.setElevation(0f);

                rl1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.circular_round_cornor));
                rl1.setElevation(0f);
            }
        });


        return view;
    }

    public HashMap<Integer, String> getData(){

        return map;
    }



}