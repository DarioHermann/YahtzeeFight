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

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectScreen extends AppCompatActivity {

    boolean isPlayerOne = true;
    int player_one_char;
    int player_two_char;

    ImageView possum;
    ImageView xeon;
    ImageView rabbit;
    AnimationDrawable possum_animation;
    AnimationDrawable xeon_animation;
    AnimationDrawable rabbit_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_screen);
        player_one_char = 0;
        player_two_char = 0;

        possum = (ImageView) findViewById(R.id.possum);
        xeon = (ImageView) findViewById(R.id.xeon);
        rabbit = (ImageView) findViewById(R.id.rabbit);

        possum.setBackgroundResource(R.drawable.possum_attack_not_one_shot);
        possum_animation = (AnimationDrawable) possum.getBackground();
        xeon.setBackgroundResource(R.drawable.xeon_attack_not_one_shot);
        xeon_animation = (AnimationDrawable) xeon.getBackground();
        rabbit.setBackgroundResource(R.drawable.rabbit_attack_not_one_shot);
        rabbit_animation = (AnimationDrawable) rabbit.getBackground();
    }

    /**
     * endChoose: method called after both player chose their characters
     */
    private void endChoose(){
        String chars= "";
        chars += player_one_char;
        chars += player_two_char;

        //They both get send as a String(the first char is the first player's character
        //The second is the second player's charact

        Intent intent = new Intent();
        intent.putExtra("characters", chars);
        setResult(200, intent); //sends characters like it was a return type back to MainActivity with the code 200
        finish();
    }

    /**
     * changeText: Changes the text at the bottom of the screen after the first player chooses his character
     */
    private void changeText(){
        TextView text = (TextView) findViewById(R.id.player_choose);
        text.setText("Player 2, Choose your Character");
    }

    /**
     * choosePossum: If the player clicks on the first button he chooses the Possum
     * And Possum's "code" is 1
     */
    public void choosePossum(View view){
        if(isPlayerOne){
            player_one_char = 1;
            isPlayerOne = false;
            changeText();
        }
        else{
            player_two_char = 1;
            endChoose();
        }
    }

    /**
     * chooseXeon: If the player clicks on the second button he chooses Xeon
     * And Xeon's "code" is 2
     */
    public void chooseXeon(View view){
        if(isPlayerOne){
            player_one_char = 2;
            isPlayerOne = false;
            changeText();
        }
        else{
            player_two_char = 2;
            endChoose();
        }
    }

    /**
     * chooseRabbit: If the player clicks on the third button he chooses the Rabbit
     * And Rabbit's "code" is 3
     */
    public void chooseRabbit(View view){
        if(isPlayerOne){
            player_one_char = 3;
            isPlayerOne = false;
            changeText();
        }
        else{
            player_two_char = 3;
            endChoose();
        }
    }


    /**
     * onWindowFocusChanged: it's automatically called by Java, when it's ready to show the screen
     * I use it to start the animations
     */
    public void onWindowFocusChanged(final boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Thread th = new Thread(){
                public void run(){
                    possum_animation.start();
                    xeon_animation.start();
                    rabbit_animation.start();
                }
            };
            th.start();
        }
    }
}
