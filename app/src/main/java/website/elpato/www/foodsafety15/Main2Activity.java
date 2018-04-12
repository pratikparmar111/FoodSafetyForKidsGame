package website.elpato.www.foodsafety15;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


//This is just demo activity to test drag drop
public class Main2Activity extends Activity {

    private ImageView food;
    private ImageView storage;
    private ImageView hand;
    Context mContext;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setFullScreen();
        setContentView(R.layout.activity_main2);
        food = (ImageView) findViewById(R.id.food);
        food.setTag("food");
        storage = (ImageView) findViewById(R.id.storage);
        storage.setTag("storage");
        hand = (ImageView) findViewById(R.id.hand);
        hand.setTag("hand");
        hand.setVisibility(View.INVISIBLE);
        storage.setOnDragListener(new StorageDragListener(

                R.drawable.deepfridge_top,
                R.drawable.fridge_1_2));


        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // food.setVisibility(View.VISIBLE);

            }
        });

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
                    hit = true;
                    draggedView.post(new Runnable() {
                        @Override
                        public void run() {
                            //hiding food item
                            draggedView.setVisibility(View.INVISIBLE);
                            Utility u = new Utility();
                           // u.playsong();


                        }
                    });

                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d(TAG, "onDrag: ACTION_DRAG_ENDED");
                    containerView.setImageResource(normalShape);
                    v.setVisibility(View.VISIBLE);
                    if (!hit) {
                        draggedView.post(new Runnable() {
                            @Override
                            public void run() {
                                draggedView.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    return true;
                default:
                    return true;
            }
        }
    }


}