package Lap6.bai4;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import fpoly.vinv01.sqlitedemo.R;

public class bai4_lap6 extends AppCompatActivity {
    private Button btnWorkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai4_lap6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUI();
        InitEvent();
    }
    private void InitUI(){
        btnWorkManager = findViewById(R.id.btnWorkManager);
    }
    private void InitEvent(){
        btnWorkManager.setOnClickListener(v -> {
            Constraints constraints = new Constraints.Builder()
                    .setRequiresCharging(true)
                    .build();
            WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                    .setConstraints(constraints)
                    .build();
            WorkManager.getInstance(this).enqueue(workRequest);
        });
    }
}