package com.isc.absa.englishglossary.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.isc.absa.englishglossary.database.DBContract.Glossary;

/**
 * Created by Absalom on 06/08/2015.
 */
public final class DBGlossary extends SQLiteOpenHelper{

    public static final String COMMA=", ";

    public static final String SQL_Create_Glossary=
            "CREATE TABLE "+ DBContract.Glossary.TABLE_NAME+"("+
                    Glossary.column_id+" INTEGER PRIMARY KEY"+COMMA+
                    Glossary.column_word+" TEXT"+COMMA+
                    Glossary.column_pos+" TEXT"+COMMA+
                    Glossary.column_meaning+" TEXT"+COMMA+
                    Glossary.column_example+" TEXT"+COMMA+
                    Glossary.column_esword+" TEXT"+COMMA+
                    Glossary.column_date+" TEXT"+COMMA+
                    Glossary.column_title+" TEXT"+COMMA+
                    Glossary.column_subtitle+" TEXT )";

    public static final String SQL_Drop_Glossary=
            "DROP TABLE IF EXISTS "+Glossary.TABLE_NAME;

    /*********************************************************/
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "GlossaryDB.db";
    /********************************************************/

    public DBGlossary(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_Create_Glossary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_Drop_Glossary);
        onCreate(db);
    }
}
