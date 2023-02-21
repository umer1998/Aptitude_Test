package com.example.aptitudetest.UI.fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.activities.MainActivity;
import com.example.aptitudetest.business.impl.AuthBusiness;
import com.example.aptitudetest.delegate.ResponseCallBack;
import com.example.aptitudetest.info.PostQuestionsAnswers;
import com.example.aptitudetest.info.QuestionsAnswer;
import com.example.aptitudetest.info.QuestionsAnswerResponce;
import com.example.aptitudetest.utils.AppUtils;
import com.example.aptitudetest.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SummaryScreenFrag extends Fragment {


    private RelativeLayout rlend, rlback;
    private MainActivity context;
    private int total,  size;
    private TextView tvTotal, tvAttempted, tvSkipped;
    private HashMap<Integer, String> map;


    public SummaryScreenFrag(MainActivity context, int size, int total, HashMap<Integer, String> map) {
        this.context = context;
        this.size = size;
        this.total = total;
        this.map = map;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary_screen, container, false);

        tvAttempted = view.findViewById(R.id.attempted);
        tvAttempted.setText(size+"");

        tvTotal = view.findViewById(R.id.total);
        tvTotal.setText(total+"");

        tvSkipped = view.findViewById(R.id.skipped);
        tvSkipped.setText(String.valueOf(total-size));

        rlback = view.findViewById(R.id.back);
        rlback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onBackPressed();
            }
        });

        rlend = view.findViewById(R.id.end);
        rlend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("You still have time left. You are not allowed to came back!")
                        .setConfirmText("Exit")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                saveAnswers();
                                sweetAlertDialog.dismiss();
                            }
                        })
                        .setCancelText("Ok")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        });

                sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button positiveButton = ((SweetAlertDialog) dialog)
                                .getButton(SweetAlertDialog.BUTTON_POSITIVE);
                        positiveButton.setBackgroundDrawable(getResources()
                                .getDrawable(R.drawable.circular_purple_round));
                        positiveButton.setTextColor(Color.parseColor("#FFFFFF"));

                        Button negativeButton = ((SweetAlertDialog) dialog)
                                .getButton(SweetAlertDialog.BUTTON_NEGATIVE);
                        negativeButton.setBackgroundDrawable(getResources()
                                .getDrawable(R.drawable.question_background));
                        negativeButton.setTextColor(Color.parseColor("#000000"));
                    }
                });

//                sweetAlertDialog.getButton(SweetAlertDialog.BUTTON_CANCEL).setBackgroundColor(getResources().getColor(R.color.pink_bg));


                sweetAlertDialog.show();
            }
        });

        return view;
    }
    public void saveAnswers(){
        PostQuestionsAnswers postQuestionsAnswers = new PostQuestionsAnswers();
        ArrayList<QuestionsAnswer> answers = new ArrayList<>();

        for (Integer key : map.keySet()) {
            QuestionsAnswer questionsAnswer = new QuestionsAnswer();
            questionsAnswer.setId(String.valueOf(key));
            questionsAnswer.setAnswer(map.get(key));
            answers.add(questionsAnswer);
        }

        postQuestionsAnswers.setList(answers);
        postQuestionsAnswers.setUserid(Integer.valueOf( PreferenceUtils.getInstance().getUSerID()));

        SweetAlertDialog pDialgoue = AppUtils.loading(context);
        pDialgoue.show();
        AuthBusiness authBusiness = new AuthBusiness();
        authBusiness.postAnswers(postQuestionsAnswers, new ResponseCallBack<QuestionsAnswerResponce>() {
            @Override
            public void onSuccess(QuestionsAnswerResponce body) {
                context.endTestwithouttimeFrag(size, total);
                pDialgoue.dismiss();
                return ;
            }

            @Override
            public void onFailure(String message) {
                pDialgoue.dismiss();
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Alert")
                        .setContentText(message)
                        .setConfirmText("Ok")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        });
                sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button positiveButton = ((SweetAlertDialog) dialog)
                                .getButton(SweetAlertDialog.BUTTON_POSITIVE);
                        positiveButton.setBackgroundDrawable(getResources()
                                .getDrawable(R.drawable.circular_purple_round));
                        positiveButton.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                });
                sweetAlertDialog.show();
                return;

            }
        });

    }
}