package com.cookandroid.sagaksagaktodo;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//각 프레그먼트의 action bar text변경
//action bar크기 변경
//bottom navigation check, uncheck 색깔변경
//bottom navigation item 1개더 추가(HOME)

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_home) + "</font>"));
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new HomeActivity()).commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_home :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_home) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeActivity()).commit(); break;
                    case R.id.nav_graph :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_graph) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new GraphActivity()).commit(); break;
                    case R.id.nav_timer :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_timer) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new TimerActivity()).commit(); break;
                    case R.id.nav_todo :
                        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.title_todo) + "</font>"));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new TodoActivity()).commit(); break;
                }
                return true;
            }
        });
    }
}
