package com.iamriju2000.quickwhatsapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.iamriju2000.quickwhatsapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShareMenu extends AppCompatActivity {
    private String phoneNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String phone = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        CharSequence text = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            text = getIntent()
                    .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
        } else {
            Toast.makeText(this, "Available for Android M and above!", Toast.LENGTH_SHORT).show();
            finishAffinity();
        }
        if (text != null) {
            phoneNo = text.toString();
        } else if (phone != null) {
            phoneNo = phone;
        } else {
            Toast.makeText(this, "Failed to parse number!", Toast.LENGTH_SHORT).show();
            finishAffinity();
        }

        phoneNo = phoneNo.replaceAll("\\D+", "");
        Log.d("QWP", "Ph: " + phoneNo);
        if (phoneNo == null || phoneNo.isEmpty()) {
            Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
            finish();
        } else {

//        String regex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";

            String countryCode = "";
            String finalNumber = "";
            if (!phoneNo.startsWith("+") && phoneNo.length() == 10) {
                countryCode = "+91";
                finalNumber += phoneNo;
            } else {
                Log.d("QWP", "comes");
                finalNumber += "+" + phoneNo;
            }
            Log.d("QWP", finalNumber);

//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(finalNumber);
//        Log.d("QWP", String.valueOf(m));
//        if (!m.matches()) {
//            Log.d("QWP", "not match");
//            Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
//            finish();
//        } else {
            final String url = "whatsapp://send?phone=" + countryCode + finalNumber;
//            final String url = "https://api.whatsapp.com/send?phone=+" + countryCode + phone + "&text=" + messageText;

            Intent messageIntent = new Intent(Intent.ACTION_VIEW);
            messageIntent.setData(Uri.parse(url));

            try {
                messageIntent.setPackage("com.whatsapp");
                startActivity(messageIntent);
            } catch (Exception e) {
                Toast.makeText(this, "You do not have WhatsApp installed!", Toast.LENGTH_SHORT).show();
                Log.d("QWP", "Couldn't find WhatsApp");
            }
            finish();
        }
    }
}