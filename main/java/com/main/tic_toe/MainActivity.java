package com.main.tic_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_player = findViewById(R.id.gamePlayer);
        Button btn_easy = findViewById(R.id.gameComputer);
        Button btn_hard = findViewById(R.id.gameComputerHard);

        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoise(1);
            }
        });
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoise(2);
            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoise(3);
            }
        });
    }

    private void onChoise(int choise) {
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("choise", choise);
        startActivity(intent);
    }
}