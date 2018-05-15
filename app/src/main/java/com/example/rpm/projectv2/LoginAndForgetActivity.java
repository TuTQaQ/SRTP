package com.example.rpm.projectv2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LoginAndForgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_forget);

        FragmentInit();

    }
    private void FragmentInit(){
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        Fragment fragment=null;
        if(type.equals("forget")){
            fragment=new ForgetFragment();
            transaction.replace(R.id.loginforgetlayout,fragment);
            transaction.commit();
        }
        if(type.equals("login")){
            fragment=new LoginFragment();
            transaction.replace(R.id.loginforgetlayout,fragment);
            transaction.commit();
        }
    }
}
