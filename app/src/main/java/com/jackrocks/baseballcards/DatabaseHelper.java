package com.jackrocks.baseballcards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 11;
    public static final String DATABASE_NAME = "BaseballCards.db";
    public static final String BASEBALLCARD_LIST_TABLE_NAME = "baseball_cards_table";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "Name";
    public static final String COL_Brand = "Brand";
    public static final String COL_Year = "Year";
    public static final String COL_Team = "Team";
    public static final String COL_Description = "Description";
    public static final String COL_Price = "Price";
    public static final String[] CARDS_COLUMNS = {COL_ID, COL_NAME, COL_Brand, COL_Year, COL_Team, COL_Description, COL_Price};


    private static final String CREATE_BASEBALLCARD_LIST_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BASEBALLCARD_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT, " +
                    COL_Brand + " TEXT, " +
                    COL_Year + " TEXT, " +
                    COL_Team + " TEXT, " +
                    COL_Description + " TEXT, " +
                    COL_Price + " DOUBLE)";






    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BASEBALLCARD_LIST_TABLE);




    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BASEBALLCARD_LIST_TABLE_NAME);
        this.onCreate(db);
        addCard(db, 1, "Albert Pujols", "Fleer Ultra", "2007", "Cardinals", "Albert Pujols unsigned Fleer bat card", 32.99);
        addCard(db, 2, "Albert Pujols", "Topps", "2003", "Cardinals", "Albert Pujols Signed Prime Cuts Card Gem", 461.99);
        addCard(db, 3, "Albert Pujols", "Leaf", "2012", "Angels", "Albert Pujols 2012 Autograph card", 367.99);
        addCard(db, 4, "Albert Pujols", "Upper Deck", "2002", "Cardinals", "Albert Pujols signed 2002 Upper Deck Victory Card", 395.99);
        addCard(db, 5, "Albert Pujols", "Topps", "2001", "Cardinals", "2001 Topps Stars Albert Pujols Rookie Card. Gold Variation", 212.99);
        addCard(db, 6, "Yadier Molina", "Diampnd Kings Dual", "2016", "Cardinals", "Yadier Molina Autographed Dual Jersey bat card", 169.95);
        addCard(db, 7, "Yadier Molina", "National trasures", "2015", "Cardinals", "2015 First Home Run Cards", 14.99);
        addCard(db, 8, "Yadier Molina", "Donruss Elite", "2004", "Cardinals", "Yadier Molina Rookie Card", 59.99);
        addCard(db, 9, "Stephen Piscotty", "Bowman Chrome", "2013", "Cardinals", "Prospect Autograph Rookie Card", 49.99);
        addCard(db, 10, "Matt Adams", "Topps", "2012", "Cardinals", "Rookie Card", 2.99);
        addCard(db, 11, "Brandon Moss", "Topps", "2013", "A's", "Mini Black Edition", 49.99);
    }



    public void addCard(SQLiteDatabase db, int id, String Name, String Brand, String Year, String Team, String Description, double Price) {

        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_NAME, Name);
        values.put(COL_Brand, Brand);
        values.put(COL_Year, Year);
        values.put(COL_Team, Team);
        values.put(COL_Description, Description);
        values.put(COL_Price, Price);

        db.insert(BASEBALLCARD_LIST_TABLE_NAME, null, values);

    }


    public BaseballCard getBaseballCard( int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = new String[]{COL_ID, COL_NAME, COL_Brand, COL_Year, COL_Team, COL_Description, COL_Price};
        String selection = "_id=?";
        String[] selectionArgs = new String[]{
                String.valueOf(id)
        };

        Cursor cursor = db.query(BASEBALLCARD_LIST_TABLE_NAME, null, null, null, null, null, null,null);
        cursor.moveToFirst();


        //String Name = cursor.getString( cursor.getColumnIndex(COL_NAME));
        String Brand = cursor.getString( cursor.getColumnIndex(COL_Brand));
        String Year = cursor.getString( cursor.getColumnIndex(COL_Year));
        String Team = cursor.getString( cursor.getColumnIndex(COL_Team));
        String Description = cursor.getString( cursor.getColumnIndex(COL_Description));
        String Price = cursor.getString( cursor.getColumnIndex(COL_Price));
        double Card_Price = Double.valueOf(Price);


        return new BaseballCard(id, "", Brand, Year, Team, Description, Card_Price);



    }


    public Cursor getSports(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM baseball_cards_table", null);
        if (cur != null) {
            cur.moveToFirst();                       // Always one row returned.
            if (cur.getInt (0) == 0) {               // Zero count means empty table.
                for (int i = 0; i < 11; i++) {
                    Log.i("Hey","displaying data");
                }
            }
        }
        //Cursor mCursor = db.query(BASEBALLCARD_LIST_TABLE_NAME, null, null, null, null, null, null, null);
        return cur;
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String selection = "_id = ?";
        String[] selectionArgs = new String[]{ String.valueOf(id)};
        db.delete(BASEBALLCARD_LIST_TABLE_NAME, selection, selectionArgs);
    }


    public Cursor getSearchResults(String search_string){
        SQLiteDatabase db = this.getReadableDatabase();
//        String WHERE_CLAUSE = COL_NAME + "LIKE ?";
//        String[] WHERE_ARGS = { search_string+"%" };
//        String ORDER_BY = COL_Brand;
//        return db.query(BASEBALLCARD_LIST_TABLE_NAME, CARDS_COLUMNS, WHERE_CLAUSE, WHERE_ARGS, null, null,null, ORDER_BY);
        String[] condition = new String[1];
        condition[0]= search_string + '%';
        return db.rawQuery("select * from baseball_cards_table where Name like ?",condition);



    }

}




