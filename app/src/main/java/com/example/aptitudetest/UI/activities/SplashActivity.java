package com.example.aptitudetest.UI.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.aptitudetest.R;
import com.example.aptitudetest.business.impl.AuthBusiness;
import com.example.aptitudetest.delegate.ResponseCallBack;
import com.example.aptitudetest.info.JobRoles;
import com.example.aptitudetest.utils.PreferenceUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startFadeOutAnimation();

    }

    public void startFadeOutAnimation() {
        ImageView fadeOutImage = findViewById(R.id.iv_logo);
        Animation startFadeOutAnimation
                = AnimationUtils.loadAnimation(
                getApplicationContext(),
                R.anim.fade_out_animation);
        fadeOutImage.startAnimation(startFadeOutAnimation);

        startFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                AuthBusiness authBusiness = new AuthBusiness();
                authBusiness.jobRole(new ResponseCallBack<JobRoles>() {
                    @Override
                    public void onSuccess(JobRoles body) {

                        PreferenceUtils.getInstance().addJobDetail(body);
                        checkUserLoggedIn();

                    }

                    @Override
                    public void onFailure(String message) {
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SplashActivity.this, SweetAlertDialog.WARNING_TYPE)
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

                        finishAffinity();


                    }
                });


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void checkUserLoggedIn() {


        startActivity(new Intent(new Intent(SplashActivity.this,Login.class)));
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);

        finishAffinity();
    }
}