package com.utopia.vznotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton create_note_button = findViewById(R.id.btn_create_note);
        create_note_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateNote.class);
            startActivity(intent);
        });

        setInitialData();

        RecyclerView recyclerView = findViewById(R.id.notes_recycler);
        Adapter adapter = new Adapter(this, states);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData(){
        SharedPreferences prefs = getSharedPreferences("NOW_ID", MODE_PRIVATE);
        int last_id = prefs.getInt("LAST_ID", 0);

        for(int i = 0; i >= last_id; i++){
            SharedPreferences object = getSharedPreferences(String.valueOf(i), MODE_PRIVATE);
            states.add(new State(String.valueOf(i), object.getString("TEXT", "None"), object.getString("CATEGORY", "None")));
        }

    }
}