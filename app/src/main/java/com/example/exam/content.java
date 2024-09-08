package com.example.exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class content extends AppCompatActivity {

    Button enrolls, sms_send, My_avail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Ensure EdgeToEdge utility is properly included
        setContentView(R.layout.activity_content);

        enrolls = findViewById(R.id.my_button);
        sms_send = findViewById(R.id.smss);
        My_avail = findViewById(R.id.my_but);
        Button back = findViewById(R.id.h_back);

        // Ensure the content view ID exists in your layout file
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        enrolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(content.this, enrollment.class);
                startActivity(intent);
                finish(); // Finish current activity if necessary
            }
        });

        sms_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(content.this, sms.class);
                startActivity(intent);
                finish(); // Finish current activity if necessary
            }
        });

        My_avail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(content.this, available.class);
                startActivity(intent);
                finish(); // Finish current activity if necessary
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(content.this, Third.class);
                startActivity(intent);
                finish(); // Finish current activity if necessary
            }
        });
    }
}
