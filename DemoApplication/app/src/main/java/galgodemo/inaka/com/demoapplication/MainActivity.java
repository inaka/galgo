package galgodemo.inaka.com.demoapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.inaka.galgo.Galgo;
import com.inaka.galgo.GalgoOptions;

/**
 * Created by Arjan on 2014-11-27.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add some customization to the log messages
        GalgoOptions options = new GalgoOptions.Builder()
                .numberOfLines(15)
                .backgroundColor(Color.parseColor("#D9d6d6d6"))
                .textColor(Color.BLACK)
                .textSize(15)
                .logToTextFile(true)
                .build();
        Galgo.enable(this, options);

        Galgo.log("I am a log message");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Galgo.log("I am a log message");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onDestroy() {
        super.onDestroy();

        // always call disable to avoid memory leaks
        Galgo.disable(this);
    }
}
