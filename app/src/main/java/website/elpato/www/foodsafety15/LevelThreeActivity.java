package website.elpato.www.foodsafety15;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Map;

public class LevelThreeActivity extends Activity {

  //  private ImageView food;
    private ImageView deepFridge;
    private ImageView refrigrator;
    private ImageView cabinet;
    private ImageView table;
    private ImageView dustbin;
    private ImageView hand;
    private String Level="1";
    Context mContext;

    MediaPlayer mediaPlayer ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);

//make media file
        mediaPlayer = MediaPlayer.create(this, R.raw.you_are_right);
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        Utility u = new Utility();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String outlet_no= bundle.getString("level");

        int[] datafood = u.getRandomNumber(8);
        for(int i=0;i< 4;i++) {

            Level = outlet_no;
           // Log.d(outlet_no, "--" +outlet_no + "--" +Level );
            if (outlet_no.equals("one"))
            {
                Log.d(outlet_no, "one--==--" +outlet_no + "--" +Level );
                Map<Integer, String> words =  u.GetLevelOneFood();
                //adding images and actions ,listener dynamically
                addImageView(linearLayout1, words.get(datafood[i]),i );
            }
            else if (outlet_no.equals("two"))
            {
                Map<Integer, String> words =  u.GetLevelTwoFood();
                //adding images and actions ,listener dynamically
                addImageView(linearLayout1, words.get(datafood[i]),i );
            }
            else
            {
                Map<Integer, String> words =  u.GetLevelThreeFood();
                //adding images and actions ,listener dynamically
                addImageView(linearLayout1, words.get(datafood[i]),i );
            }

        }
        deepFridge = (ImageView) findViewById(R.id.deepfridge);
        deepFridge.setTag("deepFridge");
        deepFridge.setOnDragListener(new LevelThreeActivity.StorageDragListener(
                R.drawable.deepfridge_top,
                R.drawable.fridge_1_2_top));

        refrigrator = (ImageView) findViewById(R.id.refrigrator);
        refrigrator.setTag("refrigrator");
        refrigrator.setOnDragListener(new LevelThreeActivity.StorageDragListener(
                R.drawable.refrigrator_opendoor_bootom,
                R.drawable.fridge_1_2_bottom));


//cabinet
       cabinet = (ImageView) findViewById(R.id.cabinet);
        cabinet.setTag("cabinet");

        cabinet.setOnDragListener(new LevelThreeActivity.StorageDragListener(
                R.drawable.uppercabinet1_2_open,
                R.drawable.uppercabinet1_2));
//table
        table = (ImageView) findViewById(R.id.table);
        table.setTag("table");

        table.setOnDragListener(new LevelThreeActivity.StorageDragListener(
                R.drawable.table,
                R.drawable.table));
//dustbin
        dustbin = (ImageView) findViewById(R.id.dustbin1_2);
        dustbin.setTag("dustbin");
