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

    AnimationDrawable number_one;
    AnimationDrawable number_two;
    boolean isNumberThree;
    boolean isNumberFour;
    AnimationDrawable number_three;
    AnimationDrawable number_four;


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



        player_one_special = (ImageView) findViewById(R.id.player_one_special);
        player_two_special = (ImageView) findViewById(R.id.player_two_special);
        player_one_rabbid = (ImageView) findViewById(R.id.player_one_char_rabbid);
        player_two_rabbid = (ImageView) findViewById(R.id.player_two_char_rabbid);
        player_one_rabbid_special = (ImageView) findViewById(R.id.player_one_rabbid_special);
        player_two_rabbid_special = (ImageView) findViewById(R.id.player_two_rabbid_special);
        player_one_char = (ImageView) findViewById(R.id.player_one_char);

        player_two_char = (ImageView) findViewById(R.id.player_two_char);

        isNumberThree=false;isNumberFour=false;

        String health_total = makeDamage();

        Intent intent = new Intent();
        intent.putExtra("health_total", health_total);
        setResult(100, intent);



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
        attackAnimation();
        health_bars(health_one, health_two);
        return health_total;
    }

    private void health_bars(int points_one, int points_two){

        healthBarOne.setMax(MAX_HEALTH);
        healthBarOne.setProgress(points_one);
        health_points_one.setText(points_one + "/" + MAX_HEALTH);

        healthBarTwo.setMax(MAX_HEALTH);
        healthBarTwo.setProgress(points_two);
        health_points_two.setText(points_two + "/" + MAX_HEALTH);

    }




    public void onWindowFocusChanged(final boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Thread th = new Thread(){
                public void run(){
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e){
                    }
                    //attackAnimation();
                    number_one.start();
                    number_two.start();
                    if(isNumberThree)
                        number_three.start();
                    if(isNumberFour)
                        number_four.start();
                    //player_one_special.setVisibility(View.VISIBLE);
                    try {
                        Thread.sleep(2000);
                    } catch(InterruptedException e){
                    }
                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          health_bars(new_health_one, new_health_two);
                                      }
                                  });

                    try {
                        Thread.sleep(2000);
                    } catch(InterruptedException e){
                    }
                    finish();
                }
            };
            th.start();
            attackAnimation();
        }
    }
    private void attackAnimation(){
        if(attack_one == 50 && attack_two == 50){
            player_one_char.setBackgroundResource(R.drawable.possum_special);
            number_one = (AnimationDrawable) player_one_char.getBackground();
            player_two_char.setBackgroundResource(R.drawable.xeon_special);
            number_two = (AnimationDrawable) player_two_char.getBackground();
            player_one_special.setBackgroundResource(R.drawable.possum_special_attack);
            number_three = (AnimationDrawable) player_one_special.getBackground();
            player_two_special.setBackgroundResource(R.drawable.xeon_special_attack);
            number_four = (AnimationDrawable) player_two_special.getBackground();
            isNumberThree = true;
            isNumberFour = true;
        } else if(attack_one == 50){
            player_one_char.setBackgroundResource(R.drawable.possum_special);
            number_one = (AnimationDrawable) player_one_char.getBackground();
            player_two_char.setBackgroundResource(R.drawable.xeon_defence);
            number_two = (AnimationDrawable) player_two_char.getBackground();
            player_one_special.setBackgroundResource(R.drawable.possum_special_attack);
            number_three = (AnimationDrawable) player_one_special.getBackground();
            isNumberThree = true;
        } else if(attack_two == 50){
            player_one_char.setBackgroundResource(R.drawable.possum_defence);
            number_one = (AnimationDrawable) player_one_char.getBackground();
            player_two_char.setBackgroundResource(R.drawable.xeon_special);
            number_two = (AnimationDrawable) player_two_char.getBackground();
            player_two_special.setBackgroundResource(R.drawable.xeon_special_attack);
            number_four = (AnimationDrawable) player_two_special.getBackground();
            isNumberFour = true;
        } else if(attack_one >= attack_two) {
            player_one_char.setBackgroundResource(R.drawable.possum_attack);
            number_one = (AnimationDrawable) player_one_char.getBackground();
            player_two_char.setBackgroundResource(R.drawable.xeon_defence);
            number_two = (AnimationDrawable) player_two_char.getBackground();
        } else{
            player_one_char.setBackgroundResource(R.drawable.possum_defence);
            number_one = (AnimationDrawable) player_one_char.getBackground();
            player_two_char.setBackgroundResource(R.drawable.xeon_attack);
            number_two = (AnimationDrawable) player_two_char.getBackground();
        }
    }

}
