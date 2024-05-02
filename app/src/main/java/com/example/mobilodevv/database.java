package com.example.mobilodevv;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class database {
    private int VERSİON=1;
    private String DATABASE_NAME="database.db";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public database(Context _context) {
        context=_context;
        dbHelper = new DatabaseHelper(_context);
    }
    public class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME,null, VERSİON);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
              db.execSQL("CREATE TABLE [KİSİLER](\n" +
                      "[id] INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                      "[adsoyad] TEXT,\n" +
                      "[tel_no] TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public database open(){
        db=dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        db.close();
    }
    public void Kisiekle(Kisi kisi){
        open();
        ContentValues values= new ContentValues();
        values.put("adsoyad",kisi.getAdsoyad());
        values.put("tel_no",kisi.getTelefonno());
        db.insert("KİSİLER",null,values);
        close();

    }
    @SuppressLint("Range")
    public List<Kisi> Kisilistele(){
        open();
        List<Kisi> Kisiler = new ArrayList<Kisi>();
        Cursor cursor = db.rawQuery("SELECT * FROM KİSİLER",null);
        if(cursor!=null && !cursor.isClosed()){
              while (cursor.moveToNext()){
                  Kisi kisi =new Kisi();
                  kisi.setAdsoyad(cursor.getString(cursor.getColumnIndex("adsoyad")));
                  kisi.setTelefonno(cursor.getString(cursor.getColumnIndex("tel_no")));
                  Kisiler.add(kisi);

                }
              cursor.close();
        }

        close();

        return Kisiler;

    }



    public void Kisisil(int id){
        open();
        db.delete("KİSİLER","id="+id,null);
        close();
    }
    public void KisiGuncelle(Kisi kisi){
        open();
        ContentValues values= new ContentValues();
        values.put("adsoyad",kisi.getAdsoyad());
        values.put("tel_no",kisi.getTelefonno());
        db.update("KİSİLER",values,"id="+kisi.getId(),null);
        close();
    }
}
