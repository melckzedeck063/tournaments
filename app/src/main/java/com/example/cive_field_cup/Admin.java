package com.example.cive_field_cup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer2;
    Button LogoutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

       NavigationView navigationView=findViewById(R.id.nav_view2);
       navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        drawer2 = findViewById(R.id.drawer2);
        LogoutBtn = findViewById(R.id.admn_logout);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, Login.class));
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer2,toolbar,R.string.navigation_drawer_open
        ,R.string.navigation_drawer_close);
        drawer2.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void onBackPressed(){
        if(drawer2.isDrawerOpen(GravityCompat.START))
        {
            drawer2.closeDrawer(GravityCompat.START);
        }
        else
        {
        super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag2_container,
                    new HomeFragment()).commit();
        }
        else if(item.getItemId() == R.id.team1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag2_container,
                    new TeamFragment()).commit();
        }
        else if(item.getItemId() == R.id.standing1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag2_container,
                    new StandingFragment()).commit();
        }
        else if(item.getItemId() == R.id.timetable1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag2_container,
                    new TimetableFragment()).commit();
        }
        else if(item.getItemId() == R.id.results1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag2_container,
                    new MatchresultsFragment()).commit();
        }
        else if(item.getItemId() == R.id.student1){
            switch (getSupportFragmentManager().beginTransaction().replace(R.id.frag2_container,
                    new StudentFragment()).commit()) {
            }
        }
        else if(item.getItemId() == R.id.player1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag2_container,
                    new PlayerFragment()).commit();
        }
        return true;
    }
}