package com.sekolahguru.folcotandiono.sekolahguru;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout homeDrawerLayout;
    private NavigationView homeNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initObject();
        initListener();
    }

    private void initView() {
        homeDrawerLayout = findViewById(R.id.drawer_layout);
        homeNavigationView = findViewById(R.id.nav_view);
    }

    private void initObject() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Jadwal ujian");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void initListener() {
        homeNavigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.soal_ujian) {
                        Intent intent = new Intent(getApplicationContext(), SoalUjianActivity.class);
                        startActivity(intent);
                    }
                    else if (menuItem.getItemId() == R.id.soal_ujian_detail) {
                        Intent intent = new Intent(getApplicationContext(), SoalUjianDetailActivity.class);
                        startActivity(intent);
                    }
                    else if (menuItem.getItemId() == R.id.jadwal_ujian) {

                    }
                    homeDrawerLayout.closeDrawers();
                    return true;
                }
            }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                homeDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
