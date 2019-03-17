package com.student.currencyapp.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.student.currencyapp.R;
import com.student.currencyapp.activity.drawer.DrawerActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    Button drawer;
    EditText pas;
    EditText log;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
        pas = findViewById(R.id.ed_password);
        drawer = findViewById(R.id.btn_drawerIn);
        log = findViewById(R.id.ed_login);
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLogin(String.valueOf(log.getText()),String.valueOf(pas.getText()));
            }
        });

    }

    @Override
    public void onLoginSucess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProceed() {
        Intent intent = new Intent(getContext(), DrawerActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
