package com.example.cive_field_cup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class Student extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Button LogoutBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = findViewById(R.id.toolbar);

        NavigationView navigationView=findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        LogoutBtn = findViewById(R.id.std_logout);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student.this, Login.class));
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open
        ,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }
    public  void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.homes) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag1_container,
                    new HomeF1()).commit();
        }
        else if (item.getItemId() == R.id.resultss) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag1_container,
                    new ResultsF1()).commit();
        }
        else if (item.getItemId() == R.id.timetables) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag1_container,
                    new TimetableF1()).commit();
        }
        else if (item.getItemId() == R.id.standings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag1_container,
                    new StandingF1()).commit();
        }
        else if (item.getItemId() == R.id.teams) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag1_container,
                    new TeamF1()).commit();
        }

        return true;
    }
}