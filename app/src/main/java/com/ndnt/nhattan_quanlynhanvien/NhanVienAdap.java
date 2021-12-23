package com.ndnt.nhattan_quanlynhanvien;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NhanVienAdap extends ArrayAdapter<NhanVien>
{
    Activity context;
    int id;
    ArrayList<NhanVien> nhanViens;


    public NhanVienAdap(Activity context,int id,ArrayList<NhanVien> nhanViens) {
        super(context, id, nhanViens);
        this.context=context;
        this.id=id;
        this.nhanViens=nhanViens;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        convertView=layoutInflater.inflate(id,null);

        NhanVien nhanVien=nhanViens.get(position);
        ImageView imageView=convertView.findViewById(R.id.imggt);
        TextView textView=convertView.findViewById(R.id.txtmavaname);
        CheckBox checkBox=convertView.findViewById(R.id.checkxoa);

        if(nhanVien.isGioitinh())
           imageView.setImageResource(R.drawable.male);
        else
            imageView.setImageResource(R.drawable.female);


        textView.setText("  "+nhanVien.getManv()+" - "+nhanVien.getTennv());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVien.setCheck(checkBox.isChecked());
            }
        });


        return convertView;
    }
}
