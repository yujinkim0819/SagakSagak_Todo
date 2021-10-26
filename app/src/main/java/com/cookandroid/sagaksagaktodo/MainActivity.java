package com.cookandroid.sagaksagaktodo;

import android.os.Bundle;
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

        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new GraphActivity()).commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_graph :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new GraphActivity()).commit(); break;
                    case R.id.nav_timer :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new TimerActivity()).commit(); break;
                    case R.id.nav_todo :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new TodoActivity()).commit(); break;
                }
                return true;
            }
        });
    }
}
