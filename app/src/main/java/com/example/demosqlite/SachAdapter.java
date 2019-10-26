package com.example.demosqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> lstSach;
    private LayoutInflater layoutInflater;
    private int layout;
    public SachAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Sach> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.lstSach =objects;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlaceHolder placeHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(this.layout,null);
            placeHolder=new PlaceHolder();
            placeHolder.tvIdSach =convertView.findViewById(R.id.tvIdSach);
            placeHolder.tvTenSach =convertView.findViewById(R.id.tvTenSach);
            placeHolder.tvIdTacGia =convertView.findViewById(R.id.tvIdKH);
            convertView.setTag(placeHolder);
        }
        else placeHolder=(PlaceHolder)convertView.getTag();
        Sach book= lstSach.get(position);
        placeHolder.tvIdSach.setText("id_book:"+book.getId_Sach()+"");
        placeHolder.tvTenSach.setText("title:"+book.getTenSach());
        placeHolder.tvIdTacGia.setText("id_author:"+book.getId_TacGia());
        return convertView;
    }
    static  class PlaceHolder{
        TextView tvIdSach, tvTenSach, tvIdTacGia;
    }
}
