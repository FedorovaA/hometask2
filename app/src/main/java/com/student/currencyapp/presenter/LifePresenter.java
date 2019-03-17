package com.student.currencyapp.presenter;

import android.os.Bundle;

public interface LifePresenter {
    void onCreate(Bundle saveInstance);
    void onStart();
    void onStop();
    void onDestroy();
}
