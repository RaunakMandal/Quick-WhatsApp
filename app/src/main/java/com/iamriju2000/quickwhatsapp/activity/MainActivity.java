package com.iamriju2000.quickwhatsapp.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iamriju2000.quickwhatsapp.adapter.CountryAdapter;
import com.iamriju2000.quickwhatsapp.util.CountryItem;
import com.iamriju2000.quickwhatsapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String countryCode = "";

    private ArrayList<CountryItem> countryName;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        Spinner countriesList = findViewById(R.id.country);
        countryAdapter = new CountryAdapter(this, countryName);
        countriesList.setAdapter(countryAdapter);

        countriesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                countryCode = clickedItem.getCountryName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initList() {
        countryName = new ArrayList<>();
        countryName.add(new CountryItem("+91", R.drawable.india));
        countryName.add(new CountryItem("+880", R.drawable.bd));
        countryName.add(new CountryItem("+1", R.drawable.us));
        countryName.add(new CountryItem("+44", R.drawable.uk));
        countryName.add(new CountryItem("+971", R.drawable.uae));

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
            sendM.setPackage("com.whatsapp");
            startActivity(sendM);
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
        Uri git_repo = Uri.parse("https://github.com/RaunakMandal/Quick-WhatsApp");
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
            case R.id.repo:
                startActivity(new Intent(Intent.ACTION_VIEW, git_repo));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}