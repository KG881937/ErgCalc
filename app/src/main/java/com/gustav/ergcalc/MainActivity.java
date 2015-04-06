package com.gustav.ergcalc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    private NumberPicker npMin,npSec1,npSec2,npDec;
    private TextView textView2k,textView6k,textView10k;
    double currMin,currSec,currTenth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();

        npMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                currMin = i2;
                setTotal();
            }
        });

        npSec1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                currSec = (i2 + npSec2.getValue());
                setTotal();
            }
        });

        npSec2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                currSec = (i2 + npSec1.getValue());
                setTotal();
            }
        });

        npDec.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                currTenth = i2;
                setTotal();
            }
        });
    }

    private void setUp() {
        this.textView2k = (TextView) findViewById(R.id.textView2k);
        this.textView6k = (TextView) findViewById(R.id.textView6k);
        this.textView10k = (TextView) findViewById(R.id.textView10k);


        npMin = (NumberPicker) findViewById(R.id.npMin);
        npSec1 = (NumberPicker) findViewById(R.id.npSec1);
        npSec2 = (NumberPicker) findViewById(R.id.npSec2);
        npDec = (NumberPicker) findViewById(R.id.npDec);

        npMin.setMaxValue(9);
        npMin.setMinValue(0);
        npMin.setValue(1);
        npSec1.setMaxValue(5);
        npSec1.setMinValue(0);
        npSec1.setValue(4);
        npSec2.setMaxValue(9);
        npSec2.setMinValue(0);
        npSec2.setValue(5);
        npDec.setMaxValue(9);
        npDec.setMinValue(0);
    }

    private void setTotal()
    {
        DecimalFormat dec = new DecimalFormat("00.0");

        currMin = (npMin.getValue() * 60);
        currTenth = npDec.getValue();
        currSec = (10* npSec1.getValue() + npSec2.getValue() + currTenth/10);

        double currTotal = (currMin + currSec);
        double total6k = currTotal;
        double total10k = currTotal;

        //2k
        currTotal = ((currTotal /60) * 4);
        double secs = currTotal % 1;
        currTotal = currTotal - secs;
        secs = secs * 60;
        this.textView2k.setText(Integer.toString((int)currTotal) + ":" + (dec.format(secs)));

        //6k
        total6k = ((total6k /60) * 12);
        secs = total6k % 1;
        total6k = total6k - secs;
        secs = secs * 60;
        this.textView6k.setText(Integer.toString((int)total6k) + ":" + (dec.format(secs)));

        //10k
        total10k = ((total10k /60) * 20);
        secs = total10k % 1;
        total10k = total10k - secs;
        secs = secs * 60;
        this.textView10k.setText(Integer.toString((int)total10k) + ":" + (dec.format(secs)));
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
