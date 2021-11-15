package com.cookandroid.sagaksagaktodo;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalenderActivity()).commit(); break;
                    case R.id.nav_timer :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_timer) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TimerActivity()).commit(); break;
                    case R.id.nav_todo :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_todo) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TodoMainFragment()).commit(); break;
                }
                return true;
            }
        });
    }
}