package com.example.classwork_recyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.classwork_recyclerview.R;
import com.example.classwork_recyclerview.databinding.ActivityFirstDownloadBinding;

public class FirstDownloadActivity extends AppCompatActivity {

    ActivityFirstDownloadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first_download);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("Splash", "FirstDownloadActivity started");
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Log.d("Splash", "Navigating to PreViewActivity");
            Intent intent = new Intent(FirstDownloadActivity.this, PreViewActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
