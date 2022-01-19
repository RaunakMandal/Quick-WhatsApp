package com.iamriju2000.quickwhatsapp.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;
import com.iamriju2000.quickwhatsapp.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button sendButton;
    private EditText phoneNumber, textMessage;
    private CountryCodePicker countryCodePicker;
    private String countryCode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();
    }

    private void initViews() {
        countryCodePicker = findViewById(R.id.countryCodePicker);
        countryCodePicker.showCloseIcon(true);
        countryCode = countryCodePicker.getDefaultCountryCodeWithPlus();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryCode = countryCodePicker.getSelectedCountryCodeWithPlus();
                Log.d("QWP", "On Picker Change: " + countryCodePicker.getSelectedCountryCodeWithPlus());
            }
        });

        phoneNumber = findViewById(R.id.phoneNumber);
        textMessage = findViewById(R.id.textMessage);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }

    public void send() {
        String phone = phoneNumber.getText().toString();
        if (phone.length() <= 0 || phone.isEmpty()) {
            Toast.makeText(MainActivity.this, "Phone number is not valid", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("QWP",  "Code " + countryCode + " Phone " + phone);
            String messageText = textMessage.getText().toString();
            final String url = "whatsapp://send?phone=+" + countryCode + phone + "&text=" + messageText;
            Intent messageIntent = new Intent(Intent.ACTION_VIEW);
            messageIntent.setData(Uri.parse(url));
            if (messageIntent.resolveActivity(getPackageManager()) != null) {
                messageIntent.setPackage("com.whatsapp");
                startActivity(messageIntent);
            } else {
                new AlertDialog.Builder(MainActivity.this)
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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri report_uri = Uri.parse("https://forms.gle/Nh9cAW3q44mZRngX7");
        Uri download_uri = Uri.parse("https://github.com/RaunakMandal/Quick-WhatsApp/releases/");
        int id = item.getItemId();

        switch (id) {
            case R.id.about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            case R.id.bug:
                startActivity(new Intent(Intent.ACTION_VIEW, report_uri));
                return true;
            case R.id.download:
                startActivity(new Intent(Intent.ACTION_VIEW, download_uri));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("QWP", adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}