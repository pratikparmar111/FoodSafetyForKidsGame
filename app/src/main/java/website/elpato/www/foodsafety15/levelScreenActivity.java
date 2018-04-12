package website.elpato.www.foodsafety15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class levelScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_screen);
//level 1 button
        Button cardNumbers1 = (Button) findViewById(R.id.button);
        cardNumbers1.setOnClickListener(CardNumbersHandler1);
//level 2  button
        Button cardNumbers2 = (Button) findViewById(R.id.button2);
        cardNumbers2.setOnClickListener(CardNumbersHandler2);
//level 3
        Button cardNumbers3 = (Button) findViewById(R.id.button3);
        cardNumbers3.setOnClickListener(CardNumbersHandler3);
    }
    Button.OnClickListener CardNumbersHandler1 = new Button.OnClickListener() {

        boolean visible ;
        public void onClick(View v) {
            Intent l1 = new Intent(getBaseContext(), LevelThreeActivity.class);
            l1.putExtra("level", "one");
            startActivity(l1);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
    };
    Button.OnClickListener CardNumbersHandler2 = new Button.OnClickListener() {

        boolean visible ;
        public void onClick(View v) {
            Intent l2 = new Intent(getBaseContext(), LevelThreeActivity.class);
            l2.putExtra("level", "two");
            startActivity(l2);
            overridePendingTransition(R.anim.slide_down_in,R.anim.fade_out);
        }
    };
    Button.OnClickListener CardNumbersHandler3 = new Button.OnClickListener() {

        boolean visible ;
        public void onClick(View v) {
            Intent l3 = new Intent(getBaseContext(), LevelThreeActivity.class);
            l3.putExtra("level", "three");
            startActivity(l3);
            overridePendingTransition(R.anim.slide_down_in,R.anim.fade_out);
        }
    };
}
