package fpoly.vinv01.sqlitedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinv01.sqlitedemo.model.Student;

public class StudentDBHelper extends SQLiteOpenHelper {
    public StudentDBHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//hàm này dùng để tạo bảng
        //hàm này chỉ chạy khi chưa có database
        String student_table = "CREATE TABLE Students (id INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", name NVARCHAR(50), " +
                "address NVARCHAR(100)" +
                ",avata TEXT)";
        db.execSQL(student_table);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //hàm này chỉ chạy khi version_DB  thay đổi
        //hàm này chỉ chạy để cập nhật bảng

    }
}
