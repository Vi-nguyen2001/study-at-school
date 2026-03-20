package fpoly.vinv01.sqlitedemo;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.vinv01.sqlitedemo.addapter.AdapterStudent;
import fpoly.vinv01.sqlitedemo.addapter.RecyleviewAdappterStd;
import fpoly.vinv01.sqlitedemo.dao.StudentDAO;
import fpoly.vinv01.sqlitedemo.model.Student;

public class ListStudentScreen extends AppCompatActivity {
    //private ListView list_student;
    private ArrayList<Student> list;
    //private AdapterStudent adapter;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private StudentDAO dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_student_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUI();
        dao = new StudentDAO(this);
        list = dao.getAllStudent();
        //adapter = new AdapterStudent(this,list);
        //list_student.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyleviewAdappterStd(this,list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void InitUI() {
        //list_student = findViewById(R.id.list_student);
        recyclerView = findViewById(R.id.list_student);

    }
}