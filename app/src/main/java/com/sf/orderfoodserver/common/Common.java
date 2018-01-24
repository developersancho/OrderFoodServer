package com.sf.orderfoodserver.common;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.sf.orderfoodserver.model.Request;
import com.sf.orderfoodserver.model.User;
import com.sf.orderfoodserver.remote.APIService;
import com.sf.orderfoodserver.remote.FCMRetrofitClient;
import com.sf.orderfoodserver.remote.IGeoCoordinates;
import com.sf.orderfoodserver.remote.RetrofitClient;

/**
 * Created by mesutgenc on 8.01.2018.
 */

public class Common {
    public static User currentUser;
    public static Request currentRequest;

    private static final String fcmURL = "https://fcm.googleapis.com/";
    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final int PICK_IMAGE_REQUEST = 71;

    public static final String baseUrl = "https://maps.googleapis.com";

    public static IGeoCoordinates getGeoCodeServices() {
        return RetrofitClient.getClient(baseUrl).create(IGeoCoordinates.class);
    }

    public static APIService getFCMService() {
        return FCMRetrofitClient.getClient(fcmURL).create(APIService.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0, pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    public static String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }

}
