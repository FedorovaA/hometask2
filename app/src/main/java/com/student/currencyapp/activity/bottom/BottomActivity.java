package com.student.currencyapp.activity.bottom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.student.currencyapp.R;
import com.student.currencyapp.fragment.ruble.RubleFragment;
import com.student.currencyapp.fragment.shekel.CurrencyFragment;

public class BottomActivity extends AppCompatActivity implements RubleFragment.OnFragmentInteractionListener, CurrencyFragment.OnFragmentInteractionListener {
    private CurrencyFragment currencyFragment1;
    private CurrencyFragment currencyFragment2;
    private CurrencyFragment currencyFragment3;
    private RubleFragment rubleFragment1;
    private RubleFragment rubleFragment2;
    private RubleFragment rubleFragment3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frag_cont3,rubleFragment1)
                            .replace(R.id.frag_cont4, currencyFragment1)
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frag_cont3,rubleFragment2)
                            .replace(R.id.frag_cont4, currencyFragment2)
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frag_cont3,rubleFragment3)
                            .replace(R.id.frag_cont4, currencyFragment3)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        currencyFragment1 = CurrencyFragment.newInstance("SHEKEL");
        currencyFragment2 = CurrencyFragment.newInstance("EURO");
        currencyFragment3 = CurrencyFragment.newInstance("TENGE");
        rubleFragment1 = RubleFragment.newInstance("SHEKEL");
        rubleFragment2 = RubleFragment.newInstance("EURO");
        rubleFragment3 = RubleFragment.newInstance("TENGE");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_cont3,rubleFragment1)
                    .replace(R.id.frag_cont4, currencyFragment1)
                    .commit();
        }
    }

    @Override
    public void onInputRubleSent(String input, String param) {
        switch (param)
        {
            case "SHEKEL":
                currencyFragment1.updateCurrencyText(input);
                break;
            case "EURO":
                currencyFragment2.updateCurrencyText(input);
                break;
            case "TENGE":
                currencyFragment3.updateCurrencyText(input);
                break;
        }
    }

    @Override
    public void onInputCurrencySent(String input, String param) {
        switch (param)
        {
            case "SHEKEL":
                rubleFragment1.updateRubleText(input);
                break;
            case "EURO":
                rubleFragment2.updateRubleText(input);
                break;
            case "TENGE":
                rubleFragment3.updateRubleText(input);
                break;
        }
    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(BottomActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
