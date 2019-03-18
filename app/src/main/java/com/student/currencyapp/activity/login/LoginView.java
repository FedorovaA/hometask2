package com.student.currencyapp.activity.login;

import com.student.currencyapp.view.BaseView;

public interface LoginView extends BaseView {
    void onLoginSucess(String message);
    void onLoginError(String message);
    void onProceed(Boolean param);
}
