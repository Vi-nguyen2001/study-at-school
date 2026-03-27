package Assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Assignment.DbHelper.DbAssm;
import Assignment.model.SanPhamAssm;

public class SanPhamDAO {
    private SQLiteDatabase db;
    private DbAssm helper;

    public SanPhamDAO(Context context){
        helper = new DbAssm(context);
        db = helper.getWritableDatabase();
    }

    public long insertSanPham(SanPhamAssm sanPham){
        db = helper.getWritableDatabase();
        long kq = -1;
        try{
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("tenSp",sanPham.getTenSp());
            values.put("giaSp",sanPham.getGiaSp());
            values.put("soLuong",sanPham.getSoLuong());
            kq = db.insert("SanPham",null,values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        return kq;
    }

    public ArrayList<SanPhamAssm> getAllSanPham(){
        ArrayList<SanPhamAssm> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        Cursor cursor = null;
        try{
            db.beginTransaction();
            String sql = "SELECT * FROM SanPham";
            cursor = db.rawQuery(sql,null);
        if (cursor!=null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int maSp = cursor.getInt(0);
                String tenSp = cursor.getString(1);
                double giaSp = cursor.getDouble(2);
                int soLuong = cursor.getInt(3);
                SanPhamAssm sanPham = new SanPhamAssm();
                sanPham.setMaSp(maSp);
                sanPham.setTenSp(tenSp);
                sanPham.setGiaSp(giaSp);
                sanPham.setSoLuong(soLuong);
                list.add(sanPham);
                cursor.moveToNext();
            }
        }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }
        return list;
    }

    public int update(SanPhamAssm sanPham) {
         db = helper.getWritableDatabase();
        int kq = 0;
        try{
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("tenSp",sanPham.getTenSp());
            values.put("giaSp",sanPham.getGiaSp());
            values.put("soLuong",sanPham.getSoLuong());
            kq = db.update("SanPham",values,"maSp=?",new String[]{sanPham.getMaSp()+""});
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
        return kq;
    }

    public int delete(int maSp){
        db = helper.getWritableDatabase();
        int kq =0;
        try{
            db.beginTransaction();
            kq = db.delete("SanPham","maSp=?",new String[]{maSp+""});
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        return kq;
    }
}
