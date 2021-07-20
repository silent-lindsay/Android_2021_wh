package com.example.mediaworksdemo;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoDetailActivity extends AppCompatActivity {


    String mockUrl = "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        Button button = findViewById(R.id.vv_button) ;
        VideoView videoView = findViewById(R.id.vv_detail);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse(mockUrl));
                videoView.setMediaController(new MediaController(VideoDetailActivity.this));
                videoView.start();
                button.setVisibility(View.INVISIBLE);
            }
        });
    }
}
