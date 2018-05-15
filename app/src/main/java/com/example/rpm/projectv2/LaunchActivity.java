package com.example.rpm.projectv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener{

    Button enterbutton;
    Button forgetbutton;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);

        findviews();
        enterbutton.setOnClickListener(this);
        forgetbutton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()){
            case R.id.enterbutton:
                intent=new Intent(LaunchActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.forgetbutton:
                intent=new Intent(LaunchActivity.this,LoginAndForgetActivity.class);
                intent.putExtra("type","forget");
                startActivity(intent);
                break;
            case R.id.loginbutton:
                intent=new Intent(LaunchActivity.this,LoginAndForgetActivity.class);
                intent.putExtra("type","login");
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private void findviews(){
        enterbutton=(Button)findViewById(R.id.enterbutton);
        forgetbutton=(Button)findViewById(R.id.forgetbutton);
        loginButton=(Button)findViewById(R.id.loginbutton);
    }
}
