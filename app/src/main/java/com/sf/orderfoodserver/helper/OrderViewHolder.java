package com.sf.orderfoodserver.helper;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.sf.orderfoodserver.R;
import com.sf.orderfoodserver.common.Common;

import info.hoang8f.widget.FButton;

/**
 * Created by mesutgenc on 19.01.2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView order_id, order_status, order_phone, order_address;
    public FButton btnEdit, btnDetail, btnDirection, btnRemove;

    public OrderViewHolder(View itemView) {
        super(itemView);

        order_id = (TextView) itemView.findViewById(R.id.order_id);
        order_status = (TextView) itemView.findViewById(R.id.order_status);
        order_phone = (TextView) itemView.findViewById(R.id.order_phone);
        order_address = (TextView) itemView.findViewById(R.id.order_address);

        btnEdit = (FButton) itemView.findViewById(R.id.btnEdit);
        btnDetail = (FButton) itemView.findViewById(R.id.btnDetail);
        btnDirection = (FButton) itemView.findViewById(R.id.btnDirection);
        btnRemove = (FButton) itemView.findViewById(R.id.btnRemove);
    }

}
