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
        if (phone == null) {
            phoneNo = text.toString();
        } else {
            phoneNo = phone;
        }
        Log.d("QWP", phoneNo + " " + text.toString());
//        phoneNo = phoneNo.replaceAll("\\s", "");
//        phoneNo = phoneNo.replaceAll("-", "");
        Log.d("QWP", "Ph: " + phoneNo);
        if (phoneNo == null) {
            Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
            finish();
        }

        String regex = "^[\\d\\(\\)\\-+]+$";

        String countryCode = "";
        if (!phoneNo.startsWith("+") && phoneNo.length() == 10) {
            countryCode = "+91";
        }

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNo);
        if (!m.matches()) {
            Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
            Log.d("QWP", String.valueOf(m.matches()));
            finish();
        } else {
            final String url = "whatsapp://send?phone=" + countryCode + phoneNo;
//            final String url = "https://api.whatsapp.com/send?phone=+" + countryCode + phone + "&text=" + messageText;

            Intent messageIntent = new Intent(Intent.ACTION_VIEW);
            messageIntent.setData(Uri.parse(url));

            try {
                messageIntent.setPackage("com.whatsapp");
                startActivity(messageIntent);
            } catch (Exception e) {
                new AlertDialog.Builder(ShareMenu.this)
                        .setTitle(R.string.wp_not_installed)
                        .setMessage(R.string.download_play_store)
                        .setPositiveButton(R.string.download, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whatsapp")));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.logo)
                        .show();
                Log.d("QWP", "Couldn't find WhatsApp");
            }
            finish();
        }
    }
}