package com.example.test713;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.test713.recycler.LinearItemDecoration;
import com.example.test713.recycler.MyAdapter;
import com.example.test713.recycler.TestData;
import com.example.test713.recycler.TestDataSet;

public class RecyclerViewActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener {

    private static final String TAG = "TAG in Recycler";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Log.i(TAG, "onCreate: ");

        recyclerView = findViewById(R.id.recycler) ;
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this) ;
        mAdapter = new MyAdapter(TestDataSet.getData()) ;

        recyclerView.setLayoutManager(layoutManager) ;
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        recyclerView.setItemAnimator(animator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.addData(position + 1, new TestData("新增头条", "0w"));
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(RecyclerViewActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.removeData(position);
    }

}