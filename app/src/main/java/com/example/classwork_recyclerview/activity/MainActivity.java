package com.example.classwork_recyclerview.activity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classwork_recyclerview.R;
import com.example.classwork_recyclerview.adapter.Adapter;
import com.example.classwork_recyclerview.databinding.ActivityMainBinding;
import com.example.classwork_recyclerview.fragment.CartFragment;
import com.example.classwork_recyclerview.fragment.FavoriteFragment;
import com.example.classwork_recyclerview.fragment.HomeFragment;
import com.example.classwork_recyclerview.fragment.MenuFragment;
import com.example.classwork_recyclerview.model.ModelList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Fragment homeFragment = new HomeFragment();
    private Fragment menuFragment = new MenuFragment();
    private Fragment cartFragment = new CartFragment();
    private Fragment favoriteFragment = new FavoriteFragment();
    private Fragment activeFragment = homeFragment;

    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        setupBottomNavigation();
        
        // Load initial fragment
        fm.beginTransaction().add(R.id.fragmentContainer, cartFragment, "3").hide(cartFragment).commit();
        fm.beginTransaction().add(R.id.fragmentContainer, menuFragment, "2").hide(menuFragment).commit();
        fm.beginTransaction().add(R.id.fragmentContainer, favoriteFragment, "4").hide(favoriteFragment).commit();
        fm.beginTransaction().add(R.id.fragmentContainer, homeFragment, "1").commit();
    }

    private void setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                fm.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                activeFragment = homeFragment;
                return true;
            } else if (id == R.id.cart) {
                fm.beginTransaction().hide(activeFragment).show(cartFragment).commit();
                activeFragment = cartFragment;
                return true;
            } else if (id == R.id.favorite) {
                fm.beginTransaction().hide(activeFragment).show(favoriteFragment).commit();
                activeFragment = favoriteFragment;
                return true;
            } else if (id == R.id.menu) {
                fm.beginTransaction().hide(activeFragment).show(menuFragment).commit();
                activeFragment = menuFragment;
                return true;
            }
            return false;
        });
    }
}
