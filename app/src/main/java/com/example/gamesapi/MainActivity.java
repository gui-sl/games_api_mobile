package com.example.gamesapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonUnity, buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonList = findViewById(R.id.buttonList);
        buttonUnity = findViewById(R.id.buttonUnity);

        buttonUnity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUnity = new Intent(MainActivity.this, searchUnity.class);
                startActivity(intentUnity);
            }
        });
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUnity = new Intent(MainActivity.this, searchList.class);
                startActivity(intentUnity);
            }
        });
    }
}