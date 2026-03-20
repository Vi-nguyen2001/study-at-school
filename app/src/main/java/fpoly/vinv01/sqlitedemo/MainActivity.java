package fpoly.vinv01.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.vinv01.sqlitedemo.dao.StudentDAO;
import fpoly.vinv01.sqlitedemo.model.Student;

public class MainActivity extends AppCompatActivity {
    private Button btnthem;
    private Button btnshow;

    private EditText edt_name;
    private EditText edt_diachi;
    private EditText edt_avata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //B1: Khởi tạo Model : Student.java : id,name,address
        //B2: Database Helper CRUD :
        //B3: Test thử
        //B4: Gắn giao diện
        initUI();
        initEvent();


    }
    private void initUI(){
        btnthem = findViewById(R.id.btnthem);
        btnshow = findViewById(R.id.btnShow);
        edt_name = findViewById(R.id.edt_name);
        edt_diachi = findViewById(R.id.edt_diachi);
        edt_avata = findViewById(R.id.edt_avata);
    }

    private void initEvent(){
        btnthem.setOnClickListener(v -> {
            String name = edt_name.getText().toString();
            String address = edt_diachi.getText().toString();
            String avata = edt_avata.getText().toString();
            Student student = new Student();
            student.setName(name);
            student.setAddress(address);
            student.setAvata(avata);
            StudentDAO dao = new StudentDAO(this);
            long kq = dao.insertStudent(student);
            if (kq > 0){
                edt_name.setText("");
                edt_diachi.setText("");
                edt_avata.setText("");
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();

            }
        });

        btnshow.setOnClickListener(v -> {
            Intent intent = new Intent(this,ListStudentScreen.class);
            startActivity(intent);
        });

    }
}