package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class courses extends AppCompatActivity {

    ImageView img1;
    ImageView Arrow;
    ImageView Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses); // Ensure this is the correct layout

        img1 = findViewById(R.id.v1);
        Arrow = findViewById(R.id.arrow);
        Home = findViewById(R.id.home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.courses), (v, insets) -> {
            v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(),
                    insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
            return insets.consumeSystemWindowInsets();
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(courses.this, video.class);
                startActivity(intent);
                finish();
            }
        });

        Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(courses.this, "Arrow Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(courses.this, courses1.class);
                startActivity(intent);
                finish();
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(courses.this, Third.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
