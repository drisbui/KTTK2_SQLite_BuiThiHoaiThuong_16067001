package com.example.demosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String   sql="create table TacGia"+
                "("+
                "id_TacGia int primary key,"+
                "tenTG text"+
                ")";
        db.execSQL(sql);
        sql  ="create table Sach"+
                "("+
                "id_Sach int primary key,"+
                "tenSach text,"+
                "id_TacGia int constraint author_id references TacGia(id_TacGia) on Delete cascade on update cascade"+
                ")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Sach");
        db.execSQL("DROP TABLE IF EXISTS TacGia");
        onCreate(db);
    }

    public boolean InsertTacGia(TacGia tacGia){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_TacGia",tacGia.getId_TacGia());
        values.put("tenTG",tacGia.getTenTG());
        db.insert("TacGia",null,values);
        return true;
    }

    public TacGia GetTacGia(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from TacGia where id_TacGia=?",new String[]{String.valueOf(id)});
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
        }
        else  return  null;
        TacGia tacGia=new TacGia(cursor.getInt(0),cursor.getString(1));
        cursor.close();
        return  tacGia;
    }

    public ArrayList<TacGia> GetAllTacGia(){
        ArrayList<TacGia> tacGias=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from TacGia",null);
        if(c!=null)
            c.moveToFirst();
        while (c.isAfterLast()==false){
            int id_TacGia = c.getInt(0);
            String tenTG = c.getString(1);
            tacGias.add(new TacGia(id_TacGia,tenTG));
            c.moveToNext();
        }
        c.close();
        return tacGias;
    }

    public boolean DeleteTacGia(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        int count=db.delete("TacGia","id_TacGia"+"=?",new String[]{String.valueOf(id)});
        if(count>0)
            return true;
        return  false;
    }
    public boolean UpdateTacGia(TacGia tacGia){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenTG",tacGia.getTenTG());
        int count=db.update("TacGia",contentValues,"id_TacGia"+"=?",new String[]{String.valueOf(tacGia.getId_TacGia())});
        if(count>0)
            return  true;
        return false;
    }

    public boolean InsertBook(Sach sach){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_Sach",sach.getId_Sach());
        values.put("tenSach",sach.getTenSach());
        values.put("id_TacGia",sach.getId_TacGia());
        db.insert("Sach",null,values);
        return true;
    }

    public Sach GetBook(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Sach where id_Sach=?",new String[]{String.valueOf(id)});
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
        }
        else  return  null;
        Sach book=new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        cursor.close();
        return  book;
    }

    public ArrayList<Sach> GetAllBook(){
        ArrayList<Sach> books=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from Sach",null);
        if(c!=null)
            c.moveToFirst();
        while (c.isAfterLast()==false){
            int id_Sach = c.getInt(0);
            String tenSach = c.getString(1);
            int id_TacGia = c.getInt(2);
            books.add(new Sach(id_Sach,tenSach,id_TacGia));
            c.moveToNext();
        }
        c.close();
        return books;
    }

    public boolean DeleteBook(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        int count=db.delete("Sach","id_Sach"+"=?",new String[]{String.valueOf(id)});
        if(count>0)
            return true;
        return  false;
    }
    public boolean UpdateBook(Sach book){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenSach",book.getTenSach());
        contentValues.put("id_TacGia",book.getId_TacGia());
        int count=db.update("Sach",contentValues,"id_Sach"+"=?",new String[]{String.valueOf(book.getId_Sach())});
        if(count>0)
            return  true;
        return false;
    }

    public ArrayList<String>getBookAuthor(int id){
        ArrayList<String>list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select  Sach.id_TacGia , Sach.tenSach "+
                "from TacGia inner join Sach on TacGia.id_TacGia = Sach.id_TacGia "+
                "where TacGia.id_TacGia="+id;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast()==false){
            list.add("id:"+cursor.getInt(0)+"-title:"+cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return  list;
    }
}
