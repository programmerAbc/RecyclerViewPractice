package com.practice;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List<String> data;
    MyAdapter myAdapter;
    Button moveButton;

    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutManager=new LinearLayoutManager(this);
        data = new ArrayList<>();
        moveButton= (Button) findViewById(R.id.moveButton);

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              linearLayoutManager.scrollToPosition(2);
            }
        });
        data.addAll(DummyData.generateDummyData(10));
        myAdapter = new MyAdapter(data);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new MyItemDecoration(this,R.color.colorAccent));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int visibleItemCount = 0;
            int pastVisibleItemCount = 0;
            int totalItemCount = 0;
            boolean loading = false;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisibleItemCount = linearLayoutManager.findFirstVisibleItemPosition();
                    if (loading == false) {
                        if ((visibleItemCount + pastVisibleItemCount) >= totalItemCount) {
                            loading = true;
                            new Handler(MainActivity.this.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    data.addAll(DummyData.generateDummyData(10));
                                    myAdapter.notifyDataSetChanged();
                                    loading = false;
                                }
                            }, 1500);
                        }
                    }
                }
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler(MainActivity.this.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        data.addAll(DummyData.generateDummyData(10));
                        myAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

    }
}
