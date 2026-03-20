package lap2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import lap2.DbHelperLap2;
import lap2.model.ToDoLap2;

public class Lap2ToDoDAO {
    private SQLiteDatabase db;
    private DbHelperLap2 helper;
    public Lap2ToDoDAO(Context context){
        helper = new DbHelperLap2(context);
        db = helper.getWritableDatabase();
    }

    public long insertToDo(ToDoLap2 toDoLap2){

        ContentValues values = new ContentValues();
        values.put("title",toDoLap2.getTitle());
        values.put("content",toDoLap2.getContent());
        values.put("date",toDoLap2.getDate());
        values.put("type",toDoLap2.getType());
        values.put("status",toDoLap2.getStatus());
        long kq = db.insert("TODOLap2",null,values);
        return kq;

    }

    public ArrayList<ToDoLap2> getAll() {
        ArrayList<ToDoLap2> list = new ArrayList<>();
        String sql = "SELECT * FROM TODOLap2";
        db.rawQuery(sql, null);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String date = cursor.getString(3);
                String type = cursor.getString(4);
                int status = cursor.getInt(5);
                list.add(new ToDoLap2(id, title, content, date, type, status));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public int updateToDo(ToDoLap2 toDoLap2){
        ContentValues values = new ContentValues();
        values.put("title",toDoLap2.getTitle());
        values.put("content",toDoLap2.getContent());
        values.put("date",toDoLap2.getDate());
        values.put("type",toDoLap2.getType());
        values.put("status",toDoLap2.getStatus());
        int kq = db.update("TODOLap2",values,"id=?",new String[]{toDoLap2.getId()+""});
        return kq;
    }

    public boolean updateStatus(int id, boolean check){
        ContentValues values = new ContentValues();
        values.put("status",check?1:0);
        long kq = db.update("TODOLap2",values,"id=?",new String[]{id+""});
        return kq >0;

    }


    public int deleteToDo(int id){
        int kq = db.delete("TODOLap2","id=?",new String[]{id+""});
        return kq;

    }
}
