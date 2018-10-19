package com.example.ayesha.blood_donation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ayesha on 25-02-2016.
 */
public class Display extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String username = getIntent().getExtras().getString("Username");
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.append(username);

    }

    public void OnDonateClick(View v) {
        if (v.getId() == R.id.button2) {
            Intent i = new Intent(this, Donate.class);
            startActivity(i);
        }

    }


}
