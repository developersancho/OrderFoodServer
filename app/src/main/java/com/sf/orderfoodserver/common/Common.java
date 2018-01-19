package com.sf.orderfoodserver.common;


import com.sf.orderfoodserver.model.User;

/**
 * Created by mesutgenc on 8.01.2018.
 */

public class Common {
    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final int PICK_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }

}
