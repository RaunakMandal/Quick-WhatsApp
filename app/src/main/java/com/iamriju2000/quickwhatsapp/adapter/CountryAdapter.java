package com.iamriju2000.quickwhatsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iamriju2000.quickwhatsapp.util.CountryItem;
import com.iamriju2000.quickwhatsapp.R;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<CountryItem> {
    public CountryAdapter(Context context, ArrayList<CountryItem> countryList) {
        super(context, 0, countryList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_list, parent, false);
        }
        ImageView flags = convertView.findViewById(R.id.flag);
        TextView countryCode = convertView.findViewById(R.id.c_name);

        CountryItem currentItem = getItem(position);

        if (currentItem != null) {
            flags.setImageResource(currentItem.getFlagImage());
            countryCode.setText(currentItem.getCountryName());
        }

        return convertView;
    }
}
