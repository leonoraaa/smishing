package com.example.smishingdetectionapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class TermsAndConditionsActivity extends SharedActivity {

    private Button acceptButton;
    private ScrollView termsScrollView;
    private TextView termsTextView;
    private boolean hasScrolledToBottom = false; // Flag to track if user has scrolled to the bottom

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions); // Ensure XML file exists and matches this name

        // Initialize views
        acceptButton = findViewById(R.id.accept_button);
        termsScrollView = findViewById(R.id.terms_scroll_view);
        termsTextView = findViewById(R.id.terms_text_view);

        // Set formatted terms and conditions text
        termsTextView.setText(Html.fromHtml(getString(R.string.terms_and_conditions), Html.FROM_HTML_MODE_COMPACT));
        
        // Check if the app is in night mode
        int nightModeFlags = getResources().getConfiguration().uiMode & 
                             Configuration.UI_MODE_NIGHT_MASK;
        
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            // Night mode is active, set colors for better contrast
            termsTextView.setTextColor(ContextCompat.getColor(this, R.color.white));
            termsScrollView.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        } else {
            // Day mode is active, use default theme colors or set specific day mode colors
            termsTextView.setTextColor(ContextCompat.getColor(this, R.color.black));
            termsScrollView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }

        // Initially disable the back button
        acceptButton.setEnabled(false);

        // Scroll listener to check if the user has scrolled to the bottom
        termsScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            // Check if the ScrollView is scrolled to the bottom
            View contentView = termsScrollView.getChildAt(0);
            if (contentView != null &&
                    contentView.getBottom() <= (termsScrollView.getScrollY() + termsScrollView.getHeight())) {
                if (!hasScrolledToBottom) {
                    hasScrolledToBottom = true; // Set the flag to true
                    acceptButton.setEnabled(true); // Enable the back button
                }
            }
        });

        // Back button listener to finish the activity and send result
        acceptButton.setOnClickListener(v -> {
            // Return result indicating that the terms were accepted
            setResult(RESULT_OK);  // RESULT_CANCELED means terms have not been accepted
            finish();  // Close the Terms and Conditions activity
        });
    }
}
