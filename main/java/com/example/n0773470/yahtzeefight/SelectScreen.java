package com.example.n0773470.yahtzeefight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectScreen extends AppCompatActivity {

    boolean isPlayerOne = true;
    int player_one_char;
    int player_two_char;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_screen);
        player_one_char = 0;
        player_two_char = 0;
    }

    private void endChoose(){
        String chars= "";
        chars += player_one_char;
        chars += player_two_char;

        Intent intent = new Intent();
        intent.putExtra("characters", chars);
        setResult(200, intent);
        finish();
    }

    public void choosePossum(View view){
        if(isPlayerOne){
            player_one_char = 1;
            isPlayerOne = false;
        }
        else{
            player_two_char = 1;
            endChoose();
        }
    }

    public void chooseXeon(View view){
        if(isPlayerOne){
            player_one_char = 2;
            isPlayerOne = false;
        }
        else{
            player_two_char = 2;
            endChoose();
        }
    }

    public void chooseRabbit(View view){
        if(isPlayerOne){
            player_one_char = 3;
            isPlayerOne = false;
        }
        else{
            player_two_char = 3;
            endChoose();
        }
    }
}
