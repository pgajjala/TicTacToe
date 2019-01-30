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
    protected void onCreate(Bundle savedInstanceState){
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
    public void onClick(View v){
        if (isPlayer1 && (((Button) v).getText()=="")) {
            ((Button) v).setText("X");
            isPlayer1 = !isPlayer1;
            checkGameOver();

        } else if (!isPlayer1 && (((Button) v).getText()=="")){
            ((Button) v).setText("O");
            isPlayer1 = !isPlayer1;
            checkGameOver();
        } else {
            Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkGameOver() {
        if (checkFull() || checkRows() || checkColumns() || checkDiagonals()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid[i][j].setText("");
                }
            }
        }
    }

    private boolean checkColumns() {
        for (int j = 0; j < 3; j++) {
            if (!grid[0][j].getText().toString().equals("")
                && grid[0][j].getText().equals(grid[1][j].getText()) && grid[1][j].getText().equals(grid[2][j].getText())) {
                Toast.makeText(MainActivity.this, grid[0][j].getText() + " wins!", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    private boolean checkRows(){
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].getText().toString().equals("")
                && grid[i][0].getText().equals(grid[i][1].getText()) && grid[i][1].getText().equals(grid[i][2].getText())) {
                Toast.makeText(MainActivity.this, grid[i][0].getText() + " wins!", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        String[] check = new String[3];
        for (int i = 0; i < 3; i++) {
            check[i] = grid[i][i].getText().toString();
        }
        if(!check[0].equals("") && check[0].equals(check[1]) && check[1].equals(check[2])) {
            Toast.makeText(MainActivity.this, check[0] + " wins!", Toast.LENGTH_LONG).show();
            return true;
        }

        for (int i = 0; i < 3; i++) {
             check[i] = grid[i][2-i].getText().toString();
        }
        if (!check[0].equals("") && check[0].equals(check[1]) && check[1].equals(check[2])) {
            Toast.makeText(MainActivity.this, check[0] + " wins!", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private boolean checkFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getText().toString().equals("")) {
                    return false;
                }
            }
        }
        Toast.makeText(MainActivity.this, "It's a tie!", Toast.LENGTH_LONG).show();
        return true;
    }
}
