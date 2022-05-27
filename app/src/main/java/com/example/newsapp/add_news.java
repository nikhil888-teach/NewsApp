package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_news extends AppCompatActivity {

    AdView adView;
    AdRequest adRequest;
    Button btnUpload;
    EditText edturl, edtTitle, edtlink;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        adView = findViewById(R.id.adview);
        btnUpload = findViewById(R.id.uploadnews);
        edtlink = findViewById(R.id.edtlink);
        edturl = findViewById(R.id.edturl);
        edtTitle = findViewById(R.id.edtTitle);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        dialog=new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.setMessage("Upload News");


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();
                databaseReference.child("News");
                User user = new User(edturl.getText().toString(), edtTitle.getText().toString(), edtlink.getText().toString());
                databaseReference.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(add_news.this, "Upload News", Toast.LENGTH_SHORT).show();
                        edtlink.setText("");
                        edturl.setText("");
                        edtTitle.setText("");
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Toast.makeText(add_news.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(add_news.this,MainActivity.class));
        finish();
        super.onBackPressed();
    }
}