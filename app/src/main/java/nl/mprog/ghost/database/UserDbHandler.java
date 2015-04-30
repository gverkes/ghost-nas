package nl.mprog.ghost.database;
/**
 * Created by Govert on 4/22/15.
 * Db Handler
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import nl.mprog.ghost.models.User;

public class UserDbHandler extends SQLiteOpenHelper {
    /* If you change the database schema, you must increment the database version. */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Ghost.db";

    /* Define User table name and column entries that define the table contents */
    public static final String TABLE_NAME = "users";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_SCORE = "score";
    public static final String KEY_NO_OF_GAMES = "no_of_games";

    public UserDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_USERNAME + " TEXT," +
                KEY_SCORE + " INTEGER," +
                KEY_NO_OF_GAMES + " INTEGER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int insert(User user) {

        // Create reference to writable database
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getName());
        values.put(KEY_SCORE, user.getScore());
        values.put(KEY_NO_OF_GAMES, user.getNumberOfGames());

        // Insert user model
        long userId = db.insert(TABLE_NAME, null, values);
        db.close();
        return (int) userId;
    }

    public void delete(int userId) {
        SQLiteDatabase db = getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TABLE_NAME, KEY_ID + "= ?", new String[] { String.valueOf(userId) });
        db.close(); // Closing database connection
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void update(User user) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, user.getName());
        values.put(KEY_SCORE, user.getScore());
        values.put(KEY_NO_OF_GAMES, user.getNumberOfGames());

        // Update user model
        db.update(TABLE_NAME, values, KEY_ID + "= ?", new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getUserList() {
        //Open connection to read only
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery =  "SELECT  " +
                KEY_ID + "," +
                KEY_USERNAME + "," +
                KEY_SCORE + "," +
                KEY_NO_OF_GAMES +
                " FROM " + TABLE_NAME +
                " ORDER BY " + KEY_SCORE + " DESC ";

        //Student student = new Student();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<>();
                user.put("name", cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                user.put("score", cursor.getString(cursor.getColumnIndex(KEY_SCORE)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;

    }

    public User getUserByName(String username){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery =  "SELECT  " +
                KEY_ID + "," +
                KEY_USERNAME + "," +
                KEY_SCORE + "," +
                KEY_NO_OF_GAMES +
                " FROM " + TABLE_NAME +
                " WHERE " + KEY_USERNAME + "=?";

        int iCount =0;
        User user = null;

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(username) } );

        if (cursor.moveToFirst()) {
            do {
                user = new User(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_USERNAME)),
                        cursor.getInt(cursor.getColumnIndex(KEY_SCORE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_NO_OF_GAMES)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }

    public User getUserById(int Id){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery =  "SELECT  " +
                KEY_ID + "," +
                KEY_USERNAME + "," +
                KEY_SCORE + "," +
                KEY_NO_OF_GAMES +
                " FROM " + TABLE_NAME +
                " WHERE " + KEY_ID + "=?";

        int iCount =0;
        User user = null;

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                user = new User(cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_USERNAME)),
                        cursor.getInt(cursor.getColumnIndex(KEY_SCORE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_NO_OF_GAMES)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }
}
