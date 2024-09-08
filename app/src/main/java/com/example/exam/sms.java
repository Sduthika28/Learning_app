package com.example.exam;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;



public class sms extends AppCompatActivity {

    EditText phone, message;
    Button msgsent;

    ImageView back;

    ImageView room;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sms);

        back = findViewById(R.id.s_back);
        room = findViewById(R.id.s_home);

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sms.this, Third.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sms.this, content.class);
                startActivity(intent);
            }
        });



        phone = findViewById(R.id.phonenum);
        message = findViewById(R.id.smss);
        msgsent = findViewById(R.id.smsbutton);




        msgsent.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){

                if (ContextCompat.checkSelfPermission(sms.this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else{
                    ActivityCompat.requestPermissions(sms.this, new String[]{Manifest.permission.SEND_SMS},
                            100);
                }

            }


        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sms), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else{
            Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
        }

    }


    private void sendSMS(){

        String Phone = phone.getText().toString();
        String Message = message.getText().toString();

        if (!Phone.isEmpty() && !Message.isEmpty()) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(Phone, null, Message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"plese enter the phone num and message",Toast.LENGTH_SHORT).show();
        }

    }
}
