package com.sf.orderfoodserver.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sf.orderfoodserver.R;
import com.sf.orderfoodserver.common.Common;
import com.sf.orderfoodserver.helper.ItemClickListener;
import com.sf.orderfoodserver.helper.OrderViewHolder;
import com.sf.orderfoodserver.model.Request;

public class OrderStatusActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase db;
    DatabaseReference requests;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        db = FirebaseDatabase.getInstance();
        requests = db.getReference("Requests");

        recyclerView = (RecyclerView) findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();

    }

    private void loadOrders() {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_item,
                OrderViewHolder.class,
                requests) {

            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.order_id.setText(adapter.getRef(position).getKey());
                viewHolder.order_status.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.order_phone.setText(model.getPhone());
                viewHolder.order_address.setText(model.getAddress());

                final Request clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(OrderStatusActivity.this, "" + clickItem.getName() + "-" + clickItem.getPhone(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getIntent().equals(Common.UPDATE)) {
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));
        } else if (item.getIntent().equals(Common.DELETE)) {
            deleteOrder(adapter.getRef(item.getOrder()).getKey());
        }

        return super.onContextItemSelected(item);
    }

    private void deleteOrder(String key) {

    }

    private void showUpdateDialog(String key, Request item) {

    }
}
