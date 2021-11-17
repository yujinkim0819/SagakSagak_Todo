package com.cookandroid.sagaksagaktodo;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_todo) + "</font>"));
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new TodoMainFragment()).commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_calender :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_calender) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalenderFragment()).commit(); break;
                    case R.id.nav_timer :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_timer) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TimerFragment()).commit(); break;
                    case R.id.nav_todo :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_todo) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TodoMainFragment()).commit(); break;
                }
                return true;
            }
        });
    }
}