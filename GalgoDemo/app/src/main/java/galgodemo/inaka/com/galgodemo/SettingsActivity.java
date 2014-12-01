package galgodemo.inaka.com.galgodemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Arjan on 2014-11-29.
 */
public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        EditText numOfLines = (EditText) findViewById(R.id.num_lines);
        EditText textSize = (EditText) findViewById(R.id.text_size);
        Spinner backgroundColour = (Spinner) findViewById(R.id.background_colour);
        Spinner textColour = (Spinner) findViewById(R.id.text_colour);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundColour.setAdapter(adapter);
        textColour.setAdapter(adapter);
        numOfLines.setText(Integer.toString(MainActivity.getNumberOfLines()), TextView.BufferType.EDITABLE);
        textSize.setText(Integer.toString(MainActivity.getTextSize()), TextView.BufferType.EDITABLE);

        switch(MainActivity.getBackgroundColour()) {
            case Color.BLACK: backgroundColour.setSelection(0);
                break;
            case Color.WHITE: backgroundColour.setSelection(1);
                break;
            case Color.GRAY: backgroundColour.setSelection(2);
                break;
            case Color.RED: backgroundColour.setSelection(3);
                break;
            case Color.GREEN: backgroundColour.setSelection(4);
                break;
            case Color.BLUE: backgroundColour.setSelection(5);
                break;
            default: backgroundColour.setSelection(6);
                break;
        }

        switch(MainActivity.getTextColour()) {
            case Color.BLACK: textColour.setSelection(0);
                break;
            case Color.WHITE: textColour.setSelection(1);
                break;
            case Color.GRAY: textColour.setSelection(2);
                break;
            case Color.RED: textColour.setSelection(3);
                break;
            case Color.GREEN: textColour.setSelection(4);
                break;
            case Color.BLUE: textColour.setSelection(5);
                break;
            default: textColour.setSelection(6);
                break;
        }


        numOfLines.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String message = s.toString();
                if(!message.isEmpty()) MainActivity.setNumberOfLines(Integer.decode(message));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String message = s.toString();
                if(!message.isEmpty()) MainActivity.setTextSize(Integer.decode(message));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backgroundColour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                if(selected.equals("Red")) MainActivity.setBackgroundColour(Color.RED);
                else if(selected.equals("Green")) MainActivity.setBackgroundColour(Color.GREEN);
                else if(selected.equals("Blue")) MainActivity.setBackgroundColour(Color.BLUE);
                else if(selected.equals("Yellow")) MainActivity.setBackgroundColour(Color.YELLOW);
                else if(selected.equals("White")) MainActivity.setBackgroundColour(Color.WHITE);
                else if(selected.equals("Black")) MainActivity.setBackgroundColour(Color.BLACK);
                else MainActivity.setBackgroundColour(Color.GRAY);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textColour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                if(selected.equals("Red")) MainActivity.setTextColour(Color.RED);
                else if(selected.equals("Green")) MainActivity.setTextColour(Color.GREEN);
                else if(selected.equals("Blue")) MainActivity.setTextColour(Color.BLUE);
                else if(selected.equals("Yellow")) MainActivity.setTextColour(Color.YELLOW);
                else if(selected.equals("White")) MainActivity.setTextColour(Color.WHITE);
                else if(selected.equals("Black")) MainActivity.setTextColour(Color.BLACK);
                else MainActivity.setTextColour(Color.GRAY);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}