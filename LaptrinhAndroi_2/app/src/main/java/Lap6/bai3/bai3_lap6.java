package Lap6.bai3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import Lap6.bai4.MyWorker;
import fpoly.vinv01.sqlitedemo.R;

public class bai3_lap6 extends AppCompatActivity {
    private Button btnBackGroundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai3_lap6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUI();
        InitEvent();
    }
    private void InitUI(){
        btnBackGroundService = findViewById(R.id.btnBackGroundService);
    }
    private void InitEvent(){
        btnBackGroundService.setOnClickListener(v -> {
            startService(new Intent(this, BackGroundServices.class));
        });
    }

}