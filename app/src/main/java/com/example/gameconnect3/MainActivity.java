package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    boolean activePlayer = true;
    int[][] turns = {{2,2,2},{2,2,2},{2,2,2}};
    int winner = 2;
    int count = 0;
    String str = "Its a draw";
    public void dropIn(View view){
        ImageView identify = (ImageView)view ;
        if(winner!= 2)
            return;
        int num = Integer.parseInt(identify.getTag().toString());
        int row , col;
        row = num/3;
        col = num%3;
        if(turns[row][col] == 2 ) {
            count++;
            identify.setTranslationY(-100);
            if (activePlayer) {
                identify.setImageResource(R.drawable.yellow);
                activePlayer = false;
                turns[row][col] = 0;
            } else {
                identify.setImageResource(R.drawable.red);
                activePlayer = true;
                turns[row][col] = 1;
            }
            identify.animate().translationYBy(80).setDuration(300);
            if(count >= 5) {
                if (checkColWin(col, turns) || checkRowWin(row, turns)) {
                    winner = turns[row][col];
                } else if (row == col || row == 0 || col == 0) {
                    if (checkDiagonalWin(turns))
                        winner = turns[row][col];
                }
                if(winner!=2 || count == 9 ) {
                    if (winner == 0)
                        str = "Yellow has won !!!";
                    else if(winner == 1)
                        str = "Red has won!!";
                    TextView text = (TextView) findViewById(R.id.winnerDisplay);
                    text.setText(str);
                    text.setVisibility(View.VISIBLE);
                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    playAgain.setVisibility(View.VISIBLE);
                }

            }
        }

    }
    public boolean checkRowWin(int row,int[][] board){
        int val = board[row][0];
        return board[row][1] == val && board[row][2] == val && val != 2;
    }
    public boolean checkColWin(int col , int[][] board){
        int val = board[0][col];
        return board[1][col] == val && board[2][col] == val && val != 2;
    }
    public boolean checkDiagonalWin(int[][] board){
        int val = board[1][1];
        if(board[0][0] == val && board[2][2] == val && val!=2)
            return true;
        else return board[0][2] == val && board[2][0] == val && val != 2;
    }
    public  void playAgain(View view){
        TextView text = (TextView) findViewById(R.id.winnerDisplay);
        text.setVisibility(View.INVISIBLE);
        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout grid =(GridLayout)findViewById(R.id.gridLayout);
        str = "Its a draw !!!";
        for(int i = 0 ; i < grid.getChildCount();i++){
            ImageView counter = (ImageView)grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
        count = 0 ;
        for(int i = 0 ; i < 3 ; i++)
            for(int j = 0 ; j < 3; j++)
                turns[i][j] = 2;
        winner = 2;
        activePlayer = true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}