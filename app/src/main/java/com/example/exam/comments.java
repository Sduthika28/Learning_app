package com.example.exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class comments extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);

        lvItems = findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etNewItem = findViewById(R.id.etNewItem);
                String itemText = etNewItem.getText().toString();
                if (!itemText.isEmpty()) {
                    itemsAdapter.add(itemText);
                    etNewItem.setText("");
                }
            }
        });

        setupListViewListener();

        ImageView imageView1 = findViewById(R.id.back_to_home);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(comments.this, Third.class);
                startActivity(intent);
            }
        });

        ImageView imageView2 = findViewById(R.id.back);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(comments.this, video.class);
                startActivity(intent);
            }
        });


    }



    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener((parent, view, position, id) -> {
            items.remove(position);
            itemsAdapter.notifyDataSetChanged();
            return true;
        });
    }
}
