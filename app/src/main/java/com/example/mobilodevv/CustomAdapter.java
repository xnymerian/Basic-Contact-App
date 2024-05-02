package com.example.mobilodevv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Kisi> {
    private Context mContext;
    private int mResource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Kisi> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }
        database db = new database(CustomAdapter.this.getContext());
        List<Kisi> kisilerListesi = db.Kisilistele();

        Kisi currentItem = getItem(position);

        if (currentItem != null) {
            TextView adSoyadTextView = convertView.findViewById(R.id.adsoyadTextView);
            TextView telefonNoTextView = convertView.findViewById(R.id.telefonnoTextView);
            ImageView imageView2 = convertView.findViewById(R.id.imageView2);

            adSoyadTextView.setText(currentItem.getAdsoyad());
            telefonNoTextView.setText(currentItem.getTelefonno());
        }

        return convertView;

}}
