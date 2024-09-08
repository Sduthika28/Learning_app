package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, regis;
    private TextView title;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.editText_username); // assuming editText_username is the id of your EditText in XML
        password = findViewById(R.id.editText_password); // assuming editText_password is the id of your EditText in XML
        login = findViewById(R.id.button_login); // assuming button_login is the id of your login button in XML
        regis = findViewById(R.id.button_register); // assuming button_register is the id of your register button in XML
        title = findViewById(R.id.title);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve email and password from EditText fields
                /*String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                Toast.makeText(MainActivity.this, "Clicked signin", Toast.LENGTH_SHORT).show();

                // Perform basic validation
                if (name.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(name, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, content.class)); // Navigate to DashboardActivity
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/
                Intent intent = new Intent(MainActivity.this, Third.class); // assuming RegisterActivity is the name of your registration activity
                startActivity(intent);
                finish();
            }
        });

        regis.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Register.class); // assuming RegisterActivity is the name of your registration activity
            startActivity(intent);
            finish();
        });

        username.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "Title: EXAM EDGE", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when the text is changed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text is changed
                String text = s.toString();
                Toast.makeText(MainActivity.this, "Hi " + text + "!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}