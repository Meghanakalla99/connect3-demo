package com.example.connect3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imv;
    public int tries=1;
    //turn==0 red, turn==1 yellow
    public int turn=0;
    //0: red 1:yellow 2:empty
    public int []filledBy = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void restart(){
        turn =0;
        for (int i=0; i<9; i++)
            filledBy[i]= 2;
        imv= (ImageView) findViewById(R.id.imageView);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView2);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView3);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView4);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView5);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView6);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView7);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView8);
        imv.setVisibility(View.INVISIBLE);
        imv= (ImageView) findViewById(R.id.imageView9);
        imv.setVisibility(View.INVISIBLE);
    }

    public void button(View v){
        imv= (ImageView) v;
        if (filledBy[Integer.parseInt(imv.getTag().toString())]==2 ){
            tries++;
            imv.setTranslationY(-1000f);
            if (turn==0){
                imv.setImageResource(R.drawable.yellow_2);
                filledBy[Integer.parseInt(imv.getTag().toString())]= turn;
                turn=1;
            }
            else{
                imv.setImageResource(R.drawable.red_2);
                filledBy[Integer.parseInt(imv.getTag().toString())]= turn;
                turn=0;
            }
            imv.animate().translationYBy(1000f).setDuration(500);
        }
        boolean winner = checkIfEq();
        if (winner) {
            if (turn ==0){
                Toast.makeText(getApplicationContext(), "Team Red wins!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Team Yellow wins!", Toast.LENGTH_LONG).show();
            }

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("Congratulations!")
                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restartGame();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
        if(tries==10 && !checkIfEq()){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("Draw!")
                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restartGame();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }

    }

    public boolean checkIfEq() {

        if (filledBy[0] == filledBy[1] && filledBy[1] == filledBy[2] && filledBy[0] != 2) {
            return true;
        } else if (filledBy[3] == filledBy[4] && filledBy[4] == filledBy[5] && filledBy[3] != 2) {
            return true;
        } else if (filledBy[6] == filledBy[7] && filledBy[7] == filledBy[8] && filledBy[6] != 2) {
            return true;
        } else if (filledBy[0] == filledBy[3] && filledBy[3] == filledBy[6] && filledBy[6] != 2) {
            return true;
        } else if (filledBy[1] == filledBy[4] && filledBy[4] == filledBy[7] && filledBy[1] != 2) {
            return true;
        } else if (filledBy[2] == filledBy[5] && filledBy[5] == filledBy[8] && filledBy[2] != 2) {
            return true;
        } else if (filledBy[0] == filledBy[4] && filledBy[4] == filledBy[8] && filledBy[0] != 2) {
            return true;
        } else if (filledBy[2] == filledBy[4] && filledBy[4] == filledBy[6] && filledBy[6] != 2) {
            return true;
        }
        else return false;
    }

    public void restartGame(){
        Activity act= MainActivity.this;
        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();
    }

}
