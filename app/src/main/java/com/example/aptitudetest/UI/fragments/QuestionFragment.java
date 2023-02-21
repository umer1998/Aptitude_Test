package com.example.aptitudetest.UI.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.activities.MainActivity;
import com.example.aptitudetest.UI.activities.SplashActivity;
import com.example.aptitudetest.UI.adapters.MyPagerAdapter;
import com.example.aptitudetest.UI.adapters.QuestionsAdpater;
import com.example.aptitudetest.business.impl.AuthBusiness;
import com.example.aptitudetest.delegate.ResponseCallBack;
import com.example.aptitudetest.info.JobRoles;
import com.example.aptitudetest.info.PostQuestionsAnswers;
import com.example.aptitudetest.info.QuestionData;
import com.example.aptitudetest.info.Questions;
import com.example.aptitudetest.info.QuestionsAnswer;
import com.example.aptitudetest.info.QuestionsAnswerResponce;
import com.example.aptitudetest.info.RegisterCallResponce;
import com.example.aptitudetest.utils.AppUtils;
import com.example.aptitudetest.utils.PreferenceUtils;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.samanjafari.easycountdowntimer.CountDownInterface;
import ir.samanjafari.easycountdowntimer.EasyCountDownTextview;


public class QuestionFragment extends Fragment {

    private MainActivity context;
    private RecyclerView recyclerView;
    private ViewPager viewPager;
    RelativeLayout rlnextQuestion, rlPreviousQuestion, rlEndTest;
    TextView nextQuestion, tvminutes;
    private EasyCountDownTextview easyCountDownTextview;
    private CircularProgressBar circularProgressBar;
    private HashMap<Integer, String> map = new HashMap<Integer, String>();
    private LinearLayout lljumptolast, lljumptofirst;
    private QuestionData registerCallResponce;
    private ArrayList<Questions> questionsList;


    public QuestionFragment(MainActivity context, QuestionData registerCallResponce) {
        this.context = context;
        this.registerCallResponce = registerCallResponce;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

//        recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false) {
//            @Override
//            public boolean canScrollHorizontally() {
//                return false;
//            }
//        });

        questionsList = registerCallResponce.getQuestions();

        tvminutes = view.findViewById(R.id.min);
        circularProgressBar = view.findViewById(R.id.progressbar);
        QuestionsAdpater adapter = new QuestionsAdpater(context);

        viewPager = view.findViewById(R.id.viewPager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(context, questionsList, map);

        viewPager.setAdapter(myPagerAdapter);
        rlnextQuestion = view.findViewById(R.id.next);
        rlnextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() + 1 == registerCallResponce.getQuestions().size()) {
                    nextQuestion.setVisibility(View.GONE);
                    context.summaryScreenFrag(map ,map.size(), Integer.valueOf(registerCallResponce.getTotalQuestion()));
                } else {

                }
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        rlPreviousQuestion = view.findViewById(R.id.back);

        rlPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (viewPager.getCurrentItem() + 1 == questionsList.size()) {
                    nextQuestion.setVisibility(View.GONE);
                } else {
                    nextQuestion.setVisibility(View.VISIBLE);
                    int ques = viewPager.getCurrentItem() + 2;
                    nextQuestion.setText("Q_" + ques + "");
                }


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        nextQuestion = view.findViewById(R.id.nextquestionno);
        nextQuestion.setText("Q_" + viewPager.getCurrentItem() + 2 + "");

        rlEndTest = view.findViewById(R.id.endTest);
        rlEndTest.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {



                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("You still have time left. Won't came back again.")
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

        circularProgressBar.setProgressMax(Integer.valueOf(registerCallResponce.getTotalTime()) * 60000);
        easyCountDownTextview = view.findViewById(R.id.count);
        easyCountDownTextview.setTime(0, 0, Integer.valueOf(registerCallResponce.getTotalTime()), 0);
        easyCountDownTextview.startTimer();
        easyCountDownTextview.showOnlySecond(true);


        easyCountDownTextview.setOnTick(new CountDownInterface() {
            @Override
            public void onTick(long time) {
                circularProgressBar.setProgress(time);
                tvminutes.setText(time / 60000 + "");

            }

            @Override
            public void onFinish() {
                context.endTestFrag(map.size(), Integer.valueOf(registerCallResponce.getTotalQuestion()));
            }
        });

        lljumptofirst = view.findViewById(R.id.jumptoq1);
        lljumptofirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        lljumptolast = view.findViewById(R.id.jumptolast);
        lljumptolast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(Integer.valueOf(registerCallResponce.getTotalQuestion()));
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
        SweetAlertDialog pDialogue = AppUtils.loading(context);
        pDialogue.show();
        AuthBusiness authBusiness = new AuthBusiness();
        authBusiness.postAnswers(postQuestionsAnswers, new ResponseCallBack<QuestionsAnswerResponce>() {
            @Override
            public void onSuccess(QuestionsAnswerResponce body) {
                pDialogue.dismiss();
                context.endTestwithouttimeFrag(map.size(), Integer.valueOf(registerCallResponce.getTotalQuestion()));
                return ;
            }

            @Override
            public void onFailure(String message) {
                pDialogue.dismiss();
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