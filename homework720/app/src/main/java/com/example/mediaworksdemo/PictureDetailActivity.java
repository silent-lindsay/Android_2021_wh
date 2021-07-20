package com.example.mediaworksdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class PictureDetailActivity extends AppCompatActivity {

    String myUrl_2 = "https://images.sudouest.fr/2016/12/23/585ce38a66a4bdec7ef14654/golden/le-pic-du-midi-d-ossau-vu-d-ayous-une-photo-prise-en-juillet-2013.jpg";
    String myUrl_1 = "https://images.larepubliquedespyrenees.fr/2013/03/16/56815ec5a43f5e4d4093e666/golden/le-skieur-descendait-hors-piste-sur-le-versant-sud-du-pic-du-midi.jpg";
    String myError = "https://i1.wp.com/techdirectarchive.com/wp-content/uploads/2020/06/1_pUEZd8z__1p-7ICIO1NZFA.png?fit=978%2C542&ssl=1";
    String mockUrl = "https://lf1-cdn-tos.bytescm.com/obj/static/ies/bytedance_official_cn/_next/static/images/0-390b5def140dc370854c98b8e82ad394.png";
    String mockErrorUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/400px-Android_logo_2019_%28stacked%29.svg.png";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        ImageView imageView = findViewById(R.id.iv_detail);
        ImageView another = findViewById( R.id.iv_new) ;
        Button btnSuccess = findViewById(R.id.btn_load_success);
        Button btnFail = findViewById(R.id.btn_load_fail);
        Button btnRoundedCorners = findViewById(R.id.btn_rounded_corners);

        Glide.with(this).load(myUrl_2).placeholder(R.drawable.placeholder_blue).error(R.drawable.error_red).transition(withCrossFade()).into(another);
        btnSuccess.setOnClickListener( v -> {
            Glide.with(this).load(myUrl_1)
                    .placeholder(R.drawable.hhhs)
                    .error(R.drawable.error_red)
                    .transition(withCrossFade())
                    .into(imageView);
        });

        btnFail.setOnClickListener( v -> {
            Glide.with(this).load(myError)
                    .placeholder(R.drawable.error)
                    .error(R.drawable.error_red)
                    .transition(withCrossFade())
                    .into(imageView);
        });

        btnRoundedCorners.setOnClickListener( v-> {
            DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();

            Glide.with(this).load(myUrl_1)
                    .placeholder(R.drawable.hhhs)
                    .error(R.drawable.error_red)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                    .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                    .into(imageView);
        });
    }
}
