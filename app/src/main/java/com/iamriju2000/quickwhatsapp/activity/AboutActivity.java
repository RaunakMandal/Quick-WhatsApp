package com.iamriju2000.quickwhatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iamriju2000.quickwhatsapp.R;
import com.iamriju2000.quickwhatsapp.adapter.OSAdapter;
import com.iamriju2000.quickwhatsapp.util.OpenSourceLibs;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<OpenSourceLibs> oslibs = new ArrayList<>();
        oslibs.add(new OpenSourceLibs("Lottie", "Lottie is an open source animation file format that's tiny, high quality, interactive, and can be manipulated at runtime.", "https://github.com/airbnb/lottie-android"));
        oslibs.add(new OpenSourceLibs("CircularImageView", "Create circular ImageView in Android in the simplest way possible.", "https://github.com/lopspower/CircularImageView"));

        OSAdapter osAdapter = new OSAdapter(this, oslibs);
        ListView lv = (ListView) findViewById(R.id.lv_abt);
        lv.setAdapter(osAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OpenSourceLibs osl = (OpenSourceLibs) parent.getItemAtPosition(position);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(osl.getUrl())));
            }
        });
    }
}