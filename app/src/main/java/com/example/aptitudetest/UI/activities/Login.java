package com.example.aptitudetest.UI.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.aptitudetest.R;
import com.example.aptitudetest.business.impl.AuthBusiness;
import com.example.aptitudetest.delegate.ResponseCallBack;
import com.example.aptitudetest.info.JobRoles;
import com.example.aptitudetest.info.JobRolesData;
import com.example.aptitudetest.info.QuestionData;
import com.example.aptitudetest.info.RegisterUserRequest;
import com.example.aptitudetest.utils.AppUtils;
import com.example.aptitudetest.utils.PreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {

    RelativeLayout btSave, rlfullname, rlphoneno;
    EditText edFullName , edPhone;
    RadioButton rbDeveloper, rbQa;
    private Spinner fieldspinner;
    private int field = 1;
    private JobRoles jobRoles;
    private RegisterUserRequest registerUserRequest = new RegisterUserRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        jobRoles = PreferenceUtils.getInstance().getJobDetail();
        rbDeveloper = findViewById(R.id.rbdeveloper);
        rbQa = findViewById(R.id.rbqa);
        rbDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbQa.setChecked(false);
            }
        });


        fieldspinner = findViewById(R.id.spinner);
        setField();

        rbQa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbDeveloper.setChecked(false);
            }
        });

        btSave = findViewById(R.id.save);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edFullName.getText().toString().equalsIgnoreCase("") && edFullName != null){
                    registerUserRequest.setName(edFullName.getText().toString());
                } else {
                    AppUtils.sweetAlertDialogueOk( "Incorrect Name","Please enter correct Name" ,Login.this );
                    return;
                }
                if(!edPhone.getText().toString().equalsIgnoreCase("") && edPhone != null && edPhone.getText().length()== 11){
                    registerUserRequest.setPhoneno(edPhone.getText().toString());
                } else {
                    AppUtils.sweetAlertDialogueOk( "Incorrect phone number","Please enter correct Phone number" ,Login.this );

                    return;
                }


                SweetAlertDialog pDailogue = AppUtils.loading(Login.this);
                pDailogue.show();
                AuthBusiness authBusiness = new AuthBusiness();
                authBusiness.register(registerUserRequest, new ResponseCallBack<QuestionData>() {
                    @Override
                    public void onSuccess(QuestionData body) {
                        String questionData = new Gson().toJson(body);
                        pDailogue.dismiss();
                        PreferenceUtils.getInstance().setUSerID(String.valueOf(body.getUserId()));
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        intent.putExtra("QuestionData", questionData );
                        startActivity(intent);

                        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                        finishAffinity();

                    }

                    @Override
                    public void onFailure(String message) {
                        pDailogue.dismiss();
                        String msg = message;
                    }
                });




            }
        });

        rlfullname = findViewById(R.id.rlname);
        rlfullname.requestFocus();

        rlphoneno = findViewById(R.id.rlphone);

        edFullName = findViewById(R.id.name);
        edPhone = findViewById(R.id.phoneno);

        edFullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rlphoneno.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.login_fields_unchecked));
                    rlfullname.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.question_background));

                }
            }
        });

        edPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                rlphoneno.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.question_background));
                rlfullname.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.login_fields_unchecked));

            }
        });

//        edFullName.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                rlphoneno.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.login_fields_unchecked));
//                rlfullname.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.question_background));
//                return false;
//            }
//        });
//
//        edPhone.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                rlphoneno.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.question_background));
//                rlfullname.setBackgroundDrawable(ContextCompat.getDrawable(Login.this, R.drawable.login_fields_unchecked));
//                return false;
//            }
//        });



    }

    private void setField() {

        final ArrayList<String> list =new ArrayList<>();
        for(int i = 0 ; i< jobRoles.list.size(); i++){
            list.add(jobRoles.list.get(i).name);
        }




        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(Login.this,
                R.layout.custom_spinner_text, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldspinner.setAdapter(adp1);


        fieldspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                field = jobRoles.list.get(position).id;
                registerUserRequest.setId(field);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}