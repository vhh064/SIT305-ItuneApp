package com.example.ituneapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ituneapp2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MyPlayListActivity extends AppCompatActivity {
    private List<ListItem> listItemList;
    private RecyclerView recyclerView;
    private PlayListAdapter playListAdapter;
    private PlaylistController playlistController;
    private ActivityMainBinding binding;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userId = getIntent().getStringExtra("userId");
        Log.d("MyPlayListActivity", "Received userId: " + userId);

        if (userId == null || userId.isEmpty()) {
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MyPlayListActivity.this, Login.class);
            startActivity(intent);
            finish();
            return;
        }

        playlistController = new PlaylistController(MyPlayListActivity.this);

        recyclerView = binding.recyclerViewTasks;

        listItemList = new ArrayList<>();
        playListAdapter = new PlayListAdapter(listItemList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(playListAdapter); // Ensure adapter is set

        refreshList();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ListItem data = listItemList.get(position);
                Intent intent = new Intent(MyPlayListActivity.this, PlayerActivity.class);
                intent.putExtra("url", data.getUrl());
                startActivity(intent);
            }

        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList() {
        if (playListAdapter == null) return;
        listItemList = playlistController.getPlayList(userId);
        playListAdapter.setTaskList(listItemList);
        playListAdapter.notifyDataSetChanged();
        Log.d("MyPlayListActivity", "Playlist refreshed with " + listItemList.size() + " items.");
    }
}


