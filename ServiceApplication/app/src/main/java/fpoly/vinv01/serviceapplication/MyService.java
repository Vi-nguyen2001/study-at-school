package fpoly.vinv01.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Giả sử bạn có file nhạc tên là 'music' trong thư mục res/raw
        // mediaPlayer = MediaPlayer.create(this, R.raw.music);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        
        // Nếu đã khởi tạo mediaPlayer thì phát nhạc
        if (mediaPlayer != null) {
            mediaPlayer.start();
        } else {
            // Thử khởi tạo nếu chưa có (cần file R.raw.music)
            try {
                mediaPlayer = MediaPlayer.create(this, R.raw.music);
                if (mediaPlayer != null) {
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Vui lòng thêm file music.mp3 vào res/raw", Toast.LENGTH_LONG).show();
            }
        }
        
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
