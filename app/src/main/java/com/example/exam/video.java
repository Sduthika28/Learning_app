package com.example.exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class video extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        // Initialize the WebView
        WebView webview = findViewById(R.id.webview);
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/V2KCAfHjySQ?si=fgVjw9OmIDk0gtoh\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webview.loadData(video, "text/html", "utf-8");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());

        // Initialize the Button
        Button myButton = findViewById(R.id.comment);
        Button Backbutton = findViewById(R.id.backbutton);

        // Set an OnClickListener for the Button
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the action to be taken when the button is clicked
                Intent intent = new Intent(video.this, comments.class); // Replace AnotherActivity with the activity you want to start
                startActivity(intent);
            }
        });

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the action to be taken when the button is clicked
                Intent intent = new Intent(video.this, courses.class); // Replace AnotherActivity with the activity you want to start
                startActivity(intent);
            }
        });
    }
}
