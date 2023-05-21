package com.example.kwesicommerce.utils;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.kwesicommerce.R;

public class NotificationUtil {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "channel_id";
    private static final String CHANNEL_NAME = String.valueOf(R.string.channel_name);
    private final Context context;

    public NotificationUtil(Context context) {
        this.context = context;
    }

    public void showNotification(String title, String notification) {
        if (ActivityCompat.checkSelfPermission(context, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted yet. Request it.
            ActivityCompat.requestPermissions((Activity) context, new String[]{POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
        } else {
            // Permission has already been granted. Show the notification.
            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(title).setContentText(notification)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showToast(String message, boolean type) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layout_toast_message, null);
        LinearLayout linearLayoutToastMessage = layout.findViewById(R.id.linearLayoutToastMessage);
        if (type) {
            linearLayoutToastMessage.setBackgroundTintList(context.getColorStateList(R.color.green));
        } else {
            linearLayoutToastMessage.setBackgroundTintList(context.getColorStateList(R.color.red));
        }

        TextView text = layout.findViewById(R.id.txtViewToastMessage);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.alegreya_sans);
        text.setTypeface(customFont);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
