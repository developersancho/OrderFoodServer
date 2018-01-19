package com.sf.orderfoodserver.helper;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.sf.orderfoodserver.R;
import com.sf.orderfoodserver.common.Common;

/**
 * Created by mesutgenc on 19.01.2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnCreateContextMenuListener {

    public TextView order_id, order_status, order_phone, order_address;

    ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        order_id = (TextView) itemView.findViewById(R.id.order_id);
        order_status = (TextView) itemView.findViewById(R.id.order_status);
        order_phone = (TextView) itemView.findViewById(R.id.order_phone);
        order_address = (TextView) itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select The Action");

        menu.add(0, 0, getAdapterPosition(), Common.UPDATE);
        menu.add(0, 1, getAdapterPosition(), Common.DELETE);
    }
}
