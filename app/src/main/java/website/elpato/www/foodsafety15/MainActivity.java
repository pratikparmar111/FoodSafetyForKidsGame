package website.elpato.www.foodsafety15;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.concurrent.ThreadLocalRandom;


//https://stackoverflow.com/questions/2591036/how-to-hide-the-title-bar-for-an-activity-in-xml-with-existing-custom-theme
public class MainActivity extends Activity {

    public static final String PREFS_GAME ="com.elapto.website.FoodStorageForKids";
    public static final String GAME_SCORE= "GameScore";

    ImageButton speaker_icon1;
    Intent svc;
    Context context;
    String TAG ="start";

    MediaPlayer mediaPlayer;
//    Utility u = new Utility();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for background sound
        Intent svc =new Intent(this, BackgroundSoundService.class);
        startService(svc);

       //intent = new Intent(getApplicationContext(), Utility.class);

        ImageView playimagee = (ImageView) findViewById(R.id.playimagee);
        playimagee.setOnClickListener(playimageeHandler1);

        setFirstTime();
        //put animation on play button
        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
        animation1.setDuration(800);
        animation1.setRepeatCount(Animation.INFINITE);
        playimagee.startAnimation(animation1);


      /*  speaker_icon1 = (ImageButton) findViewById(R.id.speaker_icon);
        speaker_icon1.setOnClickListener(speaker_icon1Handler);
        setSoundIcon(speaker_icon1);*/

    }


    public void didTapButton(View view) {
        Button button = (Button)findViewById(R.id.button);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
    }
    ImageView.OnClickListener playimageeHandler1 = new ImageView.OnClickListener() {

        boolean visible ;
        public void onClick(View v) {
            Intent l1 = new Intent(getBaseContext(), levelScreenActivity.class);
            startActivity(l1);
            overridePendingTransition(R.anim.slide_down_in,R.anim.fade_out);
        }
    };
   /* ImageButton.OnClickListener speaker_icon1Handler = new ImageButton.OnClickListener() {

        boolean visible ;
        public void onClick(View v) {

            BackgroundSoundToggle(speaker_icon1);
        }
    };*/


    @Override
    protected void onResume() {
        super.onResume();
        // play your music here.
        Log.d(TAG,"onResume: called");

    }
   /* public void onBackPressed() {
        MediaPlayer mp = new MediaPlayer();
        mp.stop();
    }*/
    @Override
    protected void onPause() {

        Log.d(TAG,"onpause: called");
        stopService(new Intent(MainActivity.this,BackgroundSoundService.class));

        BackgroundSoundService bs = new BackgroundSoundService();
        bs.onPause();

        super.onPause();

    }
    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy: called");
        super.onDestroy();
        stopService(new Intent(MainActivity.this,BackgroundSoundService.class));
    }

    public void setValueSharedPref(String name ,String Value)
    {

        //======== Code to save data ===================
        SharedPreferences sharedPreferences = this.getSharedPreferences("GameDetails", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, Value);
        editor.commit();
    }
    public void setFirstTime()
    {
        if(getValueSharedPref("SpeackerON") != null)
        {
            setValueSharedPref("SpeackerON","0");
        }
        if(getValueSharedPref("Level1Score") != null)
        {
            setValueSharedPref("Level1Score","0");
        }
        if(getValueSharedPref("Level2Score") != null)
        {
            setValueSharedPref("Level2Score","0");
        }
        if(getValueSharedPref("Level3Score") != null)
        {
            setValueSharedPref("Level3Score","0");
        }
        if(getValueSharedPref("LevelTwoUnlocked") != null)
        {
            setValueSharedPref("LevelTwoUnlocked","0");
        }
        if(getValueSharedPref("LevelThreeUnlocked") != null)
        {
            setValueSharedPref("LevelThreeUnlocked","0");
        }
    }
    public String getValueSharedPref(String name)
    {
        //========= Code to get saved/ retrieve data ==============
        try{
            SharedPreferences sharedPreferences = context.getSharedPreferences("GameDetails", Context.MODE_PRIVATE);
            return sharedPreferences.getString(name, "");
        }
        catch (Exception e)
        {
            return "";
        }

    }
    //toi get score from strored
    public String getValueScore(String name)
    {
        return getValueSharedPref(name);
    }

    //http://abhiandroid.com/programming/shared-preference


    //toogle sound icon and music
    public void BackgroundSoundToggle(ImageButton speaker_icon1)
    {
        Log.d(TAG, "SpeackerON" +getValueSharedPref("SpeackerON"));
        //0 false
        //1 ==true
        if(getValueSharedPref("SpeackerON") == "0")
        {

            speaker_icon1.setImageResource(R.drawable.speaker_off);
            setValueSharedPref("SpeackerON" ,"1");
            pauseSong();
        }
        else if(getValueSharedPref("SpeackerON") == "1")
        {
            speaker_icon1.setImageResource(R.drawable.speaker_icon);
            setValueSharedPref("SpeackerON" ,"0");
            pauseSong();
        }
    }
    public void setSoundIcon(ImageButton speaker_icon1)
    {
        if(getValueSharedPref("SpeackerON") == "1")
        {
            speaker_icon1.setImageResource(R.drawable.speaker_icon);
        }
        if(getValueSharedPref("SpeackerON") == "0")
        {
            speaker_icon1.setImageResource(R.drawable.speaker_off);
        }
    }
    public void scoreUpdate(String ScoreLevel, TextView Score)
    {
        int SCORE = Integer.parseInt(getValueScore(ScoreLevel).toString());
        SCORE=SCORE+1;
        setValueSharedPref(ScoreLevel,SCORE+"");
        Score.setText("Score : " + SCORE);

    }
    public void playSong()
    {
         mediaPlayer.start();
    }
    public void pauseSong()
    {
        // mediaPlayer.pause();
    }

    public void stopSong(View v)
    {

        //mediaPlayer.stop();
    }
}
