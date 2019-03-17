package com.student.currencyapp.activity.drawer;

import android.net.Uri;
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

import com.student.currencyapp.R;
import com.student.currencyapp.fragment.euro.EuroFragment;
import com.student.currencyapp.fragment.ruble.RubleFragment;
import com.student.currencyapp.fragment.shekel.ShekelFragment;
import com.student.currencyapp.fragment.tenge.TengeFragment;
import com.student.currencyapp.utils.LoginController;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RubleFragment.OnFragmentInteractionListener, TengeFragment.OnFragmentInteractionListener, ShekelFragment.OnFragmentInteractionListener,EuroFragment.OnFragmentInteractionListener {

    private RubleFragment rubleFragment;
    private ShekelFragment shekelFragment;
    private EuroFragment euroFragment;
    private TengeFragment tengeFragment;

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
        tengeFragment = new TengeFragment();
        shekelFragment = new ShekelFragment();
        rubleFragment = new RubleFragment();
        euroFragment = new EuroFragment();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txt = headerView.findViewById(R.id.header_text);
        txt.setText(LoginController.getInstance().getLogin());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_cont1, rubleFragment)
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
                rubleFragment.getParams(1);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_cont2, shekelFragment)
                        .commit();
                break;
            case R.id.nav_euro:
                rubleFragment.getParams(3);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_cont2, euroFragment)
                        .commit();
                break;
            case R.id.nav_tenge:
                rubleFragment.getParams(2);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_cont2, tengeFragment)
                        .commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onInputEuroSent(CharSequence input) {
        rubleFragment.updateRubleText(input);
    }

    @Override
    public void onInputTengeSent(CharSequence input) {
        rubleFragment.updateRubleText(input);
    }

    @Override
    public void onInputShekelSent(CharSequence input) {
        rubleFragment.updateRubleText(input);
    }

    @Override
    public void onInputRubleSent(CharSequence input) {
        shekelFragment.updateShekelText(input);
        euroFragment.updateEuroText(input);
        tengeFragment.updateTengeText(input);
    }

}
