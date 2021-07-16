package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter3.homework.recycler.LinearItemDecoration;
import com.example.chapter3.homework.recycler.MyAdapter;
import com.example.chapter3.homework.recycler.TestData;
import com.example.chapter3.homework.recycler.TestDataSet;

public class PlaceholderFragment extends Fragment {

    private View loading;
    private RecyclerView Recycler;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    AnimatorSet animationSet ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        loading=view.findViewById(R.id.lottie_view);

        Recycler=view.findViewById(R.id.recycler);

        Recycler.setHasFixedSize(true);        //创建线性布局管理器
        layoutManager = new LinearLayoutManager(getActivity());        //设置布局管理器
        Recycler.setLayoutManager(layoutManager);        //创建Adapter
        mAdapter = new MyAdapter(TestDataSet.getData());
//        //设置Adapter每个item的点击事件
//        mAdapter.setOnItemClickListener(new MyAdapter.IOnItemClickListener() {
//            @Override
//            public void onItemCLick(int position, TestData data) {
//
//            }
//
//            @Override
//            public void onItemLongCLick(int position, TestData data) {
//
//            }
//        });
        //设置Adapter
        Recycler.setAdapter(mAdapter);
        //分割线
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
        Recycler.addItemDecoration(itemDecoration);
        Recycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        //动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        Recycler.setItemAnimator(animator);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                Recycler.setVisibility(View.VISIBLE);
                ObjectAnimator tranout = ObjectAnimator.ofFloat(loading,"alpha",1f,0f) ;
                tranout.setInterpolator(new LinearInterpolator());
                ObjectAnimator tranin = ObjectAnimator.ofFloat(Recycler,"alpha",0f,1f) ;
                tranin.setInterpolator(new LinearInterpolator());
                animationSet = new AnimatorSet() ;
                animationSet.playTogether(tranin,tranout);
                animationSet.start();
            }
        }, 5000);
    }
}
