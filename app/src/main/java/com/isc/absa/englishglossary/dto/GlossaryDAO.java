package com.isc.absa.englishglossary.dto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.isc.absa.englishglossary.database.DBContract.Glossary;
import com.isc.absa.englishglossary.database.DBGlossary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Absalom on 06/08/2015.
 */
public class GlossaryDAO {
    private DBGlossary dbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = {
            Glossary._ID,
            Glossary.column_id,
            Glossary.column_word,
            Glossary.column_pos,
            Glossary.column_meaning,
            Glossary.column_example,
            Glossary.column_esword,
            Glossary.column_date,
            Glossary.column_title,
            Glossary.column_subtitle
    };

    public GlossaryDAO(Context context) {
        dbHelper = new DBGlossary(context);
    }

    private void openWrite() {
        db = dbHelper.getWritableDatabase();
    }

    private void openRead() {
        db = dbHelper.getReadableDatabase();
    }

    private void close(Cursor c) {
        c.close();
        db.close();
    }

    private void close() {
        db.close();
    }

    public boolean create(GlossaryDTO glossary){
        openWrite();
        ContentValues values=new ContentValues();
		values.put(allColumns[0], glossary.get_ID());
        values.put(allColumns[1], glossary.getId());
        values.put(allColumns[2], glossary.getWord());
        values.put(allColumns[3], glossary.getPos());
        values.put(allColumns[4], glossary.getMeaning());
        values.put(allColumns[5], glossary.getExample());
        values.put(allColumns[6], glossary.getEs_word());
        values.put(allColumns[7], glossary.getDate());
        values.put(allColumns[8], glossary.getTitle());
        values.put(allColumns[9], glossary.getSubtitle());


        long valor=db.insert(Glossary.TABLE_NAME, null, values);
        close();
        return (valor!=-1)?true:false;
    }

    public GlossaryDTO read(long id){
        GlossaryDTO gdto=null;
        openRead();
        String selection=Glossary.column_id+ " = ?";
        String[] selectionArgs={id+""};
        Cursor c=db.query(Glossary.TABLE_NAME, allColumns, selection, selectionArgs, null, null, null);
        if(c.getCount()>0){
            c.moveToFirst();
            gdto=result(c);
        }
        close(c);
        return gdto;
    }

    public List<GlossaryDTO> readAll() {
        openRead();
        List<GlossaryDTO> words = null;
        Cursor c = db.query(Glossary.TABLE_NAME, allColumns, null, null, null,null,  Glossary._ID + " ASC");
        if (c.getCount() > 0) {
            words = new ArrayList<GlossaryDTO>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                words.add(result(c));
                c.moveToNext();
            }
        }
        close(c);
        return words;
    }

    public boolean update(GlossaryDTO word){
        openRead();
// New value for one column
        ContentValues values = new ContentValues();
       // values.put(allColumns[0], word.get_ID());
       // values.put(allColumns[1], word.get_ID());
        values.put(allColumns[2], word.getWord());
        values.put(allColumns[3], word.getPos());
        values.put(allColumns[4], word.getMeaning());
        values.put(allColumns[5], word.getExample());
        values.put(allColumns[6], word.getEs_word());
        values.put(allColumns[7], word.getDate());
        values.put(allColumns[8], word.getTitle());
        values.put(allColumns[9], word.getSubtitle());


        String selection = allColumns[1] + " = ?";
        String[] selectionArgs = { String.valueOf(word.getId()) };

        int count = db.update(
                Glossary.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        close();
        return (count!=-1)?true:false;
    }

    public boolean deleteAll(){
        openWrite();
        long res=db.delete(Glossary.TABLE_NAME, null, null);
        close();
        return (res!=-1)?true:false;
    }

    public boolean delete(long id){
        openWrite();
        String whereClause = Glossary._ID + " = ?";
        String[] whereArgs = { id + "" };
        long res = db.delete(Glossary.TABLE_NAME, whereClause, whereArgs);
        close();
        return (res!=-1)?true:false;
    }




    private GlossaryDTO result(Cursor c) {
        GlossaryDTO gDTO = new GlossaryDTO();
        gDTO.set_ID(c.getLong(c.getColumnIndex(allColumns[0])));
        gDTO.setId(c.getInt(c.getColumnIndex(allColumns[1])));
        gDTO.setWord(c.getString(c.getColumnIndex(allColumns[2])));
        gDTO.setPos(c.getString(c.getColumnIndex(allColumns[3])));
        gDTO.setMeaning(c.getString(c.getColumnIndex(allColumns[4])));
        gDTO.setExample(c.getString(c.getColumnIndex(allColumns[5])));
        gDTO.setEs_word(c.getString(c.getColumnIndex(allColumns[6])));
        gDTO.setDate(c.getString(c.getColumnIndex(allColumns[7])));
        gDTO.setTitle(c.getString(c.getColumnIndex(allColumns[8])));
        gDTO.setSubtitle(c.getString(c.getColumnIndex(allColumns[9])));
        return gDTO;
    }

}
