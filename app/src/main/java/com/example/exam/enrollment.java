package com.example.exam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class enrollment extends AppCompatActivity {

    EditText name, gender, courseName, email, phone, address, city, state, zipCode, country;
    Button Submit;
    ImageView Aback, Groom;
    private static final int REQUEST_WRITE_STORAGE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll);

        Groom = findViewById(R.id.e_home);
        Aback = findViewById(R.id.e_back);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        courseName = findViewById(R.id.courseName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zipCode = findViewById(R.id.zipCode);
        country = findViewById(R.id.country);
        Submit = findViewById(R.id.submit);

        Groom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(enrollment.this, Third.class);
                startActivity(intent);
            }
        });

        Aback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(enrollment.this, content.class);
                startActivity(intent);
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    String studentName = name.getText().toString();
                    String studentGender = gender.getText().toString();
                    String studentCourseName = courseName.getText().toString();
                    String studentEmail = email.getText().toString();
                    String studentPhone = phone.getText().toString();
                    String studentAddress = address.getText().toString();
                    String studentCity = city.getText().toString();
                    String studentState = state.getText().toString();
                    String studentZipCode = zipCode.getText().toString();
                    String studentCountry = country.getText().toString();

                    // Save the student details to a file
                    saveToFile(studentName, studentGender, studentCourseName, studentEmail, studentPhone, studentAddress, studentCity, studentState, studentZipCode, studentCountry);
                }
            }
        });

        // Request write permission
        boolean hasPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                // Permission denied
                Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean validateInput() {
        if (name.getText().toString().isEmpty() ||
                gender.getText().toString().isEmpty() ||
                courseName.getText().toString().isEmpty() ||
                email.getText().toString().isEmpty() ||
                phone.getText().toString().isEmpty() ||
                address.getText().toString().isEmpty() ||
                city.getText().toString().isEmpty() ||
                state.getText().toString().isEmpty() ||
                zipCode.getText().toString().isEmpty() ||
                country.getText().toString().isEmpty()) {

            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveToFile(String name, String gender, String courseName, String email, String phone, String address, String city, String state, String zipCode, String country) {
        if (isExternalStorageWritable()) {
            File file = new File(Environment.getExternalStorageDirectory(), "enrollment.txt");

            try (FileOutputStream fos = new FileOutputStream(file, true)) {
                String data = "Name: " + name + "\n" +
                        "Gender: " + gender + "\n" +
                        "Course Name: " + courseName + "\n" +
                        "Email: " + email + "\n" +
                        "Phone: " + phone + "\n" +
                        "Address: " + address + "\n" +
                        "City: " + city + "\n" +
                        "State: " + state + "\n" +
                        "Zip Code: " + zipCode + "\n" +
                        "Country: " + country + "\n\n";

                fos.write(data.getBytes());
                Toast.makeText(this, "Your enrollment is successful", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "External storage is not writable", Toast.LENGTH_LONG).show();
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
