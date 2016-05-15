package com.imooc.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    RecyclerView mRecyclerView;
    List<String> mDatas = new ArrayList<String>();
    SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        setupAdapter();
    }

    private void setupAdapter() {
        mSimpleAdapter = new SimpleAdapter(this,mDatas);
        mRecyclerView.setAdapter(mSimpleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSimpleAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Toast.makeText(MainActivity.this, "Click : "+ pos, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int pos) {
                Toast.makeText(MainActivity.this, "Long Click : "+ pos, Toast.LENGTH_SHORT).show();
//                mSimpleAdapter.removeItem(pos);
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
    }

    private void initData() {
        for (int i = 'A'; i < 'z'; i++){
            mDatas.add(""+ (char)i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_add:
                mSimpleAdapter.addItem(1);
                break;
            case R.id.action_delete:
                mSimpleAdapter.removeItem(1);
                break;
            case R.id.action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
                break;
            case R.id.action_staggeredGrid:
//                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                Intent mIntent = new Intent(this, StaggeredActivity.class);
                startActivity(mIntent);
                break;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
