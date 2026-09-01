package com.example.classwork_recyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.classwork_recyclerview.R;
import com.example.classwork_recyclerview.adapter.PreViewAdapter;

public class PreViewActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TextView tvNo, tvSkip, tvPre, tvNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pre_view);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager2 = findViewById(R.id.pager);
        tvNo = findViewById(R.id.tvNo);
        tvSkip = findViewById(R.id.tvSkip);
        tvPre = findViewById(R.id.tvPre);
        tvNext = findViewById(R.id.tvNext);

        PreViewAdapter preViewAdapter = new PreViewAdapter(this);
        viewPager2.setAdapter(preViewAdapter);

        tvNext.setOnClickListener(v -> {
            int current = viewPager2.getCurrentItem();
            if (current < 2) {
                viewPager2.setCurrentItem(current + 1);
            } else {
                // Navigate to Login or Main Activity
                startActivity(new Intent(PreViewActivity.this, LoginActivity.class));
                finish();
            }
        });

        tvPre.setOnClickListener(v -> {
            int current = viewPager2.getCurrentItem();
            if (current > 0) {
                viewPager2.setCurrentItem(current - 1);
            }
        });

        tvSkip.setOnClickListener(v -> {
            startActivity(new Intent(PreViewActivity.this, LoginActivity.class));
            finish();
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tvNo.setText((position + 1) + "/3");
                
                if (position == 0) {
                    tvPre.setVisibility(View.INVISIBLE);
                } else {
                    tvPre.setVisibility(View.VISIBLE);
                }

                if (position == 2) {
                    tvNext.setText(R.string.get_started);
                } else {
                    tvNext.setText(R.string.next);
                }
            }
        });
    }
}
