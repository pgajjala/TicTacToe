package com.bignerdranch.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] grid;
    private boolean isPlayer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isPlayer1 = true;
        grid = new Button[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                String squareID = "square" + i + j;
                int resID = getResources().getIdentifier(squareID, "id", getPackageName());
                grid[i][j] = findViewById(resID);
                grid[i][j].setOnClickListener(this);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (isPlayer1 && (((Button) v).getText()=="")) {
            ((Button) v).setText("X");
            isPlayer1 = !isPlayer1;
        } else if (!isPlayer1 && (((Button) v).getText()=="")){
            ((Button) v).setText("O");
            isPlayer1 = !isPlayer1;
        } else {
            Toast.makeText(MainActivity.this, "invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