//to hide dustbin in View
        if((outlet_no.equals("one")) || (outlet_no.equals("two") ))
        {
            dustbin.setVisibility(View.INVISIBLE);
        }

        dustbin.setOnDragListener(new LevelThreeActivity.StorageDragListener(
                R.drawable.dustbin_fullopen,
                R.drawable.dustbin1_2));


        Toast.makeText(getApplicationContext(), "Put Food in Storage", Toast.LENGTH_SHORT).show();

    }
    private void addImageView(LinearLayout layout,String mDrawableName,int i){
        //https://stackoverflow.com/questions/7493287/android-how-do-i-get-string-from-resources-using-its-name
        ImageView imageView = new ImageView(this);
        int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
        imageView.setImageResource(resID);
        imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,150));
        imageView.setMaxHeight(130);
        imageView.setMaxWidth(130);
        imageView.setTag(mDrawableName);
        imageView.bringToFront();
        imageView.setId(i); //its has to int so passing as it is
        layout.addView(imageView);
        ImageView food = (ImageView) findViewById(i);
        food.setTag(mDrawableName);


        food.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
    }

    public class StorageDragListener implements View.OnDragListener {

        private static final String TAG = "StorageDragListener";

        private int enterShape;
        private int normalShape;
        private boolean hit;

        public StorageDragListener(int enterShape, int normalShape) {
            this.enterShape = enterShape;
            this.normalShape = normalShape;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            final ImageView containerView = (ImageView) v;
            final ImageView draggedView = (ImageView) event.getLocalState();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:

                    Log.d(TAG, "onDrag: ACTION_DRAG_STARTED");
                    if(containerView.getId() == R.id.deepfridge ) {
                        if (draggedView.getTag() == "ice_cubes" ||
                                draggedView.getTag() == "frozen_veg" ||
                                draggedView.getTag() == "ice_cream" ||
                                draggedView.getTag() == "meat"
                                ) {
                            Toast.makeText(getApplicationContext(), "Put in deep fridge", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (containerView.getId() == R.id.refrigrator)
                    {
                        if(     draggedView.getTag()=="cola"||
                                draggedView.getTag()=="milk" ||
                                draggedView.getTag()=="ccmber" ||
                                draggedView.getTag()=="corn" ||
                                draggedView.getTag()=="grapes" ||
                                draggedView.getTag()=="kiwi" ||
                                draggedView.getTag()=="eggplant" ||
                                draggedView.getTag()=="cheese" ||
                                draggedView.getTag()=="juice"
                                ) {
                            Toast.makeText(getApplicationContext(), "Put in Refrigerator", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else  if(containerView.getId() == R.id.dustbin1_2 ) {
                        if (draggedView.getTag() == "bad_bread") {
                            Toast.makeText(getApplicationContext(), "Put in Trash", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(containerView.getId() == R.id.cabinet ) {
                        if (draggedView.getTag() == "flour" ||
                                draggedView.getTag() == "rice" ||
                                draggedView.getTag() == "oil" ||
                                draggedView.getTag() == "garlic" ||
                                draggedView.getTag() == "potato" ||
                                draggedView.getTag() == "onion" ||
                                draggedView.getTag() == "tin_food" ||
                                draggedView.getTag() == "cookies"
                                ) {
                            Toast.makeText(getApplicationContext(), "Put in cabinet", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else if(containerView.getId() == R.id.table ) {
                        if (draggedView.getTag() == "apple" ||
                                draggedView.getTag() == "banana") {
                            Toast.makeText(getApplicationContext(), "Put in bowl on the table", Toast.LENGTH_SHORT).show();
                        }
                    }
                    hit = false;
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d(TAG, "onDrag: ACTION_DRAG_ENTERED");
                    containerView.setImageResource(enterShape);
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d(TAG, "onDrag: ACTION_DRAG_EXITED");
                    containerView.setImageResource(normalShape);
                    return true;
                case DragEvent.ACTION_DROP:
                    Log.d(TAG, "onDrag: ACTION_DROP");

                    if(containerView.getId() == R.id.deepfridge )
                    {
                        if(     draggedView.getTag()=="ice_cubes" ||
                                draggedView.getTag()=="frozen_veg" ||
                                draggedView.getTag()=="ice_cream" ||
                                draggedView.getTag()=="meat"
                                )

                        {
                            hit = true;
                            draggedView.post(new Runnable() {
                                @Override
                                public void run() {
                                    //hiding food item
                                    draggedView.setVisibility(View.INVISIBLE);

                                }
                            });

                            Log.d(TAG, "deepfridge ");
                            mediaPlayer.start();
                            return true;
                        }
                    }else
                    if (containerView.getId() == R.id.refrigrator)
                    {
                        if(     draggedView.getTag()=="cola"||
                                draggedView.getTag()=="milk" ||
                                draggedView.getTag()=="ccmber" ||
                                draggedView.getTag()=="corn" ||
                                draggedView.getTag()=="grapes" ||
                                draggedView.getTag()=="kiwi" ||
                                draggedView.getTag()=="eggplant" ||
                                draggedView.getTag()=="cheese" ||
                                draggedView.getTag()=="juice"
                                )
                        {
                            hit = true;
                            draggedView.post(new Runnable() {
                                @Override
                                public void run() {
                                    //hiding food item
                                    draggedView.setVisibility(View.INVISIBLE);

                                }
                            });
                            Log.d(TAG, "refrigrator ");
                            mediaPlayer.start();
                            return true;
                        }
                    }
                    else
                    if(containerView.getId() == R.id.dustbin1_2 )
                    {
                        if( draggedView.getTag()=="bad_bread" )
                        {
                            hit = true;
                            draggedView.post(new Runnable() {
                                @Override
                                public void run() {
                                    //hiding food item
                                    draggedView.setVisibility(View.INVISIBLE);

                                }
                            });
                            Log.d(TAG, "dustbin ");
                            mediaPlayer.start();
                            return true;
                        }
                    }
                    else if(containerView.getId() == R.id.cabinet )
                    {
                        if(  draggedView.getTag()=="flour" ||
                                draggedView.getTag()=="rice" ||
                                draggedView.getTag()=="oil" ||
                                draggedView.getTag()=="garlic" ||
                                draggedView.getTag()=="potato" ||
                                draggedView.getTag()=="onion"||
                                draggedView.getTag()=="tin_food"||
                                draggedView.getTag()=="cookies"
                                )
                        {

                            hit = true;
                            draggedView.post(new Runnable() {
                                @Override
                                public void run() {
                                    //hiding food item
                                    draggedView.setVisibility(View.INVISIBLE);

                                }
                            });
                            Log.d(TAG, "cabinet test");
                            mediaPlayer.start();
                            return true;
                        }

                    }
                    else if(containerView.getId() == R.id.table )
                    {
                        if( draggedView.getTag()=="apple"||
                                draggedView.getTag()=="banana")
                        {
                            hit = true;
                            draggedView.post(new Runnable() {
                                @Override
                                public void run() {
                                    //hiding food item
                                    draggedView.setVisibility(View.INVISIBLE);

                                }
                            });
                            Log.d(TAG, "table test");
                            mediaPlayer.start();
                            return true;
                        }


                    }
                    else
                        {


                        return false;
                    }


                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d(TAG, "onDrag: ACTION_DRAG_ENDED");
                    containerView.setImageResource(normalShape);
                    v.setVisibility(View.VISIBLE);
                    if(containerView.getId() == R.id.deepfridge ) {
                        if (draggedView.getTag() == "ice_cubes" ||
                                draggedView.getTag() == "frozen_veg" ||
                                draggedView.getTag() == "ice_cream" ||
                                draggedView.getTag() == "meat"
                                )

                        {
                            if (!hit) {
                                draggedView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        draggedView.setVisibility(View.VISIBLE);
                                    }
                                });
                                Log.d(TAG, "deepfridge");
                                //https://stackoverflow.com/questions/33086417/mediaplayer-setdatasourcestring-not-working-with-local-files

                             /* if(mediaPlayer != null)
                              {
                                  mediaPlayer.release();
                              }
                                mediaPlayer.create(getApplicationContext(), getResources().getIdentifier("thats_wrong","raw",getPackageName()));
                                mediaPlayer.start();*/



                            }
                        }
                    }
                    else if (containerView.getId() == R.id.refrigrator)
                    {
                        if(     draggedView.getTag()=="cola"||
                                draggedView.getTag()=="milk" ||
                                draggedView.getTag()=="ccmber" ||
                                draggedView.getTag()=="corn" ||
                                draggedView.getTag()=="grapes" ||
                                draggedView.getTag()=="kiwi" ||
                                draggedView.getTag()=="eggplant" ||
                                draggedView.getTag()=="cheese" ||
                                draggedView.getTag()=="juice"
                                )
                        {
                            if (!hit) {
                                draggedView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        draggedView.setVisibility(View.VISIBLE);
                                    }
                                });
                                Log.d(TAG, "refrigrator");
                             /*   if(mediaPlayer != null)
                                {
                                    mediaPlayer.release();
                                }

                                    mediaPlayer.create(getApplicationContext(), getResources().getIdentifier("thats_wrong","raw",getPackageName()));
                                    mediaPlayer.start();*/


                            }
                        }
                    }
                    else if(containerView.getId() == R.id.dustbin1_2 ) {
                            if (draggedView.getTag() == "bad_bread") {
                                if (!hit) {
                                    draggedView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            draggedView.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    Log.d(TAG, "bad_bread" );

                                }
                            }
                        }
                        else if(containerView.getId() == R.id.cabinet ) {
                            if (draggedView.getTag() == "flour" ||
                                    draggedView.getTag() == "rice" ||
                                    draggedView.getTag() == "oil" ||
                                    draggedView.getTag() == "garlic" ||
                                    draggedView.getTag() == "potato" ||
                                    draggedView.getTag() == "onion" ||
                                    draggedView.getTag() == "tin_food" ||
                                    draggedView.getTag() == "cookies"
                                    ) {
                                if (!hit) {
                                    draggedView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            draggedView.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    Log.d(TAG, "cabinet" );

                                }

                            }
                        }
                    else if(containerView.getId() == R.id.table ) {
                            if (draggedView.getTag() == "apple" ||
                                    draggedView.getTag() == "banana") {
                                if (!hit) {
                                    draggedView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            draggedView.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    Log.d(TAG, "table" );
                                }

                            }
                        }
                        else
                        {
                            if (!hit) {
                                draggedView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        draggedView.setVisibility(View.VISIBLE);

                                    }
                                });
                                Log.d(TAG, "ElseEND" +containerView.getId() +"  ---" +  draggedView.getTag());
                            }

                        }
                    return true;
                default:
                    return true;
            }
        }
    }
}
//for dispaying Toast msg https://stackoverflow.com/questions/3500197/how-to-display-toast-in-android
//for adding images dynamically and form java code on run time
// https://stackoverflow.com/questions/15153215/android-dynamically-add-an-image-view-to-a-scrollview
//http://android-coding.blogspot.co.nz/2012/10/insert-imageview-dynamically-using-java.html
