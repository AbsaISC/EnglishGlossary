package com.isc.absa.englishglossary.database;

import android.provider.BaseColumns;

/**
 * Created by Absalom on 05/08/2015.
 */
public final class DBContract {
    public DBContract(){}
    public static abstract class Glossary implements BaseColumns{
        public static final String TABLE_NAME="Glossary";
        public static final String column_id="id";
        public static final String column_word="word";
        public static final String column_pos="speech";
        public static final String column_meaning="meaning";
        public static final String column_example="example";
        public static final String column_esword="spanish";
        public static final String column_date="date";
        public static final String column_title="title";
        public static final String column_subtitle="subtitle";
    }
}
