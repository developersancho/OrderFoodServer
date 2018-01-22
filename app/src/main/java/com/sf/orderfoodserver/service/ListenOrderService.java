package com.sf.orderfoodserver.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sf.orderfoodserver.R;
import com.sf.orderfoodserver.activity.OrderStatusActivity;
import com.sf.orderfoodserver.model.Request;

import java.util.Random;

public class ListenOrderService extends Service implements ChildEventListener {

    FirebaseDatabase db;
    DatabaseReference orders;

    public ListenOrderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseDatabase.getInstance();
        orders = db.getReference("Requests");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        orders.addChildEventListener(this);

        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification(String key, Request request) {
        Intent intent = new Intent(getBaseContext(), OrderStatusActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("FOOD")
                .setContentInfo("New Order")
                .setContentText("You have new order #" + key)
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        // if you want to many notification show, you need give unique Id for each Notification
        int randomInt = new Random().nextInt(9999 - 1) + 1;
        notificationManager.notify(randomInt, builder.build());
        //notificationManager.notify(1, builder.build());

    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        // Trigger
        Request request = dataSnapshot.getValue(Request.class);
        if (request.getStatus().equals("0"))
            showNotification(dataSnapshot.getKey(), request);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
