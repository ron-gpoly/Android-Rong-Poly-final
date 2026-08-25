package com.example.classwork_recyclerview.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classwork_recyclerview.R;
import com.example.classwork_recyclerview.adapter.Adapter;
import com.example.classwork_recyclerview.model.ModelList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ModelList> data;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.recyclerview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initData();
        setupRecyclerView();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new ModelList(R.drawable.cloth, "Cotton", "Peter Parker's life is turned upside down.", 211, 101));
        data.add(new ModelList(R.drawable.cloth, "The Amazing Spider-Man", "Peter Parker finds a clue that might help him understand why his parents disappeared.", 150, 80));
        data.add(new ModelList(R.drawable.cloth, "Spider-Man: Homecoming", "Peter Parker balances his life as an ordinary high school student in Queens.", 180, 90));
        data.add(new ModelList(R.drawable.cloth, "Spider-Man 2", "Peter Parker is beset with troubles in his failing personal life.", 120, 60));
        data.add(new ModelList(R.drawable.cloth, "Spider-Man: Into the Spider-Verse", "Teen Miles Morales becomes the Spider-Man of his universe.", 200, 110));
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rvMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(data);
        adapter.setOnItemClickListener(position -> {
            ModelList item = data.get(position);
            Toast.makeText(MainActivity.this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }
}
