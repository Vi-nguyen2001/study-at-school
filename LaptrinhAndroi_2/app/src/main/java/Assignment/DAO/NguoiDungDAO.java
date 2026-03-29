package Assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Assignment.DbHelper.DbAssm;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DbAssm helper;

    public NguoiDungDAO(Context context){
        helper = new DbAssm(context);
    }


    public Boolean checkLogin(String tenDangNhap, String matKhau){
        db = helper.getReadableDatabase();
        Cursor cursor = null;
        try{
            String sql = "SELECT * FROM NguoiDung WHERE tenDangNhap = ? AND matKhau = ?";
            cursor = db.rawQuery(sql,new String[]{tenDangNhap,matKhau});
            if (cursor.getCount()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }
        return false;
    }

    public long register(String tenDangNhap, String matKhau, String hoTen){
        db = helper.getWritableDatabase();
        long kq = -1;
        try{
            db.beginTransaction();
            ContentValues values = new android.content.ContentValues();
            values.put("tenDangNhap", tenDangNhap);
            values.put("matKhau", matKhau);
            values.put("hoTen", hoTen);
            kq = db.insert("NguoiDung", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        return kq;
    }

    public String getHoTen(String tenDangNhap){
        db = helper.getReadableDatabase();
        Cursor cursor = null;
        String hoTen = "";
        try {
            cursor = db.rawQuery("SELECT hoTen FROM NguoiDung WHERE tenDangNhap = ?", new String[]{tenDangNhap});
            if (cursor.moveToFirst()){
                hoTen = cursor.getString(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return hoTen;
    }

}
