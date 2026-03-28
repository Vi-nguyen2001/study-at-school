package Assignment.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbAssm extends SQLiteOpenHelper {

    public DbAssm(Context context) {
        super(context, "DbAssm", null, 2);
    }

    //Tạo bảng NguoiDung (tenDangNhap, matKhau, hoTen)

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlNguoiDung = "CREATE TABLE NguoiDung (" +
                "tenDangNhap TEXT PRIMARY KEY," +
                "matKhau TEXT," +
                "hoTen TEXT)";
        db.execSQL(sqlNguoiDung);

        String sqlSanPham = "CREATE TABLE SanPham (" +
                "maSp INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSp TEXT," +
                "giaSp REAL," +
                "soLuong INTEGER)";
        db.execSQL(sqlSanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
