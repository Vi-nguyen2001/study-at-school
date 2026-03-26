package lap1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import fpoly.vinv01.sqlitedemo.R;

public class lap1MainScreen extends AppCompatActivity {
    ToDo toDoSelect = null;
    private Button btnThem;
    private Button btnUpdate;
    private Button btnXoa;

    private EditText edtTitle;
    private EditText edtContent;
    private EditText edtDate;
    private EditText edtType;

    private ToDoDAO dao;
    private ToDoAdapter adapter;
    private ArrayList<ToDo> list;
    private ListView listView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lap1_main_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        init();
        dao = new ToDoDAO(this);
        list = dao.getAll();
        adapter = new ToDoAdapter(this, list);
        listView.setAdapter(adapter);
        initEvent();
    }

    private void initEvent() {
        btnThem.setOnClickListener(v -> {
            String title = edtTitle.getText().toString();
            String content = edtContent.getText().toString();
            String date = edtDate.getText().toString();
            String type = edtType.getText().toString();
            ToDo todo = new ToDo();
            todo.setTitle(title);
            todo.setContent(content);
            todo.setDate(date);
            todo.setType(type);
            long kq = dao.insertToDo(todo);
            if (kq > 0) {
                todo.setId((int) kq);
                list.add(todo);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                edtTitle.setText("");
                edtContent.setText("");
                edtDate.setText("");
                edtType.setText("");
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();

            }
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            toDoSelect = list.get(position);
            edtTitle.setText(toDoSelect.getTitle());
            edtContent.setText(toDoSelect.getContent());
            edtDate.setText(toDoSelect.getDate());

        });

        btnUpdate.setOnClickListener(v -> {
            if (toDoSelect != null) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();
                String date = edtDate.getText().toString();
                String type = edtType.getText().toString();
                toDoSelect.setTitle(title);
                toDoSelect.setContent(content);
                toDoSelect.setDate(date);
                toDoSelect.setType(type);
                int kq = dao.updateToDo(toDoSelect);
                if (kq > 0) {
                    list.set(list.indexOf(toDoSelect), toDoSelect);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    toDoSelect = null;
                    edtTitle.setText("");
                    edtContent.setText("");
                    edtDate.setText("");
                    edtType.setText("");
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

            }

        });

        btnXoa.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa?");
            builder.setPositiveButton("Có", (dialog, which) -> {
                int kq = dao.deleteToDo(toDoSelect.getId());
                if (kq > 0) {
                    list.remove(toDoSelect);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    toDoSelect = null;
                    edtTitle.setText("");
                    edtContent.setText("");
                    edtDate.setText("");
                    edtType.setText("");
                } else {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }

            });
            builder.setNegativeButton("Không", null);
            builder.show();
        });

    }
    public void init() {
        btnThem = findViewById(R.id.lap1them);
        btnUpdate = findViewById(R.id.lap1update);
        btnXoa = findViewById(R.id.lap1xoa);
        edtTitle = findViewById(R.id.edt_title);
        edtContent = findViewById(R.id.edt_content);
        edtDate = findViewById(R.id.edt_date);
        edtType = findViewById(R.id.edt_type);
        listView = findViewById(R.id.lv_todo);
    }

}