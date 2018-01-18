package com.sf.orderfoodserver.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sf.orderfoodserver.R;
import com.sf.orderfoodserver.common.Common;
import com.sf.orderfoodserver.model.User;

import info.hoang8f.widget.FButton;

public class SignInActivity extends AppCompatActivity {

    MaterialEditText edtPhone, edtPassword;
    FButton btnSignIn;
    FirebaseDatabase database;
    DatabaseReference tableUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        btnSignIn = (FButton) findViewById(R.id.btnSignIn);

        database = FirebaseDatabase.getInstance();
        tableUser = database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtPhone.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    private void signInUser(String phone, String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignInActivity.this);
        mDialog.setMessage("Loading...");
        mDialog.show();

        final String localPhone = phone;
        final String localPassword = password;

        tableUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(localPhone).exists()) {

                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);

                    if (Boolean.parseBoolean(user.getIsStaff())) {
                        if (user.getPassword().toString().equals(localPassword)) {
                            Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
                            Common.currentUser = user;
                            startActivity(homeIntent);
                            finish();
                        } else {
                            mDialog.dismiss();
                            Snackbar.make(findViewById(R.id.signInLayout), "Wrong password", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        mDialog.dismiss();
                        Snackbar.make(findViewById(R.id.signInLayout), "isTsaff is false", Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    mDialog.dismiss();
                    Snackbar.make(findViewById(R.id.signInLayout), "User not exist in database", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
