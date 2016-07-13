package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 Created by andeski on 7/12/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SHOPPING_DB.db";
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_TABLE = "shopping_list";

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "ITEM_NAME";
    public static final String COL_DESC = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    public static final String[] COLUMN_HEADERS = {COL_ID, COL_NAME, COL_DESC, COL_PRICE, COL_TYPE};

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String createString = " CREATE TABLE " + DATABASE_TABLE + " ( "
            + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_NAME + " TEXT, "
            + COL_DESC + " TEXT, "
            + COL_PRICE + " TEXT, "
            + COL_TYPE + " TEXT ) ";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // if the table exists drop it and remake the table with the new information
        // else just make the table
        this.onCreate(db);
    }

    private static DatabaseHelper instance;
    // since they are both objects we can use the object instance as the mediator variable???????
    public static DatabaseHelper getInstance(Context context){
        if (instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    public Cursor getItemList() {
        // the SQLiteDatabase is an object from the class SQListOpenHelper
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, COLUMN_HEADERS, null, null, null, null, null, null);
        return cursor;
    }

    public void add_values_to_table(String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,name);
        db.insert(DATABASE_TABLE,null,cv);
//        db.close();
    }

}


