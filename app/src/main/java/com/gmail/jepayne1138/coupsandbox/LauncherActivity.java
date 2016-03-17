package com.gmail.jepayne1138.coupsandbox;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.gmail.jepayne1138.coupsandbox.databinding.ActivityLauncherBinding;

public class LauncherActivity extends AppCompatActivity {

    public final static String CUR_PLAYER_SCREENNAME = "com.gmail.jepayne1138.CUR_PLAYER_SCREENNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLauncherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_launcher);
    }

    public void buttonStartOnClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText inputScreenName = (EditText) findViewById(R.id.inputScreenName);
        intent.putExtra(CUR_PLAYER_SCREENNAME, inputScreenName.getText().toString());
        startActivity(intent);
    }


}
