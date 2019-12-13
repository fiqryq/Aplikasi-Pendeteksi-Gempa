package com.autumnleaves.pendeteksigempa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private TextView textView;
    private Button delete;
    private ImageView updatedatabase;
//    remove idea
//    remove idea

    String pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
//        delete = findViewById(R.id.hapus);
        updatedatabase = findViewById(R.id.centerImage);

        final NotificationHelper notificationHelper = new NotificationHelper(this);
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.content);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Pesan");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() == true) {
                    pesan = dataSnapshot.getValue().toString();
                    textView.setText(pesan + "");
                    rippleBackground.startRippleAnimation();
                    notificationHelper.notify("Aplikasi Gempa", "Peesan" + pesan);
                } else {
                    textView.setText("Aman");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updatedatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue();
                rippleBackground.stopRippleAnimation();
            }
        });
//
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                databaseReference.removeValue();
//                rippleBackground.stopRippleAnimation();
//            }
//        });
    }
}
