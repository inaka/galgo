package galgodemo.inaka.com.galgodemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.inaka.galgo.Galgo;
import com.inaka.galgo.GalgoOptions;


public class MainActivity extends Activity {

    private static GalgoOptions options;
    private static int numberOfLines = 15;
    private static int backgroundColour = Color.parseColor("#D9d6d6d6");
    private static int textColour = Color.BLACK;
    private static int textSize = 15;
    private static boolean changed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        options = new GalgoOptions.Builder()
                .numberOfLines(numberOfLines)
                .backgroundColor(backgroundColour)
                .textColor(textColour)
                .textSize(textSize)
                .build();


        Button button = (Button) findViewById(R.id.send_log);
        button.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               EditText message = (EditText) findViewById(R.id.log_message);
               String log = message.getText().toString();
               if(log.length() >= 0) {
                   Galgo.log(log);
                   message.setText("");
               }
               return;
           }
        });

        Galgo.log("Application Started");

        Galgo.enable(this, options);
    }

    public void setOptions() {
        Galgo.disable(this);
        this.options = new GalgoOptions.Builder()
                .numberOfLines(numberOfLines)
                .backgroundColor(backgroundColour)
                .textColor(textColour)
                .textSize(textSize)
                .build();
        Galgo.enable(this, options);
    }

    public static void setNumberOfLines(int numberOfLines1) {
        numberOfLines = numberOfLines1;
        changed = true;
    }

    public static void setBackgroundColour(int backgroundColour1) {
        backgroundColour = backgroundColour1;
        changed = true;
    }

    public static void setTextColour(int textColour1) {
        textColour = textColour1;
        changed = true;
    }

    public static void setTextSize(int textSize1) {
        textSize = textSize1;
        changed = true;
    }

    public static int getNumberOfLines() {
        return numberOfLines;
    }

    public static int getTextSize() {
        return textSize;
    }

    public static int getTextColour() {
        return textColour;
    }

    public static int getBackgroundColour() {
        return backgroundColour;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(changed) {
            options =  new GalgoOptions.Builder()
                    .numberOfLines(numberOfLines)
                    .backgroundColor(backgroundColour)
                    .textColor(textColour)
                    .textSize(textSize)
                    .build();
            Galgo.disable(this);
            Galgo.enable(this, options);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Galgo.log("Adding items to the action bar");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Galgo.log("Settings Selected");
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Galgo.disable(this);
    }
}
