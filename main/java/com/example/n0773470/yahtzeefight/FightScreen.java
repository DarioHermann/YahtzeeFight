package com.example.n0773470.yahtzeefight;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class FightScreen extends AppCompatActivity {

    ProgressDialog progressBarOne;
    ProgressDialog progressBarTwo;

    ProgressBar healthBarOne;
    ProgressBar healthBarTwo;

    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();

    private long fileSize = 0;

    int health_one = 0;
    int health_two = 0;
    int attack_one = 0;
    int attack_two = 0;
    int new_health_one = 0;
    int new_health_two = 0;
    TextView health_points_one;
    TextView health_points_two;


    ImageView player_one_char;
    ImageView player_two_char;
    ImageView player_one_special;
    ImageView player_two_special;
    ImageView player_one_rabbid;
    ImageView player_two_rabbid;
    ImageView player_one_rabbid_special;
    ImageView player_two_rabbid_special;


    private static final int MAX_HEALTH = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_screen);
        Intent i = getIntent();
        health_one = i.getIntExtra("healthOne", -1);
        health_two = i.getIntExtra("healthTwo", -1);
        attack_one = i.getIntExtra("attackOne", -1);
        attack_two = i.getIntExtra("attackTwo", -1);

        healthBarOne = (ProgressBar) findViewById(R.id.player_one_health_bar);
        healthBarTwo = (ProgressBar) findViewById(R.id.player_two_health_bar);

        health_points_one = (TextView) findViewById(R.id.player_one_health_points);
        health_points_two = (TextView) findViewById(R.id.player_two_health_points);

        String health_total = makeDamage();

        Intent intent = new Intent();
        intent.putExtra("health_total", health_total);
        setResult(100, intent);

        player_one_char = (ImageView) findViewById(R.id.player_one_char);
        player_two_char = (ImageView) findViewById(R.id.player_two_char);
        player_one_special = (ImageView) findViewById(R.id.player_one_special);
        player_two_special = (ImageView) findViewById(R.id.player_two_special);
        player_one_rabbid = (ImageView) findViewById(R.id.player_one_char_rabbid);
        player_two_rabbid = (ImageView) findViewById(R.id.player_two_char_rabbid);
        player_one_rabbid_special = (ImageView) findViewById(R.id.player_one_rabbid_special);
        player_two_rabbid_special = (ImageView) findViewById(R.id.player_two_rabbid_special);

        player_one_special.setVisibility(View.INVISIBLE);
        player_two_special.setVisibility(View.INVISIBLE);
        player_one_rabbid.setVisibility(View.INVISIBLE);
        player_two_rabbid.setVisibility(View.INVISIBLE);
        player_one_rabbid_special.setVisibility(View.INVISIBLE);
        player_two_rabbid_special.setVisibility(View.INVISIBLE);

        //finish();
    }

    private String makeDamage(){
        new_health_one = health_one;
        new_health_two = health_two;

        if(attack_one >= attack_two)
            new_health_two = health_two - (attack_one-attack_two);
        else
            new_health_one = health_one - (attack_two-attack_one);

        String health_total = "";

        if(new_health_one < 10)
            health_total +="00";
        else if(new_health_one < 100)
            health_total+="0";
        health_total += new_health_one;

        if(new_health_two < 10)
            health_total +="00";
        else if(new_health_two < 100)
            health_total+="0";
        health_total += new_health_two;

        health_bars(health_one, health_two);
        return health_total;
    }

    private void health_bars(int points_one, int points_two){

        healthBarOne.setMax(MAX_HEALTH);
        healthBarOne.setProgress(points_one);
        String points = "";
        points += points_one;
        points += "/";
        points += MAX_HEALTH;
        health_points_one.setText(points);

        healthBarTwo.setMax(MAX_HEALTH);
        healthBarTwo.setProgress(points_two);
        points = "";
        points += points_two;
        points += "/";
        points += MAX_HEALTH;
        health_points_two.setText(points);


        /*progressBarOne = new ProgressDialog(this);
        progressBarOne.setMessage("Player 1");
        progressBarOne.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBarOne.setProgress(old_health_one);
        progressBarOne.setMax(MAX_HEALTH);
        progressBarOne.show();

        progressBarTwo = new ProgressDialog(this);
        progressBarTwo.setMessage("Player 2");
        progressBarTwo.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBarTwo.setProgress(old_health_two);
        progressBarTwo.setMax(MAX_HEALTH);
        progressBarTwo.show();*/

    /*    try{
            Thread.sleep(5000);
        } catch(InterruptedException e){

        }*/

        //attackAnimation(new_health_one, new_health_two);

        /*healthBarOne.setProgress(new_health_one);
        healthBarTwo.setProgress(new_health_two);*/

       // finish();
    }


    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Thread th = new Thread(){
                public void run(){
                    attackAnimation();
                    try {
                        Thread.sleep(2000);
                    } catch(InterruptedException e){
                    }
                    health_bars(new_health_one, new_health_two);
                }
            };
            th.start();

        }
    }
    private void attackAnimation(){
        player_one_char.setBackgroundResource(R.drawable.possum_attack);
        AnimationDrawable number_one = (AnimationDrawable) player_one_char.getBackground();
        number_one.start();
    }

}
