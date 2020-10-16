package com.iamriju2000.quickwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        EditText number = (EditText) findViewById(R.id.number);
        EditText message = (EditText) findViewById(R.id.message);
        String phoneNum = number.getText().toString(), phoneNumber = "";
        if (phoneNum.matches("")) {
            Toast.makeText(this, "Please Enter a Phone Number!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            phoneNumber += phoneNum;
        }
        String sendMessage = message.getText().toString();
        String url = "https://api.whatsapp.com/send?phone=+91" + phoneNumber + "&text=" + sendMessage;
        Intent sendM = new Intent(Intent.ACTION_VIEW);
        sendM.setData(Uri.parse(url));
        if (sendM.resolveActivity(getPackageManager()) != null) {
            startActivity(sendM);
        }
    }
}