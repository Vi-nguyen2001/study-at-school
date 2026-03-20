package lap1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ToDoDAO {
    public DbHelper dbHelper;

    public ToDoDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<ToDo> getAll() {
        ArrayList<ToDo> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            db.beginTransaction(); // Bắt đầu
            cursor = db.rawQuery("SELECT * FROM TODO", null);

            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String content = cursor.getString(2);
                    String date = cursor.getString(3);
                    String type = cursor.getString(4);
                    int status = cursor.getInt(5);

                    list.add(new ToDo(id, title, content, date, type, status));

                    cursor.moveToNext(); // PHẢI CÓ DÒNG NÀY để không bị treo máy
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.endTransaction(); // PHẢI CÓ DÒNG NÀY để mở khóa Database
        }
        return list;
    }

    public long insertToDo(ToDo todo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long kq = -1;
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("title", todo.getTitle());
            values.put("content", todo.getContent());
            values.put("date", todo.getDate());
            values.put("type", todo.getType());
            values.put("status", todo.getStatus()); // Đảm bảo có dòng này

            kq = db.insert("TODO", null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); // PHẢI CÓ DÒNG NÀY
        }
        return kq;
    }

    public int updateToDo(ToDo todo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int kq = 0;
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("title", todo.getTitle());
            values.put("content", todo.getContent());
            values.put("date", todo.getDate());
            values.put("type", todo.getType());
            values.put("status", todo.getStatus()); // Đảm bảo có dòng này
            kq = db.update("TODO", values, "id=?", new String[]{todo.getId()+""});
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

        return kq;
    }

    public int deleteToDo(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int kq = 0;
        try {
            db.beginTransaction();
            kq = db.delete("TODO", "id=?", new String[]{id + ""});
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        return kq;
    }

}
