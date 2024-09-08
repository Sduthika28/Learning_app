package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText usernameEditText, nameEditText, emailEditText, passwordEditText;
    private Button signUpButton;
    private static final int MAX_RETRIES = 3;
    private int retryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        usernameEditText = findViewById(R.id.username);
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signUpButton = findViewById(R.id.signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTxt = usernameEditText.getText().toString().trim();
                String nameTxt = nameEditText.getText().toString().trim();
                String emailTxt = emailEditText.getText().toString().trim();
                String passwordTxt = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(usernameTxt) || TextUtils.isEmpty(nameTxt) || TextUtils.isEmpty(emailTxt) || TextUtils.isEmpty(passwordTxt)) {
                    Toast.makeText(Register.this, "Enter the required fields", Toast.LENGTH_SHORT).show();
                } else {
                    createUser(usernameTxt, nameTxt, emailTxt, passwordTxt);
                }
            }
        });
    }

    private void createUser(String username, String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeUserData(username, name, email, password);
                        } else {
                            handleRegistrationError(task.getException(), username, name, email, password);
                        }
                    }
                });
    }

    private void handleRegistrationError(Exception exception, String username, String name, String email, String password) {
        if (exception != null) {
            if (exception instanceof FirebaseNetworkException && retryCount < MAX_RETRIES) {
                retryCount++;
                Toast.makeText(Register.this, "Network error, retrying... (" + retryCount + ")", Toast.LENGTH_SHORT).show();
                createUser(username, name, email, password);
            } else {
                Log.e("RegistrationError", "Registration failed: " + exception.getMessage());
                Toast.makeText(Register.this, "Registration failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Register.this, "Registration failed for unknown reasons", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeUserData(String username, String name, String email, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);
        user.put("name", name);
        user.put("password", password);

        db.collection("users").document(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (task.getException() != null) {
                                Log.e("DataStorageError", "Data storage failed: " + task.getException().getMessage());
                            }
                            Toast.makeText(Register.this, "Data storage failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
