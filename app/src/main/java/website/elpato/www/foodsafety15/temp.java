package website.elpato.www.foodsafety15;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Dell on 4/11/2018.
 */

public class temp
{


    //In Utiltys file

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

  




















}
