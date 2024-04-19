package com.main.tic_toe;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Game extends AppCompatActivity {
    private String order = "X";
    private final TextView[] elems = new TextView[9];
    private TextView textWin;
    private TextView textLoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        elems[0] = findViewById(R.id.col1_empty);
        elems[1] = findViewById(R.id.col2_empty);
        elems[2] = findViewById(R.id.col3_empty);
        elems[3] = findViewById(R.id.col4_empty);
        elems[4] = findViewById(R.id.col5_empty);
        elems[5] = findViewById(R.id.col6_empty);
        elems[6] = findViewById(R.id.col7_empty);
        elems[7] = findViewById(R.id.col8_empty);
        elems[8] = findViewById(R.id.col9_empty);

        textWin = findViewById(R.id.text_win);
        textLoose = findViewById(R.id.text_loose);

        Button btn_back = findViewById(R.id.btn_back);
        Intent intentInput = getIntent();
        int choiseInput = intentInput.getIntExtra("choise", 0);

        for (TextView elem : elems) {
            step(elem, choiseInput);
        }

        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(Game.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void step(TextView col, int game) {
        col.setOnClickListener(v -> {
            if (col.getText() == "") {
                col.setText(order);

                if (Objects.equals(order, "X"))  order = "O";
                else order = "X";

                if (game == 1) playerVsPlayer();
                else if (game == 2) playerVsComputerEasy();
                else if (game == 3) playerVsComputerHard();
            }
        });
    }
    private boolean checkWin() {
        if (elems[0].getText() != "") {
            if (elems[0].getText() == elems[1].getText() && elems[0].getText() == elems[2].getText()) {
                return true;
            }
            else if (elems[0].getText() == elems[4].getText() && elems[0].getText() == elems[8].getText()) {
                return true;
            }
            else if (elems[0].getText() == elems[3].getText() && elems[0].getText() == elems[6].getText()) {
                return true;
            }
        }
        if (elems[1].getText() != "") {
            return elems[1].getText() == elems[4].getText() && elems[1].getText() == elems[7].getText();
        }
        if (elems[2].getText() != "") {
            if (elems[2].getText() == elems[5].getText() && elems[2].getText() == elems[8].getText()) {
                return true;
            }
            else if (elems[2].getText() == elems[4].getText() && elems[2].getText() == elems[6].getText()) {
                return true;
            }
        }
        if (elems[3].getText() != "") {
            return elems[3].getText() == elems[4].getText() && elems[3].getText() == elems[5].getText();
        }
        if (elems[6].getText() != "") {
            return elems[6].getText() == elems[7].getText() && elems[6].getText() == elems[8].getText();
        }
        return false;
    }
    private boolean checkDraw() {
        boolean draw = false;

        for (TextView elem : elems) {
            if (elem.getText() != "") draw = true;
            else {
                draw = false;
                break;
            }
        }
        return draw;
    }
    private void playerVsPlayer() {
        if (checkWin()) {
            textWin.setVisibility(VISIBLE);
        }
        else if (checkDraw()) {
            textLoose.setVisibility(VISIBLE);
        }
    }

    private void playerVsComputerEasy() {
        for (TextView elem : elems) {
            if (elem.getText() == "") {
                elem.setText(order);
                break;
            }
        }

        computerCheck();
        order = "X";
    }

    private void playerVsComputerHard() {
        boolean check = false;
        if (!check) check = checkRowForComputer(elems[0], elems[1], elems[2]);
        if (!check) check = checkRowForComputer(elems[0], elems[4], elems[8]);
        if (!check) check = checkRowForComputer(elems[0], elems[3], elems[6]);
        if (!check) check = checkRowForComputer(elems[1], elems[3], elems[5]);
        if (!check) check = checkRowForComputer(elems[2], elems[5], elems[8]);
        if (!check) check = checkRowForComputer(elems[2], elems[4], elems[6]);
        if (!check) check = checkRowForComputer(elems[3], elems[4], elems[5]);
        if (!check) check = checkRowForComputer(elems[6], elems[7], elems[8]);
        if (!check && elems[4].getText().equals("")) {
            elems[4].setText(order);
            check = true;
        }
        if (!check) {
            for (TextView elem : elems) {
                if (elem.getText() == "") {
                    elem.setText(order);
                    break;
                }
            }
        }

        computerCheck();
        order = "X";
    }

    private void computerCheck() {
        if (checkWin() && Objects.equals(order, "X")) {
            textWin.setVisibility(VISIBLE);
        }
        else if (checkDraw() || (checkWin() && Objects.equals(order, "O"))) {
            textLoose.setVisibility(VISIBLE);
        }
    }
    private boolean checkRowForComputer(TextView col1, TextView col2, TextView col3) {
        TextView[] cols = {col1, col2, col3};
        int countEmpty = 0;
        int needCol = -1;

        for (int i = 0; i < cols.length; i++) {
            if (cols[i].getText().equals("")) {
                countEmpty++;
                needCol = i;
            }
        }
        if (countEmpty == 1) {
            if (cols[0].getText().equals(cols[1]) ||
                    cols[0].getText().equals(cols[2]) ||
                    cols[1].getText().equals(cols[2])) {
                cols[needCol].setText(order);
                return true;
            }
        }
        return false;
    }
}