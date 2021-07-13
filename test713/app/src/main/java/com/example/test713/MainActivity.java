package com.example.test713;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"Main activity onCreate: ") ;

        final Button bt = findViewById(R.id.tryyyy);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, Try.class) ;
                startActivity(intent);
            }
        });
        final Button baidu = findViewById(R.id.baidu) ;
        baidu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW) ;
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        final Button to = findViewById(R.id.toast) ;
        to.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Button Toast" , Toast.LENGTH_SHORT).show();
            }
        });
        final Button bo = findViewById(R.id.phone) ;
        bo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL_BUTTON) ;
                startActivity(intent);
            }
        });

        final Button pag = findViewById(R.id.page) ;
        pag.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Page.class);
                startActivity(intent);
            }
        });

        final Button rey = findViewById(R.id.list) ;
        rey.setOnClickListener(new View.OnClickListener(){
            public void onClick(View c) {
                Intent intent = new Intent( MainActivity.this,RecyclerViewActivity.class) ;
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Main activity onStart: ");
    }
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Main activity onResume: ") ;
    }
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"Main activity onPause: ");
    }
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Main activity onStop: ");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "Main activity onDestroy: ");
    }

}
