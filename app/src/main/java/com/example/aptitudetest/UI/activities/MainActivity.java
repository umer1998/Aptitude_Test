package com.example.aptitudetest.UI.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.example.aptitudetest.R;
import com.example.aptitudetest.UI.fragments.EndTestFragment;
import com.example.aptitudetest.UI.fragments.TestTimeoutFragment;
import com.example.aptitudetest.UI.fragments.HomeFragment;
import com.example.aptitudetest.UI.fragments.QuestionFragment;
import com.example.aptitudetest.UI.fragments.SummaryScreenFrag;
import com.example.aptitudetest.info.JobRoles;
import com.example.aptitudetest.info.QuestionData;
import com.example.aptitudetest.info.RegisterCallResponce;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private QuestionData questionData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        homeFrag(getQuestionData());
    }

    private QuestionData getQuestionData() {


        Gson gson = new Gson();
        Type type = new TypeToken<QuestionData>() {
        }.getType();


        return gson.fromJson(getIntent().getStringExtra("QuestionData"), type);


    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        if(fragment instanceof QuestionFragment){
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Can't go back to previous stage, For that you need to end test.")
                    .setConfirmText("Ok");

            sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Button positiveButton = ((SweetAlertDialog) dialog)
                            .getButton(SweetAlertDialog.BUTTON_POSITIVE);
                    positiveButton.setBackgroundDrawable(getResources()
                            .getDrawable(R.drawable.question_background));
                    positiveButton.setTextColor(Color.parseColor("#000000"));

                }
            });

            sweetAlertDialog.show();

        } else {
            super.onBackPressed();

        }
    }


    public void homeFrag(QuestionData questionData) {


        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        HomeFragment homeFragment = new HomeFragment(MainActivity.this , questionData);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .add(R.id.main_container, homeFragment)
                .commit();


    }

    public void questionFrag(QuestionData registerCallResponce) {


        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        QuestionFragment homeFragment = new QuestionFragment(MainActivity.this, registerCallResponce);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .add(R.id.main_container, homeFragment)
                .commit();


    }

    public void summaryScreenFrag(HashMap<Integer, String> map, int size, int total) {


        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        SummaryScreenFrag homeFragment = new SummaryScreenFrag(MainActivity.this, size , total , map);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .add(R.id.main_container, homeFragment)
                .addToBackStack("back")
                .commit();


    }

    public void endTestFrag(int size, Integer total) {


        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        TestTimeoutFragment homeFragment = new TestTimeoutFragment(MainActivity.this, size , total);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .add(R.id.main_container, homeFragment)
                .commit();


    }

    public void endTestwithouttimeFrag(int size, int total) {


        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        EndTestFragment homeFragment = new EndTestFragment(MainActivity.this, size , total);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .add(R.id.main_container, homeFragment)
                .commit();


    }
}