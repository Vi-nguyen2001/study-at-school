package Lap6.bai3;


import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import android.os.Handler;

public class BackGroundServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service bắt đầu chạy", Toast.LENGTH_SHORT).show();
        try{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BackGroundServices.this, "Đang chuyển trang web", Toast.LENGTH_SHORT).show();
                    Intent webIntent = new Intent(Intent.ACTION_VIEW);
                    webIntent.setData(Uri.parse("https://ap.poly.edu.vn/login"));
                    webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(webIntent);
                    stopSelf();
                }
            }, 5000);
        }catch (Exception e){
            Toast.makeText(this, "Lỗi chuyển trang web", Toast.LENGTH_LONG).show();
            stopSelf();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service đã dừng", Toast.LENGTH_SHORT).show();
    }

}
