package fpoly.vinv01.sqlitedemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinv01.sqlitedemo.database.StudentDBHelper;
import fpoly.vinv01.sqlitedemo.model.Student;

public class StudentDAO {
    private SQLiteDatabase db;
    private StudentDBHelper helper;
    public StudentDAO(Context context){
        helper = new StudentDBHelper(context);
        db = helper.getWritableDatabase();//cấp quyền đọc và ghi
    }

    //insert
    public long insertStudent(Student student){
        //bước 1 ghép giá trị với tên cột tương ứng
        ContentValues values = new ContentValues();
        values.put("id",student.getId());
        values.put("name",student.getName());
        values.put("address",student.getAddress());
        values.put("avata",student.getAvata());
        //bước 2 gọi câu lệnh insert
        long kq = db.insert("Students",null,values);
        return kq;

    }
    public ArrayList<Student> getAllStudent(){
        String sql = "SELECT * FROM Students";
        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Student> list = new ArrayList<>();
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String avata = cursor.getString(3);
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAddress(address);
                student.setAvata(avata);
                list.add(student);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public int update(Student student) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int kq = 0;
        try{
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("name",student.getName());
            values.put("address",student.getAddress());
            values.put("avata",student.getAvata());
            kq = db.update("Students",values,"id=?",new String[]{student.getId()+""});
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }





        return kq;
    }

    public boolean delete(String id){
        long kq = db.delete("Students","id=?",new String[]{id});
        if (kq>0){
            return true;
        }
        return false;
    }

}








