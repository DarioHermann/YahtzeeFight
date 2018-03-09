package com.example.n0773470.yahtzeefight;

/**
 * @author  Dario Hermann
 * @version 1.00, 08/03/2018
 *
 * All three Character belong to Redshrike
 * The Awesome Possum: https://opengameart.org/content/the-awesome-possum-ultimate-smash-friends
 * Xeon: https://opengameart.org/content/xeon-ultimate-smash-friends
 * Surge (Rabbit): https://opengameart.org/content/surge-of-opensurge-for-ultimate-smash-friends
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Vibrator;
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

    int player_one_char_selected;
    int player_two_char_selected;
    int health_one = 0;
    int health_two = 0;
    int attack_one = 0;
    int attack_two = 0;
    int new_health_one = 0;
    int new_health_two = 0;
    private static final int MAX_HEALTH = 100;
    TextView health_points_one;
    TextView health_points_two;


    ProgressBar healthBarOne;
    ProgressBar healthBarTwo;
    ImageView player_one_char;
    ImageView player_two_char;
    ImageView player_one_special;
    ImageView player_two_special;
    ImageView player_one_rabbid_special;
    ImageView player_two_rabbid_special;
    AnimationDrawable number_one;
    AnimationDrawable number_two;
    boolean isNumberThree;
    boolean isNumberFour;
    AnimationDrawable number_three;
    AnimationDrawable number_four;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_screen);
        Intent i = getIntent();
        health_one = i.getIntExtra("healthOne", -1);
        health_two = i.getIntExtra("healthTwo", -1);
        attack_one = i.getIntExtra("attackOne", -1);
        attack_two = i.getIntExtra("attackTwo", -1);
        player_one_char_selected = i.getIntExtra("charOne", -1);
        player_two_char_selected = i.getIntExtra("charTwo", -1);

        healthBarOne = (ProgressBar) findViewById(R.id.player_one_health_bar);
        healthBarTwo = (ProgressBar) findViewById(R.id.player_two_health_bar);

        health_points_one = (TextView) findViewById(R.id.player_one_health_points);
        health_points_two = (TextView) findViewById(R.id.player_two_health_points);



        player_one_special = (ImageView) findViewById(R.id.player_one_special);
        player_two_special = (ImageView) findViewById(R.id.player_two_special);
        player_one_rabbid_special = (ImageView) findViewById(R.id.player_one_rabbid_special);
        player_two_rabbid_special = (ImageView) findViewById(R.id.player_two_rabbid_special);
        player_one_char = (ImageView) findViewById(R.id.player_one_char);

        player_two_char = (ImageView) findViewById(R.id.player_two_char);

        isNumberThree=false;isNumberFour=false;

        String health_total = makeDamage();

        Intent intent = new Intent();
        intent.putExtra("health_total", health_total);
        setResult(100, intent); //send health_total like it was a return type back to MainActivity
    }

    /**
     * makeDamage: The method that calculates de damage done to each player
     * @return as a string the health of both players (first 3 chars are the health of the first player,
     *  the remaining 3 are from the second player
     */
    private String makeDamage(){
        new_health_one = health_one;
        new_health_two = health_two;

        //For the damage I do the attack points (points done in this round) from the player with the most points
        //and subtract it by the value of the player with less points. The result is the damage dealt to the player
        //with less points. There's a exception:
        //If a player hits 50 points (yahtzee) then the opponent can't defend
        //If both players hit yahtzee at the same time (the probability of that happening is of 0.212%)
        //Both receive 50 of damage
        if(attack_one == 50 && attack_two == 50){
            new_health_one -= 50;
            new_health_two -= 50;
        } else if(attack_one == 50)
            new_health_two -= 50;
        else if(attack_two == 50)
            new_health_one -=50;
        else if(attack_one >= attack_two)
            new_health_two -= (attack_one-attack_two);
        else
            new_health_one -= (attack_two-attack_one);

        if(new_health_one < 0)
            new_health_one = 0;
        if(new_health_two < 0)
            new_health_two = 0;

        //The health of both players is written as a single string

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

    /**
     * health_bars: This method updates the health bars shown in the top of the screen.
     * @param points_one player one's health points
     * @param points_two player two's health points
     */
    private void health_bars(int points_one, int points_two){

        healthBarOne.setMax(MAX_HEALTH);
        healthBarOne.setProgress(points_one);
        health_points_one.setText(points_one + "/" + MAX_HEALTH);

        healthBarTwo.setMax(MAX_HEALTH);
        healthBarTwo.setProgress(points_two);
        health_points_two.setText(points_two + "/" + MAX_HEALTH);

    }


    /**
     * onWindowFocusChanged: it's automatically called by Java, when it's ready to show the screen
     * I use it to start the animations and update the heath bars after the animation's done
     */
    public void onWindowFocusChanged(final boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Thread th = new Thread(){
                public void run(){
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e){
                    }
                    //number_one is the player one's character
                    //number_two is the player two's character
                    //If the first player is either possum or xeon and makes a yahtzee, number_three starts
                    //The same goes for the second player with number_four
                    //Rabbit's yahtzee attack is animated differently, so it only uses either number_one or number_two
                    number_one.start();
                    number_two.start();
                    if(isNumberThree)
                        number_three.start();
                    if(isNumberFour)
                        number_four.start();


                    //vibrate during the attack animation, I have a 500 millis pause before it starts
                    //so the vibration doesn't start while the attack hasn't been complete
                    long[] pattern = {500, 700, 0};

                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(pattern, -1); //I have to use -1 so it doesn't repeat
                    try {
                        Thread.sleep(1000); // sleep a bit to update the health bars after the attack
                    } catch(InterruptedException e){
                    }
                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          health_bars(new_health_one, new_health_two);
                                      }
                                  });

                    try {
                        Thread.sleep(1500); //sleep a bit so the players can see what's happening on the screen
                    } catch(InterruptedException e){
                    }
                    finish();
                }
            };
            th.start(); //start the thread
        }
    }


    /**
     * attackAnimation: Controls what animations run depending on the characters chosen and the type
     * of attack
     */
    private void attackAnimation(){
        //If the player makes a special attack (yahtzee), move_type will be 3
        //If it's a normal attack, move_type will be 1
        //If it has do defend, the move_type will be 2
        if(attack_one == 50){
            isNumberThree = true;
            if(player_one_char_selected ==1)
                isPossum(1, 3);
            else if(player_one_char_selected == 2)
                isXeon(1, 3);
            else
                isRabbit(1, 3);
        }
        if(attack_two == 50){
            isNumberFour = true;
            if(player_two_char_selected ==1)
                isPossum(2, 3);
            else if(player_two_char_selected == 2)
                isXeon(2, 3);
            else
                isRabbit(2, 3);
        }
        if(attack_one >= attack_two) {
            if(attack_one != 50){
                if(player_one_char_selected == 1)
                    isPossum(1, 1);
                if(player_one_char_selected == 2)
                    isXeon(1, 1);
                if(player_one_char_selected == 3)
                    isRabbit(1, 1);
            }
            if(attack_two != 50){
                if(player_two_char_selected == 1)
                    isPossum(2, 2);
                if(player_two_char_selected == 2)
                    isXeon(2, 2);
                if(player_two_char_selected == 3)
                    isRabbit(2, 2);
            }
        } else if(attack_two > attack_one){

            if(player_one_char_selected == 1)
                isPossum(1, 2);
            if(player_one_char_selected == 2)
                isXeon(1, 2);
            if(player_one_char_selected == 3)
                isRabbit(1, 2);

            if(attack_two != 50){
                if(player_two_char_selected == 1)
                    isPossum(2, 1);
                if(player_two_char_selected == 2)
                    isXeon(2, 1);
                if(player_two_char_selected == 3)
                    isRabbit(2, 1);
            }
        }
    }

    /**
     * isPossum: Animation controller for the player with the Possum character
     * @param player The player's number (player 1 or 2)
     * @param move_type Type of move (special, normal attack, defence)
     */
    private void isPossum(int player, int move_type){
        if(player == 1){
            if(move_type == 1) {
                player_one_char.setBackgroundResource(R.drawable.possum_attack);
                number_one = (AnimationDrawable) player_one_char.getBackground();
            } else if(move_type == 2){
                player_one_char.setBackgroundResource(R.drawable.possum_defence);
                number_one = (AnimationDrawable) player_one_char.getBackground();
            } else{
                player_one_char.setBackgroundResource(R.drawable.possum_special);
                number_one = (AnimationDrawable) player_one_char.getBackground();
                player_one_special.setBackgroundResource(R.drawable.possum_special_attack);
                number_three = (AnimationDrawable) player_one_special.getBackground();
            }
        } else if(player == 2){
            if(move_type == 1) {
                player_two_char.setBackgroundResource(R.drawable.possum_attack);
                number_two = (AnimationDrawable) player_two_char.getBackground();
            } else if(move_type == 2){
                player_two_char.setBackgroundResource(R.drawable.possum_defence);
                number_two = (AnimationDrawable) player_two_char.getBackground();
            } else{
                player_two_char.setBackgroundResource(R.drawable.possum_special);
                number_two = (AnimationDrawable) player_two_char.getBackground();
                player_two_special.setBackgroundResource(R.drawable.possum_special_attack);
                number_four = (AnimationDrawable) player_two_special.getBackground();
            }
        }
    }

    /**
     * isXeon: Animation controller for the player with the Xeon character
     * @param player The player's number (player 1 or 2)
     * @param move_type Type of move (special, normal attack, defence)
     */
    private void isXeon(int player, int move_type){
        if(player == 1){
            if(move_type == 1) {
                player_one_char.setBackgroundResource(R.drawable.xeon_attack);
                number_one = (AnimationDrawable) player_one_char.getBackground();
            } else if(move_type == 2){
                player_one_char.setBackgroundResource(R.drawable.xeon_defence);
                number_one = (AnimationDrawable) player_one_char.getBackground();
            } else{
                player_one_char.setBackgroundResource(R.drawable.xeon_special);
                number_one = (AnimationDrawable) player_one_char.getBackground();
                player_one_special.setBackgroundResource(R.drawable.xeon_special_attack);
                number_three = (AnimationDrawable) player_one_special.getBackground();
            }
        } else if(player == 2){
            if(move_type == 1) {
                player_two_char.setBackgroundResource(R.drawable.xeon_attack);
                number_two = (AnimationDrawable) player_two_char.getBackground();
            } else if(move_type == 2){
                player_two_char.setBackgroundResource(R.drawable.xeon_defence);
                number_two = (AnimationDrawable) player_two_char.getBackground();
            } else{
                player_two_char.setBackgroundResource(R.drawable.xeon_special);
                number_two = (AnimationDrawable) player_two_char.getBackground();
                player_two_special.setBackgroundResource(R.drawable.xeon_special_attack);
                number_four = (AnimationDrawable) player_two_special.getBackground();
            }
        }
    }

    /**
     * isPossum: Animation controller for the player with the Rabbit character
     * @param player The player's number (player 1 or 2)
     * @param move_type Type of move (special, normal attack, defence)
     */
    private void isRabbit(int player, int move_type){
        if(player == 1){
            if(move_type == 1) {
                player_one_char.setBackgroundResource(R.drawable.rabbit_attack);
                number_one = (AnimationDrawable) player_one_char.getBackground();
            } else if(move_type == 2){
                player_one_char.setBackgroundResource(R.drawable.rabbit_defence);
                number_one = (AnimationDrawable) player_one_char.getBackground();
            } else{ //differently from the Possum and xeon, Rabbit doesn't use number_three
                player_one_rabbid_special.setBackgroundResource(R.drawable.rabbit_special_attack);
                number_one = (AnimationDrawable) player_one_rabbid_special.getBackground();
                isNumberThree = false;
            }
        } else if(player == 2){
            if(move_type == 1) {
                player_two_char.setBackgroundResource(R.drawable.rabbit_attack);
                number_two = (AnimationDrawable) player_two_char.getBackground();
            } else if(move_type == 2){
                player_two_char.setBackgroundResource(R.drawable.rabbit_defence);
                number_two = (AnimationDrawable) player_two_char.getBackground();
            } else{
                player_two_rabbid_special.setBackgroundResource(R.drawable.rabbit_special_attack);
                number_two = (AnimationDrawable) player_two_rabbid_special.getBackground();
                isNumberFour = false;
            }
        }
    }

}
