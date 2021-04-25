package com.utopia.vznotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateNote extends AppCompatActivity {

    ArrayList<String> categories = new ArrayList<>();
    String selected_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        categories.add("Аккаунты");
        categories.add("Номера телефонов");
        categories.add("+ Добавить свою");

        Spinner spinner = findViewById(R.id.spinner);
        EditText category = findViewById(R.id.input_category);
        EditText text = findViewById(R.id.input_main);
        ImageView back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            back_btn.setVisibility(View.INVISIBLE);
            category.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.VISIBLE);
        });
        Button btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(v -> {

            SharedPreferences prefs = getSharedPreferences("NOW_ID", MODE_PRIVATE);
            int last_id = prefs.getInt("LAST_ID", 0);

            SharedPreferences.Editor editor_now_id = getSharedPreferences("NOW_ID", MODE_PRIVATE).edit();
            int new_id = last_id + 1;
            editor_now_id.putInt("LAST_ID", new_id);
            editor_now_id.apply();

            SharedPreferences.Editor editor = getSharedPreferences(String.valueOf(new_id), MODE_PRIVATE).edit();
            editor.putString("TEXT", text.getText().toString());
            editor.putString("CATEGORY", selected_category);
            editor.apply();

            onBackPressed();
        });
        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> onBackPressed());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(0, true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                if (selected.equals("+ Добавить свою")){
                    spinner.setSelection(0, true);
                    back_btn.setVisibility(View.VISIBLE);
                    category.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.INVISIBLE);
                    selected_category = category.getText().toString();
                } else {
                    selected_category = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}