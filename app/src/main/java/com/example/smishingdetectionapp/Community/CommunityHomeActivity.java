package com.example.smishingdetectionapp.Community;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smishingdetectionapp.MainActivity;
import com.example.smishingdetectionapp.NewsActivity;
import com.example.smishingdetectionapp.R;
import com.example.smishingdetectionapp.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;

public class CommunityHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communityhomepage);

        final String origin;
        String src = getIntent().getStringExtra("source");
        if (src == null) origin = "home";
        else origin = src;


        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Trending")); // current page
        tabLayout.addTab(tabLayout.newTab().setText("Posts"));
        tabLayout.addTab(tabLayout.newTab().setText("Report"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                } else if (position == 1) {
                    // go to CommunityPostActivity
                    Intent intent = new Intent(CommunityHomeActivity.this,
                            CommunityPostActivity.class);
                    intent.putExtra("source", origin);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                } else if (position == 2) {
                    // launch ReportActivity
                    Intent i = new Intent(CommunityHomeActivity.this, CommunityReportActivity.class);
                    i.putExtra("source", origin);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // Back Button
        ImageButton community_back = findViewById(R.id.community_back);
        // Check if the back button is initialized properly
        if (community_back != null) {
            // Set an onClick listener to handle the back button's behavior
            community_back.setOnClickListener(v -> {
                // Start SettingsActivity when back button is pressed
                startActivity(new Intent(this, SettingsActivity.class));
                // Close the current activity
                finish();
            });
        } else {
            // Log an error if the back button is null
            Log.e("NotificationActivity", "Back button is null");
        }

        // Bottom Navigation Bar
        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else if (id == R.id.nav_report) {
                Intent i= new Intent(this,CommunityReportActivity.class);
                i.putExtra("source", "home");
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
                return true;

            } else if (id == R.id.nav_news) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });
    }
}
