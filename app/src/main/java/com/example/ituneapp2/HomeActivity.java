package com.example.ituneapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    private Button btnPlay;
    private Button btnAddToPlaylist;
    private Button btnMyPlayList;
    private EditText etUrl;
    private PlaylistController playlistController;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userId = getIntent().getStringExtra("username");
        Log.d("HomeActivity", "Received userId: " + userId);

        etUrl = findViewById(R.id.etUrl);
        btnAddToPlaylist = findViewById(R.id.btnAddToPlaylist);
        btnPlay = findViewById(R.id.btnPlay);
        btnMyPlayList = findViewById(R.id.btnMyPlayList);
        playlistController = new PlaylistController(HomeActivity.this);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUrl.setError(null);
                String url = etUrl.getText().toString();
                if ("".equals(url)) {
                    etUrl.setError("Enter url");
                    etUrl.requestFocus();
                    return;
                }
                Intent intent = new Intent(HomeActivity.this, PlayerActivity.class);
                intent.putExtra("url", etUrl.getText().toString().trim());
                startActivity(intent);
            }
        });

        btnAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUrl.setError(null);
                String url = etUrl.getText().toString();
                if ("".equals(url)) {
                    etUrl.setError("Enter url");
                    etUrl.requestFocus();
                    return;
                }

                ListItem data = new ListItem(url, userId);
                long id = playlistController.newListItem(data);
                if (id == -1) {
                    Snackbar.make(v, "Error saving. Try again", Snackbar.LENGTH_LONG)
                            .setAnchorView(R.id.btnPlay)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(v, "New YouTube URL added", Snackbar.LENGTH_LONG)
                            .setAnchorView(R.id.btnPlay)
                            .setAction("Action", null).show();
                }
            }
        });

        btnMyPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HomeActivity", "Launching MyPlayListActivity with userId: " + userId);
                Intent intent = new Intent(HomeActivity.this, MyPlayListActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }
}
