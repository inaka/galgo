package galgodemo.inaka.com.demoapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.inaka.galgo.Galgo;
import com.inaka.galgo.GalgoOptions;

/**
 * Created by Arjan on 2014-11-27.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EditText numOfLines = (EditText) findViewById(R.id.num_of_lines);
        EditText setTextSize = (EditText) findViewById(R.id.set_text_size);
        CheckBox writeToFile = (CheckBox) findViewById(R.id.write_to_file);

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
