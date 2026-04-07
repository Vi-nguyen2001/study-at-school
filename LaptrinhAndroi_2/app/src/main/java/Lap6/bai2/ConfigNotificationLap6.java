package Lap6.bai2;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.os.Build;

public class ConfigNotificationLap6 extends Application{
    public static final String CHANNEL = "bai2_lap6";

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }
    private void config(){
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            NotificationChannel channel = new NotificationChannel(CHANNEL, "bai2_lap6",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(null, audioAttributes);
            channel.setDescription("bai2_lap6");
            android.app.NotificationManager manager = getSystemService(android.app.NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }


}
