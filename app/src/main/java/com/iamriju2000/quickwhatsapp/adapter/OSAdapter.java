package com.iamriju2000.quickwhatsapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iamriju2000.quickwhatsapp.R;
import com.iamriju2000.quickwhatsapp.util.OpenSourceLibs;

import java.util.ArrayList;

public class OSAdapter extends ArrayAdapter<OpenSourceLibs> {


    public OSAdapter(Context context, ArrayList<OpenSourceLibs> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.os_items, parent, false);
        }

        OpenSourceLibs osl = getItem(position);

        TextView name = (TextView) convertView.findViewById(R.id.name_os);
        TextView desc = (TextView) convertView.findViewById(R.id.desc_os);

        name.setText(osl.getName());
        desc.setText(osl.getDescription());

        return convertView;
    }
}
