package Lap6.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.vinv01.sqlitedemo.R;

public class bai2_lap6 extends AppCompatActivity {
    private Button btnStart, btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2_lap6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUI();
        InitEvent();

    }
    private void InitUI(){
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
    }

    private void InitEvent(){
        btnStart.setOnClickListener(v -> {
            startService(new Intent(this, ForegroundService.class));
        });
        btnEnd.setOnClickListener(v -> {
            stopService(new Intent(this, ForegroundService.class));
        });
    }
}