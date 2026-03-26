package lap1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "todo.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TODO (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, date TEXT, type TEXT, status INTEGER)";
        db.execSQL(sql);

        String data = "INSERT INTO TODO (title, content, date, type, status) VALUES " +
                "('Học java', 'Học java cơ bản', '17/04/2001', 'Dễ', 1)," +
                "('Học Android', 'Học android cơ bản ', '17/04/2001', 'Dễ', 0)";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS TODO");
            onCreate(db);
        }

    }
}
