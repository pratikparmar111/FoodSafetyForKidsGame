package website.elpato.www.foodsafety15;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static website.elpato.www.foodsafety15.R.raw.you_are_right;

/**
 * Created by Dell on 4/8/2018.
 */

public class Utility  {
    //sound on off toggle

   // private boolean SpeackerON =true;
    //score for diffrent level
  //  private int Score=0;
    private int Level1Score=0;
    private int Level2Score=0;
    private int Level3Score=0;
//for checking level are finished or not
    private boolean LevelTwoUnlocked =false;
    private boolean LevelThreeUnlocked =false;


    public int[] getRandomNumber(int upto)
    {
        int[] numbers = new int[upto];
        for ( int i = 1; i <=upto; i++)
        {
            numbers[i-1] = i;
        }
        shuffleArray(numbers);

        return numbers;
    }
    //Reference:// Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd =  new Random();//ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public Map<Integer, String> GetLevelOneFood() {
        Map<Integer, String> words = new LinkedHashMap<>();
        words.put(1,"apple");
        words.put(2,"cookies");
        words.put(3,"juice");
        words.put(4,"ice_cream");
        words.put(5,"meat");
        words.put(6,"banana");
        words.put(7,"tin_food");
        words.put(8,"oil");
        return words;
    }
    public Map<Integer, String> GetLevelTwoFood() {
        Map<Integer, String> words = new LinkedHashMap<>();
        words.put(1,"grapes");
        words.put(2,"kiwi");
        words.put(3,"eggplant");
        words.put(4,"frozen_veg");
        words.put(5,"cheese");
        words.put(6,"garlic");
        words.put(7,"potato");
        words.put(8,"onion");
        return words;
    }
    public Map<Integer, String> GetLevelThreeFood() {
        Map<Integer, String> words = new LinkedHashMap<>();
        words.put(1,"cola");
        words.put(2,"milk");
        words.put(3,"ccmber");
        words.put(4,"ice_cubes");
        words.put(5,"corn");
        words.put(6,"bad_bread");
        words.put(7,"flour");
        words.put(8,"rice");


        return words;
    }

    /*public void showHint(String name, ImageButton deepfridgeHint, ImageButton refrigeratorHint, ImageButton cabinetHint ,ImageButton trashHint)
    {
        deepfridgeHint.setVisibility(View.INVISIBLE);
        refrigeratorHint.setVisibility(View.INVISIBLE);
        cabinetHint.setVisibility(View.INVISIBLE);
        trashHint.setVisibility(View.INVISIBLE);

        if (name == "ice_cream" || name == "meat" )
        {
            //show hint deep Fridge
            deepfridgeHint.setVisibility(View.VISIBLE);
        }
        else  if (name == "apple" || name =="juice")
        {
            //show hint Refrigerator
            refrigeratorHint.setVisibility(View.VISIBLE);
        }
        else  if (name == "cookies")
        {
            //show hint cabinet
            cabinetHint.setVisibility(View.VISIBLE);
        }
        else  if (name == "bad_bread")
        {
            //show hint trash
            trashHint.setVisibility(View.VISIBLE);
        }
    }*/


}
//Reference: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array/1520212