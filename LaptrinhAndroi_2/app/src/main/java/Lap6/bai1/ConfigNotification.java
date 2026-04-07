package Lap6.bai1;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;

public class ConfigNotification extends Application {
    public static final String CHANNEL_ID = "lap_6";

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
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "lap_6",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), audioAttributes);
                channel.setDescription("lap_6");
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
        }

}
