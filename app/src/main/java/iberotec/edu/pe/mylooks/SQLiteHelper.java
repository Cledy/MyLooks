package iberotec.edu.pe.mylooks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Margot on 16/12/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

        private static String DB_NAME= "ParteArribaDB.sqlite";





    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, String tipo, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO P VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, tipo);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
