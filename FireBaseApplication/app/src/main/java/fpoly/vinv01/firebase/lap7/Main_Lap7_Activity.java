package fpoly.vinv01.firebase.lap7;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import fpoly.vinv01.firebase.R;

public class Main_Lap7_Activity extends AppCompatActivity {
    private EditText edtTitle, edtComment, edtDate, edtType;
    private Button btnAdd;
    private DatabaseReference mDatabase;
    private ArrayList<ToDoModel> list;
    private Adapter adapter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lap7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("ToDo");
        InitUI();
        adapter = new Adapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loadDataFromFirebase();
        InitEvent();
    }
    private void InitUI() {
        edtTitle = findViewById(R.id.edtTitle);
        edtComment = findViewById(R.id.edtComment);
        edtDate = findViewById(R.id.edtDate);
        edtType = findViewById(R.id.edtType);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.dsToDo);
    }
    private void InitEvent() {
        btnAdd.setOnClickListener(v -> {
            String title = edtTitle.getText().toString();
            String comment = edtComment.getText().toString();
            String date = edtDate.getText().toString();
            String type = edtType.getText().toString();
            String id = UUID.randomUUID().toString();
            ToDoModel toDoModel = new ToDoModel(id, title, comment, date, type, 0);
            DAO_lap7 dao = new DAO_lap7(this);
            dao.insert(toDoModel);
            edtTitle.setText("");
            edtComment.setText("");
            edtDate.setText("");
            edtType.setText("");
        });
        edtType.setOnClickListener(v -> {
            AlertDialog.Builder typeBuilder = new AlertDialog.Builder(this);
            String[] types = {"Dễ", "Trung bình", "Khó"};
            typeBuilder.setTitle("Chọn loại");
            typeBuilder.setItems(types, (dialogInterface, i) -> {
                edtType.setText(types[i]);
            });
            typeBuilder.show();
        });
    }

    private void loadDataFromFirebase() {
        mDatabase.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                list.clear();
                for (com.google.firebase.database.DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ToDoModel toDoModel = snapshot.getValue(ToDoModel.class);
                    if (toDoModel != null) {
                        list.add(toDoModel);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {

            }
        });
    }
}