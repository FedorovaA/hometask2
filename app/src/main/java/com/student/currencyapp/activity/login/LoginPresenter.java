package com.student.currencyapp.activity.login;

import android.os.Bundle;
import android.widget.Toast;

import com.student.currencyapp.presenter.BasePresenter;
import com.student.currencyapp.utils.LoginController;

public class LoginPresenter extends BasePresenter<LoginView> {

    private String password="1234";

    public LoginPresenter() { }

    public void onLogin(String log,String pas){
        if(pas.equals(password)){
            view.onLoginSucess("Wellcome");
            LoginController.getInstance().setLogin(log);
            view.onProceed();
        }
        else {
            view.onLoginError("Invalid data");
        }
    }

    @Override
    public void onCreate(Bundle saveInstance) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
