package com.example.ituneapp2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayerActivity extends AppCompatActivity {
    private YouTubePlayerView youTubePlayerView;
    private String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Set the Toolbar as the ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        url = getIntent().getStringExtra("url"); // Retrieve the URL passed to this Activity
        youTubePlayerView = findViewById(R.id.youtube_player_view);

        if (url != null) {
            // Add a listener only if URL is not null
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = extractVideoId(url);
                    if (videoId != null) {
                        youTubePlayer.loadVideo(videoId, 0);
                    } else {
                        Log.e("PlayerActivity", "Failed to extract video ID from URL");
                    }
                }
            });
        } else {
            Log.e("PlayerActivity", "No URL provided to PlayerActivity");
        }
    }

    private String extractVideoId(String videoUrl) {
        if (videoUrl != null && videoUrl.contains("v=")) {
            try {
                return videoUrl.split("v=")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e("PlayerActivity", "URL does not contain a valid video ID", e);
            }
        }
        return null; // Return null if the URL is invalid
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
