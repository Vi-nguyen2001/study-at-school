package fpoly.vinv01.firebase.lap7;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;



public class DAO_lap7 {
    private Context context;
    private DatabaseReference mDatabase;

    public DAO_lap7(Context context) {
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference("ToDo");
    }

    public void insert(ToDoModel toDoModel) {
        HashMap<String, Object> hashMap = toDoModel.coverHashMap();
        mDatabase.child(toDoModel.getId()).setValue(hashMap)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                          Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                      });
    }
    public void update(ToDoModel toDoModel) {
        HashMap<String, Object> hashMap = toDoModel.coverHashMap();
        mDatabase.child(toDoModel.getId()).setValue(hashMap)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
    public void delete(String id) {
        mDatabase.child(id).removeValue()
                .addOnSuccessListener(unused -> {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}
