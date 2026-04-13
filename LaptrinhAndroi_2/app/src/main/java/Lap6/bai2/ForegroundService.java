package Lap6.bai2;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import fpoly.vinv01.sqlitedemo.R;

public class ForegroundService extends Service {
    public MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service đã được tạo", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service đang chạy", Toast.LENGTH_SHORT).show();
        Notification notification = new NotificationCompat.Builder(this, ConfigNotificationLap6.CHANNEL)
                .setContentTitle("Foreground Service")
                .setContentText("Đang chạy nền")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();
        startForeground(1, notification);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        } else {
            try {
                mediaPlayer = MediaPlayer.create(this, R.raw.music);
                if (mediaPlayer != null) {
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Tạm dừng", Toast.LENGTH_SHORT).show();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}