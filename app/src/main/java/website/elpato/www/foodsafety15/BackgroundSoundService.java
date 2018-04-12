package website.elpato.www.foodsafety15;

/**
 * Created by Dell on 4/6/2018.
 */

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
public class BackgroundSoundService {
}
 https://stackoverflow.com/questions/8209858/android-background-music-service/8209975#8209975
 */
public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this,R.raw.bgmusic); // R.raw.idil);
        player.setLooping(true); // Set looping
        player.setVolume(1,1);

    }
    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {

        //player.release();
      //  player = null;
       // player.setVolume(0,0);
       // player.stop();
      //  player.release();
    }
    public void onPause() {
        Log.d(TAG,"onpause:background called");
      //  player.release();
      // player = null;
       // player.stop();
      //  player.release();

    }
public void onResume()
{
  //  player = MediaPlayer.create(this,R.raw.bgmusic); // R.raw.idil);
  //  player.setLooping(true); // Set looping
  //  player.setVolume(10,10);
}
    @Override
    public void onLowMemory() {

    }
}