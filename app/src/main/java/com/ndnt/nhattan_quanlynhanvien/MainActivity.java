package com.ndnt.nhattan_quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText edtma,edtten;
    RadioGroup gioitinh;
    Button btnnhap;
    ImageButton xxx;

    ListView lv;
    NhanVienAdap nhanVienAdap;
    ArrayList<NhanVien> nhanViens;

    SQLiteDatabase database;
    String tentable="qlnhanvien.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thamchieu();


        database=openOrCreateDatabase(tentable,MODE_PRIVATE,null);
        try {
            createTable();
        }
        catch (Exception e)
        {
            
        }

        nhapData();
        btnnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nhap();
            }
        });

        xxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xoa();
            }
        });
    }

    private void nhapData()
    {
        nhanViens.clear();
        Cursor cursor=database.query("nhanvien",null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            String manv=cursor.getString(0);
            String tennv=cursor.getString(1);
            int gt=cursor.getInt(2);
            boolean gtt=true;
            if(gt==0)
                gtt=false;

            NhanVien tam=new NhanVien(manv,tennv,gtt,false);
            nhanViens.add(tam);
        }
        nhanVienAdap.notifyDataSetChanged();
        cursor.close();

    }

    private void createTable()
    {
        String sql="CREATE TABLE "+ "nhanvien" + " ( manv TEXT primary key, tennv TEXT, gioitinh INTEGER)";
        database.execSQL(sql);
        Toast.makeText(this,"Thanh cong",Toast.LENGTH_SHORT).show();
    }

    private void Xoa()
    {
        ArrayList<String> maxoa=new ArrayList<>();
        for(int i=0;i<nhanViens.size();i++)
        {
            if(nhanViens.get(i).isCheck())
            {
                maxoa.add(nhanViens.get(i).getManv());
            }
        }

        for(int i=0;i<maxoa.size();i++)
        {
            database.delete("nhanvien","manv=?",new String[]{maxoa.get(i)});
        }
        nhapData();
        nhanVienAdap.notifyDataSetChanged();
    }

    private void Nhap()
    {
        String manv=edtma.getText().toString();
        String ten=edtten.getText().toString();
        boolean gt;

        int ci=gioitinh.getCheckedRadioButtonId();
        if(ci==R.id.gtnam)
            gt=true;
        else
            gt=false;

        ContentValues contentValues=new ContentValues();
        contentValues.put("manv",manv);
        contentValues.put("tennv",ten);
        contentValues.put("gioitinh",gt);
        database.insert("nhanvien",null,contentValues);
        nhapData();
        nhanVienAdap.notifyDataSetChanged();
    }

    private void thamchieu()
    {
        edtma=findViewById(R.id.edtma);
        edtten=findViewById(R.id.edtten);
        gioitinh=findViewById(R.id.gioitinh);
        btnnhap=findViewById(R.id.btnnhap);
        xxx= findViewById(R.id.xx);
        lv=findViewById(R.id.lv);
        nhanViens=new ArrayList<>();
        nhanVienAdap=new NhanVienAdap(this,R.layout.layoutnhanvien,nhanViens);
        lv.setAdapter(nhanVienAdap);
    }
}