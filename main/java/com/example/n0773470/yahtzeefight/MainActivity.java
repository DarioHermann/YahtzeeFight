package com.example.n0773470.yahtzeefight;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TypedArray dice_faces;
    TypedArray dice_faces_clicked;
    TextView roll_number;
    int times_rolled;
    int turns_to_end;
    boolean is_player_one_turn;

    int[] current_dice_values = {0, 0, 0, 0, 0};
    boolean is_dice_one_clicked = false;
    boolean is_dice_two_clicked = false;
    boolean is_dice_three_clicked = false;
    boolean is_dice_four_clicked = false;
    boolean is_dice_five_clicked = false;
    int dice_one_value;
    int dice_two_value;
    int dice_three_value;
    int dice_four_value;
    int dice_five_value;

    boolean aces_one_clicked;
    boolean aces_two_clicked;
    boolean twos_one_clicked;
    boolean twos_two_clicked;
    boolean threes_one_clicked;
    boolean threes_two_clicked;
    boolean fours_one_clicked;
    boolean fours_two_clicked;
    boolean fives_one_clicked;
    boolean fives_two_clicked;
    boolean sixes_one_clicked;
    boolean sixes_two_clicked;
    boolean three_of_kind_one_clicked;
    boolean three_of_kind_two_clicked;
    boolean four_of_kind_one_clicked;
    boolean four_of_kind_two_clicked;
    boolean full_house_one_clicked;
    boolean full_house_two_clicked;
    boolean small_straight_one_clicked;
    boolean small_straight_two_clicked;
    boolean large_straight_one_clicked;
    boolean large_straight_two_clicked;
    boolean yahtzee_one_clicked;
    boolean yahtzee_two_clicked;
    boolean chance_one_clicked;
    boolean chance_two_clicked;

    boolean exists_three_kind;
    boolean exists_four_kind;
    boolean exists_full_house;
    boolean exists_small_straight;
    boolean exists_large_straight;
    boolean exists_yahtzee;

    boolean player_one_bonus;
    boolean player_two_bonus;
    int player_one_upper_points;
    int player_two_upper_points;
    int player_one_upper_total_points;
    int player_two_upper_total_points;
    int player_one_lower_points;
    int player_two_lower_points;
    int player_one_total_points;
    int player_two_total_points;

    int player_one_character;
    int player_two_character;
    int player_one_health;
    int player_two_health;
    int player_one_attack;
    int player_two_attack;
    private static final int MAX_HEALTH = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset_game();
    }


    /**
     * roll: function to roll the dices
     */
    private void roll(){
        Random r = new Random();

        ImageButton dice_one = (ImageButton) findViewById(R.id.first_dice);
        ImageButton dice_two = (ImageButton) findViewById(R.id.second_dice);
        ImageButton dice_three = (ImageButton) findViewById(R.id.third_dice);
        ImageButton dice_four = (ImageButton) findViewById(R.id.fourth_dice);
        ImageButton dice_five = (ImageButton) findViewById(R.id.fifth_dice);

        if(!is_dice_one_clicked) {
            dice_one_value = r.nextInt(6);
            dice_one.setBackgroundResource(dice_faces.getResourceId(dice_one_value, -1));
            dice_one_value++;
        }
        if(!is_dice_two_clicked){
            dice_two_value = r.nextInt(6);
            dice_two.setBackgroundResource(dice_faces.getResourceId(dice_two_value, -1));
            dice_two_value++;
        }
        if(!is_dice_three_clicked){
            dice_three_value = r.nextInt(6);
            dice_three.setBackgroundResource(dice_faces.getResourceId(dice_three_value, -1));
            dice_three_value++;
        }
        if(!is_dice_four_clicked){
            dice_four_value = r.nextInt(6);
            dice_four.setBackgroundResource(dice_faces.getResourceId(dice_four_value, -1));
            dice_four_value++;
        }
        if(!is_dice_five_clicked){
            dice_five_value = r.nextInt(6);
            dice_five.setBackgroundResource(dice_faces.getResourceId(dice_five_value, -1));
            dice_five_value++;
        }

        int numbers[] = {dice_one_value, dice_two_value, dice_three_value, dice_four_value, dice_five_value};
        check_list(numbers);
    }

    /**
     * reset_values: resets the values on the table to zero (it's called before the points are shown on the table and before the next player starts his turn)
     * @param before_count if true: doesn't change the upper values and turns the lower values in red text
     *                     else: turns every number black and resets to zero all the values that were not used.
     */
    private void reset_values(boolean before_count){
        TextView ones;
        TextView twos;
        TextView threes;
        TextView fours;
        TextView fives;
        TextView sixes;
        TextView three_of_a_kind;
        TextView four_of_a_kind ;
        TextView full_house;
        TextView small_straight;
        TextView large_straight;
        TextView yahtzee;
        TextView chance;
        boolean three_kind_is_off;
        boolean four_kind_is_off;
        boolean full_house_is_off;
        boolean sm_str_is_off;
        boolean lg_str_is_off;
        boolean yahtzee_is_off;
        boolean chance_is_off;


        if(is_player_one_turn) {
            ones = (TextView) findViewById(R.id.player_one_aces);
            twos = (TextView) findViewById(R.id.player_one_twos);
            threes = (TextView) findViewById(R.id.player_one_threes);
            fours = (TextView) findViewById(R.id.player_one_fours);
            fives = (TextView) findViewById(R.id.player_one_fives);
            sixes = (TextView) findViewById(R.id.player_one_sixes);
            three_of_a_kind = (TextView) findViewById(R.id.player_one_3_of_kind);
            four_of_a_kind = (TextView) findViewById(R.id.player_one_4_of_kind);
            full_house = (TextView) findViewById(R.id.player_one_full_house);
            small_straight = (TextView) findViewById(R.id.player_one_sm_str);
            large_straight = (TextView) findViewById(R.id.player_one_lg_str);
            yahtzee = (TextView) findViewById(R.id.player_one_yahtzee);
            chance = (TextView) findViewById(R.id.player_one_chance);

            three_kind_is_off = three_of_kind_one_clicked;
            four_kind_is_off = four_of_kind_one_clicked;
            full_house_is_off = full_house_one_clicked;
            sm_str_is_off = small_straight_one_clicked;
            lg_str_is_off = large_straight_one_clicked;
            yahtzee_is_off = yahtzee_one_clicked;
            chance_is_off = chance_one_clicked;
        } else {
            ones = (TextView) findViewById(R.id.player_two_aces);
            twos = (TextView) findViewById(R.id.player_two_twos);
            threes = (TextView) findViewById(R.id.player_two_threes);
            fours = (TextView) findViewById(R.id.player_two_fours);
            fives = (TextView) findViewById(R.id.player_two_fives);
            sixes = (TextView) findViewById(R.id.player_two_sixes);
            three_of_a_kind = (TextView) findViewById(R.id.player_two_3_of_kind);
            four_of_a_kind = (TextView) findViewById(R.id.player_two_4_of_kind);
            full_house = (TextView) findViewById(R.id.player_two_full_house);
            small_straight = (TextView) findViewById(R.id.player_two_sm_str);
            large_straight = (TextView) findViewById(R.id.player_two_lg_str);
            yahtzee = (TextView) findViewById(R.id.player_two_yahtzee);
            chance = (TextView) findViewById(R.id.player_two_chance);

            three_kind_is_off = three_of_kind_two_clicked;
            four_kind_is_off = four_of_kind_two_clicked;
            full_house_is_off = full_house_two_clicked;
            sm_str_is_off = small_straight_two_clicked;
            lg_str_is_off = large_straight_two_clicked;
            yahtzee_is_off = yahtzee_two_clicked;
            chance_is_off = chance_two_clicked;
        }

        if(!before_count) {
            TextView up[] = {ones, twos, threes, fours, fives, sixes};

            boolean[] status = {aces_one_clicked, twos_one_clicked, threes_one_clicked, fours_one_clicked, fives_one_clicked, sixes_one_clicked,
                    aces_two_clicked, twos_two_clicked, threes_two_clicked, fours_two_clicked, fives_two_clicked, sixes_two_clicked};

            for(int j = 0; j < 6; j++){
                if(is_player_one_turn) {
                    if (!status[j]) {
                        up[j].setText("0");
                        up[j].setTextColor(Color.BLACK);
                    }
                } else {
                    if (!status[j+6]) {
                        up[j].setText("0");
                        up[j].setTextColor(Color.BLACK);
                    }
                }
            }
        }

        if(!three_kind_is_off && before_count){
            three_of_a_kind.setTextColor(Color.RED);
            three_of_a_kind.setText("0");
        } else if(!three_kind_is_off){
            three_of_a_kind.setTextColor(Color.BLACK);
            three_of_a_kind.setText("0");
        }

        if(!four_kind_is_off && before_count){
            four_of_a_kind.setTextColor(Color.RED);
            four_of_a_kind.setText("0");
        } else if(!four_kind_is_off) {
            four_of_a_kind.setTextColor(Color.BLACK);
            four_of_a_kind.setText("0");
        }

        if(!full_house_is_off && before_count){
            full_house.setTextColor(Color.RED);
            full_house.setText("0");
        } else if(!full_house_is_off){
            full_house.setTextColor(Color.BLACK);
            full_house.setText("0");
        }

        if(!sm_str_is_off && before_count){
            small_straight.setTextColor(Color.RED);
            small_straight.setText("0");
        } else if(!sm_str_is_off){
            small_straight.setTextColor(Color.BLACK);
            small_straight.setText("0");
        }

        if(!lg_str_is_off && before_count){
            large_straight.setTextColor(Color.RED);
            large_straight.setText("0");
        } else if(!lg_str_is_off){
            large_straight.setTextColor(Color.BLACK);
            large_straight.setText("0");
        }

        if(!yahtzee_is_off && before_count){
            yahtzee.setTextColor(Color.RED);
            yahtzee.setText("0");
        } else if(!yahtzee_is_off) {
            yahtzee.setTextColor(Color.BLACK);
            yahtzee.setText("0");
        }

        if(!chance_is_off && before_count){
            chance.setTextColor(Color.RED);
            chance.setText("0");
        } else if(!chance_is_off){
            chance.setTextColor(Color.BLACK);
            chance.setText("0");
        }

        if(!before_count)
            roll_number.setText("3");
    }

    /**
     * check_list: Checks how many point each line weights on each roll of the dices.
     * @param numbers list of the values of the current dices
     */
    private void check_list(int[] numbers){
        System.arraycopy(numbers, 0, current_dice_values, 0, numbers.length);
        int upper_values[] = {0, 0, 0, 0, 0, 0};
        TextView ones;
        TextView twos;
        TextView threes;
        TextView fours;
        TextView fives;
        TextView sixes;
        TextView three_of_a_kind;
        TextView four_of_a_kind;
        TextView full_house;
        TextView small_straight;
        TextView large_straight;
        TextView yahtzee;
        TextView chance;

        boolean three_kind_is_off;
        boolean four_kind_is_off;
        boolean full_house_is_off;
        boolean sm_str_is_off;
        boolean lg_str_is_off;
        boolean yahtzee_is_off;
        boolean chance_is_off;

        if(is_player_one_turn){
            ones = (TextView) findViewById(R.id.player_one_aces);
            twos = (TextView) findViewById(R.id.player_one_twos);
            threes = (TextView) findViewById(R.id.player_one_threes);
            fours = (TextView) findViewById(R.id.player_one_fours);
            fives = (TextView) findViewById(R.id.player_one_fives);
            sixes = (TextView) findViewById(R.id.player_one_sixes);
            three_of_a_kind = (TextView) findViewById(R.id.player_one_3_of_kind);
            four_of_a_kind = (TextView) findViewById(R.id.player_one_4_of_kind);
            full_house = (TextView) findViewById(R.id.player_one_full_house);
            small_straight = (TextView) findViewById(R.id.player_one_sm_str);
            large_straight = (TextView) findViewById(R.id.player_one_lg_str);
            yahtzee = (TextView) findViewById(R.id.player_one_yahtzee);
            chance = (TextView) findViewById(R.id.player_one_chance);

            three_kind_is_off = three_of_kind_one_clicked;
            four_kind_is_off = four_of_kind_one_clicked;
            full_house_is_off = full_house_one_clicked;
            sm_str_is_off = small_straight_one_clicked;
            lg_str_is_off = large_straight_one_clicked;
            yahtzee_is_off = yahtzee_one_clicked;
            chance_is_off = chance_one_clicked;
        } else {
            ones = (TextView) findViewById(R.id.player_two_aces);
            twos = (TextView) findViewById(R.id.player_two_twos);
            threes = (TextView) findViewById(R.id.player_two_threes);
            fours = (TextView) findViewById(R.id.player_two_fours);
            fives = (TextView) findViewById(R.id.player_two_fives);
            sixes = (TextView) findViewById(R.id.player_two_sixes);
            three_of_a_kind = (TextView) findViewById(R.id.player_two_3_of_kind);
            four_of_a_kind = (TextView) findViewById(R.id.player_two_4_of_kind);
            full_house = (TextView) findViewById(R.id.player_two_full_house);
            small_straight = (TextView) findViewById(R.id.player_two_sm_str);
            large_straight = (TextView) findViewById(R.id.player_two_lg_str);
            yahtzee = (TextView) findViewById(R.id.player_two_yahtzee);
            chance = (TextView) findViewById(R.id.player_two_chance);

            three_kind_is_off = three_of_kind_two_clicked;
            four_kind_is_off = four_of_kind_two_clicked;
            full_house_is_off = full_house_two_clicked;
            sm_str_is_off = small_straight_two_clicked;
            lg_str_is_off = large_straight_two_clicked;
            yahtzee_is_off = yahtzee_two_clicked;
            chance_is_off = chance_two_clicked;
        }

        reset_values(true);

        TextView up[] = {ones, twos, threes, fours, fives, sixes};

        for(int i = 0; i < 5; i++)
            upper_values[numbers[i]-1] += numbers[i];

        boolean[] status = {aces_one_clicked, twos_one_clicked, threes_one_clicked, fours_one_clicked, fives_one_clicked, sixes_one_clicked,
                aces_two_clicked, twos_two_clicked, threes_two_clicked, fours_two_clicked, fives_two_clicked, sixes_two_clicked};

        for(int j = 0; j < 6; j++){
            if(is_player_one_turn) {
                if (!status[j]) {
                    up[j].setText(Integer.toString(upper_values[j]));
                    up[j].setTextColor(Color.RED);
                }
            } else {
                if (!status[j+6]) {
                    up[j].setText(Integer.toString(upper_values[j]));
                    up[j].setTextColor(Color.RED);
                }
            }
        }

        numbers = sort_numbers(numbers);

        exists_three_kind = false;
        exists_four_kind = false;
        exists_full_house = false;
        exists_yahtzee = false;
        exists_small_straight = false;
        exists_large_straight = false;


        int chance_value=0;
        for(int i = 0; i < numbers.length; i++)
            chance_value+=numbers[i];

        //I think this for is a quicker way to find out about a lot of the points possible
        for(int i =0; i < 3; i++){
            if(numbers[i] == numbers[i+1] && numbers[i+1]==numbers[i+2]){ //If this is true, three of a kind exists
                exists_three_kind = true;
                if(i < 2) {
                    if (numbers[i + 2] == numbers[i + 3]) { //If i < 2 and this is true, a four of a kind exists
                        exists_four_kind = true;
                        if (i == 0) {
                            if (numbers[i + 3] == numbers[i + 4]) //If i < 1 and this is true, yahtzee exists
                                exists_yahtzee = true;
                        }
                    }else if(i == 0 && numbers[i+3] == numbers[i+4]){ //if 3 of a kind and 2 of another kind exist, then Full House exists
                        exists_full_house = true;
                    }
                }
                if(i == 2 && numbers[0] == numbers[1])
                    exists_full_house = true;
            }
            if(exists_three_kind) //If three of a kind was proven, we can break out of the for loop
                break;
        }

        //setting the points for the players to guide themselves (a good option for yahtzee begginners)
       if(exists_three_kind && !three_kind_is_off)
            three_of_a_kind.setText(Integer.toString(chance_value));
       if(exists_four_kind && !four_kind_is_off)
           four_of_a_kind.setText(Integer.toString(chance_value));
       if (exists_full_house && !full_house_is_off)
           full_house.setText("25");
       if(exists_yahtzee && !yahtzee_is_off)
           yahtzee.setText("50");

       //Checks if small or large straight exist (1-2-3-4-5 or 2-3-4-5-6)
       for(int i = 0; i < numbers.length-1; i++) {
           if (numbers[i] != (numbers[i + 1] + 1))
               break;
           if (i == numbers.length - 2) {
               if (numbers[i+1] == 2)
                   exists_large_straight = true;
               else
                   exists_small_straight = true;
           }
       }

       if(exists_small_straight && !sm_str_is_off)
           small_straight.setText("30");
       else if(exists_large_straight && !lg_str_is_off)
           large_straight.setText("40");

       if(!chance_is_off)
            chance.setText(Integer.toString(chance_value));
    }

    /**
     * sort_numbers: Sort the values of the dices (this is done so it's faster and easier to check if three of a kind, four of a kind, full house, etc. exist
     * @param numbers the list of the dices current values
     * @return sorted dice values list
     */
    private int[] sort_numbers(int[] numbers){
        int x;
        for(int i = 0; i < numbers.length-1; i++){
            for(int j = 1; j < numbers.length-i;j++){
                if(numbers[j-1] < numbers[j]) {
                    x = numbers[j-1];
                    numbers[j-1] = numbers[j];
                    numbers[j] = x;
                }
            }
        }
        return numbers;
    }

    /**
     * roll_dice: method called when the roll Button is pressed (it counts how many times the player rolled the dices)
     *
     */
    public void roll_dice(View view){
        Button roll_button = (Button) findViewById(R.id.roll_button);
        times_rolled++;
        if(times_rolled >= 3) {
            roll_button.setVisibility(View.INVISIBLE);
        }
        roll_number.setText(Integer.toString(3-times_rolled)); //This updates the Baloon on the right of the roll button. (Indicates how many rolls the player can make)
        roll();
    }

    /**
     * reset_dices: resets the dices before each players turn
     */
    private void reset_dices(){
        ImageButton dice_one = (ImageButton) findViewById(R.id.first_dice);
        ImageButton dice_two = (ImageButton) findViewById(R.id.second_dice);
        ImageButton dice_three = (ImageButton) findViewById(R.id.third_dice);
        ImageButton dice_four = (ImageButton) findViewById(R.id.fourth_dice);
        ImageButton dice_five = (ImageButton) findViewById(R.id.fifth_dice);
        Button roll_button = (Button) findViewById(R.id.roll_button);
        TextView player_option = (TextView) findViewById(R.id.player_option_text);

        is_player_one_turn = !is_player_one_turn;
        times_rolled = 0;

        dice_one.setBackgroundResource(R.drawable.dice_reset);
        dice_two.setBackgroundResource(R.drawable.dice_reset);
        dice_three.setBackgroundResource(R.drawable.dice_reset);
        dice_four.setBackgroundResource(R.drawable.dice_reset);
        dice_five.setBackgroundResource(R.drawable.dice_reset);

        is_dice_one_clicked = false;
        is_dice_two_clicked = false;
        is_dice_three_clicked = false;
        is_dice_four_clicked = false;
        is_dice_five_clicked = false;
        if(roll_button.getVisibility() == View.INVISIBLE)
            roll_button.setVisibility(View.VISIBLE);
        if(is_player_one_turn)
            player_option.setText(R.string.player_1);
        else
            player_option.setText(R.string.player_2);
    }

    /**
     * reset_game: Resets the game (points and table)
     * it's called when the app is started at the beginning and after a player presses the button "new game"
     */
    private void reset_game(){
        TextView win_text = (TextView) findViewById(R.id.winning_text);
        Button new_game_button = (Button) findViewById(R.id.new_game_button);

        dice_faces = getResources().obtainTypedArray(R.array.dice_faces);
        dice_faces_clicked = getResources().obtainTypedArray(R.array.dice_faces_clicked);

        win_text.setVisibility(View.INVISIBLE);
        new_game_button.setVisibility(View.INVISIBLE);

        roll_number = (TextView) findViewById(R.id.roll_number);

        is_player_one_turn = false;
        turns_to_end = 26;

        dice_one_value = 0;
        dice_two_value = 0;
        dice_three_value = 0;
        dice_four_value = 0;
        dice_five_value = 0;

        aces_one_clicked = false;
        aces_two_clicked = false;
        twos_one_clicked = false;
        twos_two_clicked = false;
        threes_one_clicked = false;
        threes_two_clicked = false;
        fours_one_clicked = false;
        fours_two_clicked = false;
        fives_one_clicked = false;
        fives_two_clicked = false;
        sixes_one_clicked = false;
        sixes_two_clicked = false;
        three_of_kind_one_clicked = false;
        three_of_kind_two_clicked = false;
        four_of_kind_one_clicked = false;
        four_of_kind_two_clicked = false;
        full_house_one_clicked = false;
        full_house_two_clicked = false;
        small_straight_one_clicked = false;
        small_straight_two_clicked = false;
        large_straight_one_clicked = false;
        large_straight_two_clicked = false;
        yahtzee_one_clicked = false;
        yahtzee_two_clicked = false;
        chance_one_clicked = false;
        chance_two_clicked = false;

        exists_three_kind = false;
        exists_four_kind = false;
        exists_full_house = false;
        exists_yahtzee = false;
        exists_small_straight = false;
        exists_large_straight = false;

        player_one_bonus=false;
        player_two_bonus=false;
        player_one_upper_points = 0;
        player_two_upper_points = 0;
        player_one_upper_total_points = 0;
        player_two_upper_total_points = 0;
        player_one_lower_points = 0;
        player_two_lower_points = 0;
        player_one_total_points = 0;
        player_two_total_points = 0;

        player_one_health = MAX_HEALTH;
        player_two_health = MAX_HEALTH;
        player_one_attack = 0;
        player_two_attack = 0;

        reset_dices();
        Intent screen = new Intent(getApplicationContext(), SelectScreen.class);
        startActivityForResult(screen, 200);
    }

    /**
     * reset_button: it's called with reset_game. It resets all the table points "buttons" So they can be pressed again.
     */
    private void reset_buttons(){
        FrameLayout aces_button;
        FrameLayout twos_button;
        FrameLayout threes_button;
        FrameLayout fours_button;
        FrameLayout fives_button;
        FrameLayout sixes_button;
        FrameLayout three_kind_button;
        FrameLayout four_kind_button;
        FrameLayout full_house_button;
        FrameLayout small_straight_button;
        FrameLayout large_straight_button;
        FrameLayout yahtzee_button;
        FrameLayout chance_button;

        if(is_player_one_turn) {
            aces_button = (FrameLayout) findViewById(R.id.aces_button_one);
            twos_button = (FrameLayout)findViewById(R.id.twos_button_one);
            threes_button = (FrameLayout) findViewById(R.id.threes_button_one);
            fours_button = (FrameLayout) findViewById(R.id.fours_button_one);
            fives_button = (FrameLayout) findViewById(R.id.fives_button_one);
            sixes_button = (FrameLayout) findViewById(R.id.sixes_button_one);
            three_kind_button = (FrameLayout) findViewById(R.id.three_kind_button_one);
            four_kind_button = (FrameLayout) findViewById(R.id.four_kind_button_one);
            full_house_button = (FrameLayout) findViewById(R.id.full_house_button_one);
            small_straight_button = (FrameLayout) findViewById(R.id.small_straight_button_one);
            large_straight_button = (FrameLayout) findViewById(R.id.large_straight_button_one);
            yahtzee_button = (FrameLayout) findViewById(R.id.yahtzee_button_one);
            chance_button = (FrameLayout) findViewById(R.id.chance_button_one);
        } else {
            aces_button = (FrameLayout) findViewById(R.id.aces_button_two);
            twos_button = (FrameLayout)findViewById(R.id.twos_button_two);
            threes_button = (FrameLayout) findViewById(R.id.threes_button_two);
            fours_button = (FrameLayout) findViewById(R.id.fours_button_two);
            fives_button = (FrameLayout) findViewById(R.id.fives_button_two);
            sixes_button = (FrameLayout) findViewById(R.id.sixes_button_two);
            three_kind_button = (FrameLayout) findViewById(R.id.three_kind_button_two);
            four_kind_button = (FrameLayout) findViewById(R.id.four_kind_button_two);
            full_house_button = (FrameLayout) findViewById(R.id.full_house_button_two);
            small_straight_button = (FrameLayout) findViewById(R.id.small_straight_button_two);
            large_straight_button = (FrameLayout) findViewById(R.id.large_straight_button_two);
            yahtzee_button = (FrameLayout) findViewById(R.id.yahtzee_button_two);
            chance_button = (FrameLayout) findViewById(R.id.chance_button_two);
        }

        aces_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                aces_clicked(view);
            }
        });
        twos_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                twos_clicked(view);
            }
        });
        threes_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                threes_clicked(view);
            }
        });
        fours_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fours_clicked(view);
            }
        });
        fives_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fives_clicked(view);
            }
        });
        sixes_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                sixes_clicked(view);
            }
        });
        three_kind_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                three_of_a_kind_clicked(view);
            }
        });
        four_kind_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                four_of_a_kind_clicked(view);
            }
        });
        full_house_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                full_house_clicked(view);
            }
        });
        small_straight_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                small_straight_clicked(view);
            }
        });
        large_straight_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                large_straight_clicked(view);
            }
        });
        yahtzee_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                yahtzee_clicked(view);
            }
        });
        chance_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                chance_clicked(view);
            }
        });
    }


    /**
     * finish_game: This method is called when the last round has been played to determine which player won
     * and make the "new game" button visible
     */
    private void finish_game(){
        TextView win_text = (TextView) findViewById(R.id.winning_text);
        Button new_game_button = (Button) findViewById(R.id.new_game_button);
        if(player_one_health > player_two_health)
            win_text.setText(R.string.player_1_wins);
        else if(player_one_health < player_two_health)
            win_text.setText(R.string.player_2_wins);
        else
            win_text.setText(R.string.draw);

        win_text.setVisibility(View.VISIBLE);
        new_game_button.setVisibility(View.VISIBLE);
    }

    /**
     * new_game: method called when a game is finished and the user presses the "new game" button
     *
     */
    public void new_game(View view){
        TextView upper_total = (TextView) findViewById(R.id.player_one_total);
        TextView bonus = (TextView) findViewById(R.id.player_one_bonus);
        TextView upper_total_total = (TextView) findViewById(R.id.player_one_up_total);
        TextView lower_total = (TextView) findViewById(R.id.player_one_low_total);
        TextView upper_total_down = (TextView) findViewById(R.id.player_one_up_total_total);
        TextView combined_total = (TextView) findViewById(R.id.player_one_combined_total);

        TextView[] texts= {upper_total, bonus, upper_total_total, lower_total, upper_total_down, combined_total};

        for(int i = 0; i < texts.length; i++){
            texts[i].setText("0");
            texts[i].setTextColor(Color.BLACK);
        }

        texts[0] = (TextView) findViewById(R.id.player_two_total);
        texts[1] = (TextView) findViewById(R.id.player_two_bonus);
        texts[2] = (TextView) findViewById(R.id.player_two_up_total);
        texts[3] = (TextView) findViewById(R.id.player_two_low_total);
        texts[4] = (TextView) findViewById(R.id.player_two_up_total_total);
        texts[5] = (TextView) findViewById(R.id.player_two_combined_total);

        for(int i = 0; i < texts.length; i++){
            texts[i].setText("0");
            texts[i].setTextColor(Color.BLACK);
        }

        reset_game();
        for(int i = 0; i < 2; i++) {
            reset_values(false);
            reset_buttons();
            is_player_one_turn = !is_player_one_turn;
        }
    }



    /**
     * dice_one_clicked: method called when the first dice is clicked
     * to lock or unlock the dice
     */
    public void dice_one_click(View view){
        ImageButton dice = (ImageButton) findViewById(R.id.first_dice);
        if(!is_dice_one_clicked){
            dice.setBackgroundResource(dice_faces_clicked.getResourceId(dice_one_value-1, -1));
            is_dice_one_clicked = true;
        } else{
            dice.setBackgroundResource(dice_faces.getResourceId(dice_one_value-1, -1));
            is_dice_one_clicked=false;
        }
    }

    /**
     * dice_two_clicked: method called when the second dice is clicked
     * to lock or unlock the dice
     */
    public void dice_two_click(View view){
        ImageButton dice = (ImageButton) findViewById(R.id.second_dice);
        if(!is_dice_two_clicked){
            dice.setBackgroundResource(dice_faces_clicked.getResourceId(dice_two_value-1, -1));
            is_dice_two_clicked = true;
        } else{
            dice.setBackgroundResource(dice_faces.getResourceId(dice_two_value-1, -1));
            is_dice_two_clicked=false;
        }
    }

    /**
     * dice_three_clicked: method called when the third dice is clicked
     * to lock or unlock the dice
     */
    public void dice_three_click(View view){
        ImageButton dice = (ImageButton) findViewById(R.id.third_dice);
        if(!is_dice_three_clicked){
            dice.setBackgroundResource(dice_faces_clicked.getResourceId(dice_three_value-1, -1));
            is_dice_three_clicked = true;
        } else{
            dice.setBackgroundResource(dice_faces.getResourceId(dice_three_value-1, -1));
            is_dice_three_clicked=false;
        }
    }

    /**
     * dice_four_clicked: method called when the fourth dice is clicked
     * to lock or unlock the dice
     */
    public void dice_four_click(View view){
        ImageButton dice = (ImageButton) findViewById(R.id.fourth_dice);
        if(!is_dice_four_clicked){
            dice.setBackgroundResource(dice_faces_clicked.getResourceId(dice_four_value-1, -1));
            is_dice_four_clicked = true;
        } else{
            dice.setBackgroundResource(dice_faces.getResourceId(dice_four_value-1, -1));
            is_dice_four_clicked=false;
        }
    }

    /**
     * dice_five_clicked: method called when the fifth dice is clicked
     * to lock or unlock the dice
     */
    public void dice_five_click(View view){
        ImageButton dice = (ImageButton) findViewById(R.id.fifth_dice);
        if(!is_dice_five_clicked){
            dice.setBackgroundResource(dice_faces_clicked.getResourceId(dice_five_value-1, -1));
            is_dice_five_clicked = true;
        } else{
            dice.setBackgroundResource(dice_faces.getResourceId(dice_five_value-1, -1));
            is_dice_five_clicked=false;
        }
    }



    /**
     * update_total_points: update the players total points
     * @param points points to be added to the total score
     */
    private void update_total_points(int points){
        TextView total_points;
        if(is_player_one_turn) {
            total_points = (TextView) findViewById(R.id.player_one_combined_total);
            player_one_total_points += points;
            total_points.setText(Integer.toString(player_one_total_points));
            player_one_attack = points;
        }
        else {
            total_points = (TextView) findViewById(R.id.player_two_combined_total);
            player_two_total_points += points;
            total_points.setText(Integer.toString(player_two_total_points));
            player_two_attack = points;
        }

        turns_to_end--; //counter to end the game, if it reaches 0, it means all boxes where ticked

        reset_values(false);

        //After the second player has played, the Activity FightScreen gets called
        if(!is_player_one_turn){
            Intent in = new Intent(getApplicationContext(), FightScreen.class);
            in.putExtra("charOne", player_one_character);
            in.putExtra("charTwo", player_two_character);
            in.putExtra("healthOne", player_one_health);
            in.putExtra("healthTwo", player_two_health);
            in.putExtra("attackOne", player_one_attack);
            in.putExtra("attackTwo", player_two_attack);
            startActivityForResult(in, 100); //startActivityForResult(Intent, requestCod) is the same as startActivity(Intent)
            //But it expects a result back from the second activity (like a return type of a method)
            //And it's caught by the onActivityResult method
        }
    }

    /**
     * onActivityResult: The method that catches the result from the activity called
     * @param requestCode codeInput in startActivityForResult
     * @param resultCode codeInput in the other's activity setResult
     * @param data The result obtained from the other activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){ //FightScreen resultCode
            String health = data.getStringExtra("health_total");
            player_one_health = Integer.parseInt(health.substring(0, 3));
            player_two_health = Integer.parseInt(health.substring(3, 6));

            if(player_one_health <= 0 || player_two_health <= 0 || turns_to_end <= 0)
                finish_game();
        }
        else if(resultCode == 200){ //SelectScren resultCode
            String chars = data.getStringExtra("characters");

            player_one_character = Integer.parseInt(chars.substring(0, 1));
            player_two_character = Integer.parseInt(chars.substring(1, 2));
        }
    }

    /**
     * update_upper_points: update the players upper points
     * @param points points to be added to the upper score
     */
    private void update_upper_points(int points){
        TextView up_points;
        TextView up_total_points;
        TextView up_total_points_down;
        TextView up_bonus;

        if(is_player_one_turn){
            up_points = (TextView) findViewById(R.id.player_one_total);
            up_total_points = (TextView) findViewById(R.id.player_one_up_total);
            up_total_points_down = (TextView) findViewById(R.id.player_one_up_total_total);
            up_bonus = (TextView) findViewById(R.id.player_one_bonus);

            player_one_upper_points += points;
            player_one_upper_total_points += points;
            up_points.setText(Integer.toString(player_one_upper_points));
            if(!player_one_bonus && player_one_upper_points >= 63) {
                player_one_bonus = true;
                up_bonus.setText("35");
                player_one_upper_total_points += 35;
                points += 35;
            }

            up_total_points.setText(Integer.toString(player_one_upper_total_points));
            up_total_points_down.setText(Integer.toString(player_one_upper_total_points));

        } else {
            up_points = (TextView) findViewById(R.id.player_two_total);
            up_total_points = (TextView) findViewById(R.id.player_two_up_total);
            up_total_points_down = (TextView) findViewById(R.id.player_two_up_total_total);
            up_bonus = (TextView) findViewById(R.id.player_two_bonus);

            player_two_upper_points += points;
            player_two_upper_total_points += points;
            up_points.setText(Integer.toString(player_two_upper_points));
            if(!player_two_bonus && player_two_upper_points >= 63) {
                player_two_bonus = true;
                up_bonus.setText("35");
                player_two_upper_total_points += 35;
                points += 35;
            }

            up_total_points.setText(Integer.toString(player_two_upper_total_points));
            up_total_points_down.setText(Integer.toString(player_two_upper_total_points));
        }

        update_total_points(points);
    }

    /**
     * update_lower_points: update the players lower points
     * @param points points to be added to the lower score
     */
    private void update_lower_points(int points){
        TextView low_points;
        if(is_player_one_turn){
            low_points = (TextView) findViewById(R.id.player_one_low_total);
            player_one_lower_points += points;
            low_points.setText(Integer.toString(player_one_lower_points));
        } else {
            low_points = (TextView) findViewById(R.id.player_two_low_total);
            player_two_lower_points += points;
            low_points.setText(Integer.toString(player_two_lower_points));
        }
        update_total_points(points);
    }


    /**
     * nothing: a method that does nothing. This method is called by the table buttons after they already have been pressed
     * It's a quicker way to make so the buttons aren't clickable twice
     */
    public void nothing(){
        ;
    }



    /**
     * aces_clicked: a method called when the aces "button" is clicked
     */
    public void aces_clicked(View view){
        TextView aces;
        FrameLayout aces_button;
        if(is_player_one_turn) {
            aces = (TextView) findViewById(R.id.player_one_aces);
            aces_button = (FrameLayout) findViewById(R.id.aces_button_one);
            aces_one_clicked = true;
        } else{
            aces = (TextView) findViewById(R.id.player_two_aces);
            aces_button = (FrameLayout) findViewById(R.id.aces_button_two);
            aces_two_clicked = true;
        }
        int count = 0;
        for (int x : current_dice_values)
            if (x == 1)
                count += x;
        aces.setTextColor(Color.BLACK);
        aces_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        update_upper_points(count);
        reset_dices();
    }

    /**
     * twos_clicked: a method called when the twos "button" is clicked
     */
    public void twos_clicked(View view){
        TextView twos;
        FrameLayout twos_button;
        if(is_player_one_turn){
            twos = (TextView) findViewById(R.id.player_one_twos);
            twos_button = (FrameLayout)findViewById(R.id.twos_button_one);
            twos_one_clicked = true;
        } else {
            twos = (TextView) findViewById(R.id.player_two_twos);
            twos_button = (FrameLayout)findViewById(R.id.twos_button_two);
            twos_two_clicked = true;
        }
        int count = 0;
        for(int x : current_dice_values)
            if(x == 2)
                count += x;
        twos.setTextColor(Color.BLACK);
        twos_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        update_upper_points(count);
        reset_dices();
    }

    /**
     * threes_clicked: a method called when the threes "button" is clicked
     */
    public void threes_clicked(View view){
        TextView threes;
        FrameLayout threes_button;
        if(is_player_one_turn){
            threes = (TextView) findViewById(R.id.player_one_threes);
            threes_button = (FrameLayout) findViewById(R.id.threes_button_one);
            threes_one_clicked = true;
        } else {
            threes = (TextView) findViewById(R.id.player_two_threes);
            threes_button = (FrameLayout) findViewById(R.id.threes_button_two);
            threes_two_clicked = true;
        }
        int count = 0;
        for(int x : current_dice_values)
            if(x == 3)
                count += x;
        threes.setTextColor(Color.BLACK);
        threes_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        update_upper_points(count);
        reset_dices();
    }

    /**
     * fours_clicked: a method called when the fours "button" is clicked
     */
    public void fours_clicked(View view){
        TextView fours;
        FrameLayout fours_button;
        if(is_player_one_turn){
            fours = (TextView) findViewById(R.id.player_one_fours);
            fours_button = (FrameLayout) findViewById(R.id.fours_button_one);
            fours_one_clicked = true;
        } else {
            fours = (TextView) findViewById(R.id.player_two_fours);
            fours_button = (FrameLayout) findViewById(R.id.fours_button_two);
            fours_two_clicked = true;
        }
        int count = 0;
        for(int x:current_dice_values)
            if(x == 4)
                count += x;
        fours.setTextColor(Color.BLACK);
        fours_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        update_upper_points(count);
        reset_dices();
    }

    /**
     * fives_clicked: a method called when the fives "button" is clicked
     */
    public void fives_clicked(View view){
        TextView fives;
        FrameLayout fives_button;
        if(is_player_one_turn){
            fives = (TextView) findViewById(R.id.player_one_fives);
            fives_button = (FrameLayout) findViewById(R.id.fives_button_one);
            fives_one_clicked = true;
        } else {
            fives = (TextView) findViewById(R.id.player_two_fives);
            fives_button = (FrameLayout) findViewById(R.id.fives_button_two);
            fives_two_clicked = true;
        }
        int count = 0;
        for(int x:current_dice_values)
            if(x == 5)
                count += x;
        fives.setTextColor(Color.BLACK);
        fives_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        update_upper_points(count);
        reset_dices();
    }

    /**
     * sixes_clicked: a method called when the sixes "button" is clicked
     */
    public void sixes_clicked(View view){
        TextView sixes;
        FrameLayout sixes_button;
        if(is_player_one_turn){
            sixes = (TextView) findViewById(R.id.player_one_sixes);
            sixes_button = (FrameLayout) findViewById(R.id.sixes_button_one);
            sixes_one_clicked = true;
        } else {
            sixes = (TextView) findViewById(R.id.player_two_sixes);
            sixes_button = (FrameLayout) findViewById(R.id.sixes_button_two);
            sixes_two_clicked = true;
        }
        int count = 0;
        for(int x : current_dice_values)
            if(x == 6)
                count += x;
        sixes.setTextColor(Color.BLACK);
        sixes_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        update_upper_points(count);
        reset_dices();
    }

    /**
     * three_of_a_kind_clicked: a method called when the 3 of a kind "button" is clicked
     */
    public void three_of_a_kind_clicked(View view){
        TextView three_kind;
        FrameLayout three_kind_button;
        if(is_player_one_turn){
            three_kind = (TextView) findViewById(R.id.player_one_3_of_kind);
            three_kind_button = (FrameLayout) findViewById(R.id.three_kind_button_one);
            three_of_kind_one_clicked = true;
        } else {
            three_kind = (TextView) findViewById(R.id.player_two_3_of_kind);
            three_kind_button = (FrameLayout) findViewById(R.id.three_kind_button_two);
            three_of_kind_two_clicked = true;
        }
        int count = 0;
        if(exists_three_kind){
            for(int x : current_dice_values)
                count += x;
            update_lower_points(count);
        }
        else {
            three_kind.setText("X");
            update_total_points(0);
        }
        three_kind.setTextColor(Color.BLACK);
        three_kind_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        reset_dices();
    }

    /**
     * four_of_a_kind_clicked: a method called when the 4 of a kind "button" is clicked
     */
    public void four_of_a_kind_clicked(View view){
        TextView four_kind;
        FrameLayout four_kind_button;
        if(is_player_one_turn){
            four_kind = (TextView) findViewById(R.id.player_one_4_of_kind);
            four_kind_button = (FrameLayout) findViewById(R.id.four_kind_button_one);
            four_of_kind_one_clicked = true;
        } else {
            four_kind = (TextView) findViewById(R.id.player_two_4_of_kind);
            four_kind_button = (FrameLayout) findViewById(R.id.four_kind_button_two);
            four_of_kind_two_clicked = true;
        }
        int count = 0;
        if(exists_four_kind){
            for(int x : current_dice_values)
                count += x;
            update_lower_points(count);
        }
        else {
            four_kind.setText("X");
            update_total_points(0);
        }
        four_kind.setTextColor(Color.BLACK);
        four_kind_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        reset_dices();
    }

    /**
     * full_house_clicked: a method called when the fll house "button" is clicked
     */
    public void full_house_clicked(View view){
        TextView full_house;
        FrameLayout full_house_button;
        if(is_player_one_turn){
            full_house = (TextView) findViewById(R.id.player_one_full_house);
            full_house_button = (FrameLayout) findViewById(R.id.full_house_button_one);
            full_house_one_clicked = true;
        } else {
            full_house = (TextView) findViewById(R.id.player_two_full_house);
            full_house_button = (FrameLayout) findViewById(R.id.full_house_button_two);
            full_house_two_clicked = true;
        }
        if(exists_full_house) {
            update_lower_points(25);
        }
        else {
            full_house.setText("X");
            update_total_points(0);
        }
        full_house.setTextColor(Color.BLACK);
        full_house_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        reset_dices();
    }

    /**
     * small_straight_clicked: a method called when the small straight "button" is clicked
     */
    public void small_straight_clicked(View view){
        TextView small_straight;
        FrameLayout small_straight_button;
        if(is_player_one_turn){
            small_straight = (TextView) findViewById(R.id.player_one_sm_str);
            small_straight_button = (FrameLayout) findViewById(R.id.small_straight_button_one);
            small_straight_one_clicked = true;
        } else {
            small_straight = (TextView) findViewById(R.id.player_two_sm_str);
            small_straight_button = (FrameLayout) findViewById(R.id.small_straight_button_two);
            small_straight_two_clicked = true;
        }
        if(exists_small_straight)
            update_lower_points(30);
        else {
            small_straight.setText("X");
            update_total_points(0);
        }
        small_straight.setTextColor(Color.BLACK);
        small_straight_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        reset_dices();
    }

    /**
     * _clicked: a method called when the large straight "button" is clicked
     */
    public void large_straight_clicked(View view){
        TextView large_straight;
        FrameLayout large_straight_button;
        if(is_player_one_turn){
            large_straight = (TextView) findViewById(R.id.player_one_lg_str);
            large_straight_button = (FrameLayout) findViewById(R.id.large_straight_button_one);
            large_straight_one_clicked = true;
        } else {
            large_straight = (TextView) findViewById(R.id.player_two_lg_str);
            large_straight_button = (FrameLayout) findViewById(R.id.large_straight_button_two);
            large_straight_two_clicked = true;
        }
        if(exists_large_straight)
            update_lower_points(40);
        else {
            large_straight.setText("X");
            update_total_points(0);
        }
        large_straight.setTextColor(Color.BLACK);
        large_straight_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        reset_dices();
    }

    /**
     * yahtzee_clicked: a method called when the yahtzee "button" is clicked
     */
    public void yahtzee_clicked(View view){
        TextView yahtzee;
        FrameLayout yahtzee_button;
        if(is_player_one_turn){
            yahtzee = (TextView) findViewById(R.id.player_one_yahtzee);
            yahtzee_button = (FrameLayout) findViewById(R.id.yahtzee_button_one);
            yahtzee_one_clicked = true;
        } else {
            yahtzee = (TextView) findViewById(R.id.player_two_yahtzee);
            yahtzee_button = (FrameLayout) findViewById(R.id.yahtzee_button_two);
            yahtzee_two_clicked = true;
        }
        if(exists_yahtzee)
            update_lower_points(50);
        else {
            yahtzee.setText("X");
            update_total_points(0);
        }
        yahtzee.setTextColor(Color.BLACK);
        yahtzee_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        reset_dices();
    }

    /**
     * chance_clicked: a method called when the chance "button" is clicked
     */
    public void chance_clicked(View view){
        TextView chance;
        FrameLayout chance_button;
        if(is_player_one_turn){
            chance = (TextView) findViewById(R.id.player_one_chance);
            chance_button = (FrameLayout) findViewById(R.id.chance_button_one);
            chance_one_clicked = true;
        } else {
            chance = (TextView) findViewById(R.id.player_two_chance);
            chance_button = (FrameLayout) findViewById(R.id.chance_button_two);
            chance_two_clicked = true;
        }
        int count = 0;
        for(int x:current_dice_values)
            count += x;
        chance.setTextColor(Color.BLACK);
        chance_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                nothing();
            }
        });
        update_lower_points(count);
        reset_dices();
    }
}
