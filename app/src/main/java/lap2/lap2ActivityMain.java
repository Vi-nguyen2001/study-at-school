package lap2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.vinv01.sqlitedemo.R;
import lap2.dao.Lap2ToDoDAO;
import lap2.model.ToDoLap2;

public class lap2ActivityMain extends AppCompatActivity {
    private EditText edtTieuDe;
    private EditText edtMonHoc;
    private EditText edtNgay;
    private EditText edtMucDo;
    private RecyclerView dsToDo;
    private Button btnThem;

    private Lap2ToDoDAO dao;
    private Lap2ToDoAdapter adapter;
    private ArrayList<ToDoLap2> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lap2_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        dao = new Lap2ToDoDAO(this);
        list = dao.getAll();
        adapter = new Lap2ToDoAdapter(this, list);
        dsToDo.setAdapter(adapter);
        initEvent();
    }

    private void initEvent() {
        btnThem.setOnClickListener(v -> {
            String tieuDe = edtTieuDe.getText().toString();
            String monHoc = edtMonHoc.getText().toString();
            String ngay = edtNgay.getText().toString();
            String mucDo = edtMucDo.getText().toString();
            ToDoLap2 toDoLap2 = new ToDoLap2();
            toDoLap2.setTitle(tieuDe);
            toDoLap2.setContent(monHoc);
            toDoLap2.setDate(ngay);
            toDoLap2.setType(mucDo);

            long kq = dao.insertToDo(toDoLap2);
            if (kq > 0) {
                toDoLap2.setId((int) kq);
                list.add(toDoLap2);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                edtTieuDe.setText("");
                edtMonHoc.setText("");
                edtNgay.setText("");
                edtMucDo.setText("");
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        edtMucDo.setOnClickListener(v -> {
            String[] mucDo = {"Dễ", "Trung Bình", "Khó"};
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("Chọn mức độ");
            builder.setItems(mucDo, (dialog, which) -> {
                edtMucDo.setText(mucDo[which]);
            });
            builder.show();
        });


    }

    private void init() {
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtMonHoc = findViewById(R.id.edtMonHoc);
        edtNgay = findViewById(R.id.edtNgay);
        edtMucDo = findViewById(R.id.edtMucDo);
        dsToDo = findViewById(R.id.dsToDo);
        btnThem = findViewById(R.id.btnThem);
        dsToDo.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
    }
}