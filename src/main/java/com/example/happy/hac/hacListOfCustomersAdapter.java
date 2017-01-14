package com.example.happy.hac;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 10/24/2016.
 */

public class hacListOfCustomersAdapter extends ArrayAdapter {

    List<String> data = new ArrayList<>();
    List<TextView>  textViews = new ArrayList<>();
    public hacListOfCustomersAdapter(Context context, int resource , List<String> list) {
        super(context, resource);
        data = list;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
            textViews.get(position).setText(data.get(position));
        return textViews;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.haclistofcustomersarrayadapter, parent, false);
            }
        TextView textView = (TextView) convertView.findViewById(R.id.hac_list_of_customers_adapter_text_view);
        for(String x: data){
            add(textView);
        }
        return convertView;
    }
}
