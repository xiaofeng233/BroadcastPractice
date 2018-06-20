package com.ldxiaofeng.broadcastpractice;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private EditText accountEdit;

    private  EditText passwordEdit;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("admin")&& password.equals("123456")){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"帐号密码有误",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

