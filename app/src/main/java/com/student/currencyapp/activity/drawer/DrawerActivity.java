package com.student.currencyapp.activity.drawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.student.currencyapp.R;
import com.student.currencyapp.fragment.ruble.RubleFragment;
import com.student.currencyapp.fragment.shekel.CurrencyFragment;
import com.student.currencyapp.utils.LoginController;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RubleFragment.OnFragmentInteractionListener, CurrencyFragment.OnFragmentInteractionListener {

    private CurrencyFragment currencyFragment1;
    private CurrencyFragment currencyFragment2;
    private CurrencyFragment currencyFragment3;
    private RubleFragment rubleFragment1;
    private RubleFragment rubleFragment2;
    private RubleFragment rubleFragment3;

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        currencyFragment1 = CurrencyFragment.newInstance("SHEKEL");
        currencyFragment2 = CurrencyFragment.newInstance("EURO");
        currencyFragment3 = CurrencyFragment.newInstance("TENGE");
        rubleFragment1 = RubleFragment.newInstance("SHEKEL");
        rubleFragment2 = RubleFragment.newInstance("EURO");
        rubleFragment3 = RubleFragment.newInstance("TENGE");


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txt = headerView.findViewById(R.id.header_text);
        txt.setText(LoginController.getInstance().getLogin());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_cont1,rubleFragment1)
                    .replace(R.id.frag_cont2, currencyFragment1)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_shekel:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_cont1,rubleFragment1)
                        .replace(R.id.frag_cont2, currencyFragment1)
                        .commit();
                break;
            case R.id.nav_euro:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_cont1,rubleFragment2)
                        .replace(R.id.frag_cont2, currencyFragment2)
                        .commit();
                break;
            case R.id.nav_tenge:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_cont1,rubleFragment3)
                        .replace(R.id.frag_cont2, currencyFragment3)
                        .commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    public void onInputRubleSent(String input,String param) {
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
    public void onMessage(String message) {
        Toast.makeText(DrawerActivity.this,message,Toast.LENGTH_SHORT).show();
    }

}
