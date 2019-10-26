package com.example.demosqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnTTSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTTSach=(Button)findViewById(R.id.btnTTSAch);
        btnTTSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                View view1= LayoutInflater.from(MainActivity.this).inflate(R.layout.customdialog,null);
                setWidged(view1);
                builder.setView(view1);
                builder.setTitle("Thông tin sách");
                AlertDialog alertDialog=builder.create();
                alertDialog.show();


            }
        });
    }
    private void setWidged(View view){
        Button btnSave=(Button)view.findViewById(R.id.btnSave);
        Button btnDelete=(Button)view.findViewById(R.id.btnDelete);
        Button btnUpdate=(Button)view.findViewById(R.id.btnUpdate);
        Button btnSelect=(Button)view.findViewById(R.id.btnSelect);
        final EditText edID=(EditText)view.findViewById(R.id.edMaSach);
        final EditText edTenS=(EditText)view.findViewById(R.id.edTenSach);
        final EditText edIDTG=(EditText)view.findViewById(R.id.edMaTG);
        final GridView gridView=(GridView)view.findViewById(R.id.gridView);

        final DBHelper dbHelper=new DBHelper(MainActivity.this,"QuanLySach",null,1);
        final ArrayList<Sach> lst_Sach= dbHelper.GetAllBook();
        final SachAdapter adapter=new SachAdapter(MainActivity.this,R.layout.customgridviewitem,lst_Sach);

        gridView.setAdapter(adapter);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach=new Sach();
                sach.setId_Sach(Integer.parseInt(edID.getText().toString()));
                sach.setTenSach(edTenS.getText().toString());
                sach.setId_TacGia(Integer.parseInt(edIDTG.getText().toString()));
                if(Search_book(sach.getId_Sach(),lst_Sach)==-1) {
                    if (dbHelper.InsertBook(sach)) {
                        Toast.makeText(MainActivity.this, "Đã lưu thành công", Toast.LENGTH_SHORT).show();
                        lst_Sach.add(sach);
                        adapter.notifyDataSetChanged();
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "Mã trùng", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maso= Integer.parseInt(edID.getText().toString());
                if( dbHelper.DeleteBook(maso)){
                    int index=Search_book(maso,lst_Sach);
                    lst_Sach.remove(index);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maso=Integer.parseInt(edID.getText().toString());
                String tieude=edTenS.getText().toString();
                int tentg=Integer.parseInt(edIDTG.getText().toString());
                Sach book=new Sach(maso,tieude,tentg);
                if(dbHelper.UpdateBook(book)){
                    int index=Search_book(maso,lst_Sach);
                    lst_Sach.get(index).setTenSach(book.getTenSach());
                    lst_Sach.get(index).setId_TacGia(book.getId_TacGia());
                    Toast.makeText(MainActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
                else Toast.makeText(MainActivity.this, "Updata không thành công", Toast.LENGTH_SHORT).show();

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Danh sách", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private int Search_book(int book_id,ArrayList<Sach>list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId_Sach()==book_id)
                return i;
        }
        return -1;
    }
}
