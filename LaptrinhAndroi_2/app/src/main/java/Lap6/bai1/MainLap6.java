package Lap6.bai1;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

import fpoly.vinv01.sqlitedemo.R;

public class MainLap6 extends AppCompatActivity {
    private Button btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lap6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitUI();
        InitEvent();
    }
    private void InitUI(){
        btnNotification = findViewById(R.id.btnNotification);
    }
    private void InitEvent(){
        btnNotification.setOnClickListener(v -> {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_2);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ConfigNotification.CHANNEL_ID);
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder.setContentTitle("Thông báo");
            builder.setContentText("Nội dung thông báo");
            builder.setLargeIcon(bitmap);
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
                manager.notify((int)new Date().getTime(), builder.build());
            }else{
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 7979);
            }
        });
    }
}