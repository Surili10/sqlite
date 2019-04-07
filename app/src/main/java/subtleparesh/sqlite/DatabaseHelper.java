package subtleparesh.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "Surili";
    private static final int DB_VERSION = 1;

    private static final  String TABLE_NAME = "user";
    private static final  String  USER_ID = "id";
    private static final  String  USER_NAME = "name";
    private static final  String  USER_ADDRESS = "address";
    private static final  String  USER_PHONE_NUMBER = "phone_number";


    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "create table "+TABLE_NAME + " ( "+USER_ID + " integer primary key autoincrement, "+USER_NAME + " text, "+USER_ADDRESS + " text, "
                +USER_PHONE_NUMBER + " text )";
        db.execSQL(query);
    }

    public boolean addUser(User user)
    {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_ADDRESS, user.getAddress());
        values.put(USER_PHONE_NUMBER, user.getPhoneNumber());

        long number = database.insert(TABLE_NAME, null, values);
        database.close();
        if(number > 0)
        {
            return true;
        }
            return false;

    }

    public  List<User> getAllUser()
    {
        List<User> users = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String query = "select * from "+TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(USER_ID));

            String name = cursor.getString(cursor.getColumnIndex(USER_NAME));
            String address = cursor.getString(cursor.getColumnIndex(USER_ADDRESS));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(USER_PHONE_NUMBER));

            User user =  new User(id, name , address, phoneNumber);
            users.add(user);
        }

        cursor.close();
        database.close();
        return users;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
