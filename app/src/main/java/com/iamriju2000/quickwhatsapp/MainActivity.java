package com.iamriju2000.quickwhatsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String countryCode = "";

    ArrayList<String> countryName = new ArrayList<String>(
            Arrays.asList("\uD83C\uDDEE\uD83C\uDDF3 +91", "\uD83C\uDDFA\uD83C\uDDF8 +1", "\uD83C\uDDEC\uD83C\uDDE7 +44", "\uD83C\uDDE9\uD83C\uDDEA +49", "\uD83C\uDDE6\uD83C\uDDFA +61"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner countryList = (Spinner) findViewById(R.id.country);
        countryList.setOnItemSelectedListener(this);

        ArrayAdapter<String> spinnerAdap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryName);
        spinnerAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countryList.setAdapter(spinnerAdap);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setCode(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        countryCode = "91";
    }

    public void setCode(int id) {
        if (id == 0) {
            countryCode = "91";
        } else if (id == 1) {
            countryCode = "1";
        } else if (id == 2) {
            countryCode = "44";
        } else if (id == 3) {
            countryCode = "49";
        } else if (id == 4) {
            countryCode = "61";
        }
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
        String url = "https://api.whatsapp.com/send?phone=+" + countryCode + phoneNumber + "&text=" + sendMessage;
        Intent sendM = new Intent(Intent.ACTION_VIEW);
        sendM.setData(Uri.parse(url));
        if (sendM.resolveActivity(getPackageManager()) != null) {
            startActivity(sendM);
        }
    }
}