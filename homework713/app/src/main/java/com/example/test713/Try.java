package com.example.test713;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Try extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);
        final Button butapp = findViewById(R.id.trybutton1) ;
        final Button butdis = findViewById(R.id.trybutton2) ;
        final ImageView img = findViewById(R.id.tryimg) ;
        final Button exit = findViewById(R.id.exit) ;
        butapp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                img.setImageResource(R.drawable.helene_rolles);
            }
        });

        butdis.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                img.setImageResource(R.drawable.hnulls);
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v) {
                Intent intent = new Intent( Try.this,MainActivity.class) ;
                startActivity(intent);
            }
        });

    }

}
