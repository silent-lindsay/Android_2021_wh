package com.bytedance.practice5;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.TabStopSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bytedance.practice5.model.Message;
import com.bytedance.practice5.model.MessageListResponse;
import com.bytedance.practice5.socket.SocketActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.bytedance.practice5.Constants.BASE_URL;
import static com.bytedance.practice5.Constants.STUDENT_ID;
import static com.bytedance.practice5.Constants.token;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "chapter5";
    private FeedAdapter adapter = new FeedAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_mine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(Constants.STUDENT_ID);
            }
        });

        findViewById(R.id.btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(null);
            }
        });
        findViewById(R.id.btn_socket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SocketActivity.class);
                startActivity(intent);
            }
        });



    }

    //TODO 2
    // 用HttpUrlConnection实现获取留言列表数据，用Gson解析数据，更新UI（调用adapter.setData()方法）
    // 注意网络请求和UI更新分别应该放在哪个线程中
    private void getData(String studentId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MessageListResponse rets = BaseGetMessage(studentId) ;
                if ( rets != null ) {
                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setData(rets.feeds);
                        }
                    });
                }

            }
        }).start();
    }

    public MessageListResponse BaseGetMessage(String studentId){
        MessageListResponse ret = null;
//        String urlStr = (studentId == null ) ? BASE_URL+"messages":BASE_URL+"messages/"+studentId ;
        String urlStr = BASE_URL+"messages" ;
        if( studentId != null )
            urlStr = String.format("%smessages?student_id=%s",BASE_URL,studentId);
//        String urlStr = String.format("%smessages/%s?studentId=%s",BASE_URL,STUDENT_ID,STUDENT_ID);
        try{
            URL url = new URL(urlStr);
            HttpURLConnection htconn = (HttpURLConnection)url.openConnection() ;
            htconn.setRequestMethod("GET");
            htconn.setConnectTimeout(6000);
            htconn.setRequestProperty("accept",token);
            if ( htconn.getResponseCode() == 200 ){
                InputStream in = htconn.getInputStream();
                BufferedReader out = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                ret = new Gson().fromJson(out,new TypeToken<MessageListResponse>(){}.getType());
                in.close();
                out.close();
            }
            htconn.disconnect();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Request Failed",Toast.LENGTH_SHORT).show(); ;
        }
//        Log.i(TAG, "BaseGetMessage: Now we search mine!");
//        if( studentId != null ) {
//            MessageListResponse newret = null;
//            for ( int i= 0 ; i < ret.feeds.size();i++ ){
//                Log.i(TAG, "BaseGetMessage: "+ret.feeds.get(i).getStudentId());
//                if( ret.feeds.get(i).getStudentId() == studentId ) newret.feeds.add(ret.feeds.get(i));
//            }
//            return  newret;
//        }
//        else
            return ret;
    }

}