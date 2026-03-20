package lap2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperLap2 extends SQLiteOpenHelper {
    public DbHelperLap2(Context context) {
        super(context, "todolap.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TODOLap2 (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, date TEXT, type TEXT, status INTEGER)";
        db.execSQL(sql);

        String data = "INSERT INTO TODOLap2 (title, content, date, type, status) VALUES " +
                "('Học java', 'Học java cơ bản', '17/04/2026', 'Dễ', 1),"+
                "('Học java2', 'Học java nâng cao', '17/04/2026', 'Dễ', 2),";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS TODOLap2");
            onCreate(db);


    }
}
}
