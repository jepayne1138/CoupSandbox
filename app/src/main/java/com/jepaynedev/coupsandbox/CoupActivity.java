package com.jepaynedev.coupsandbox;

import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CoupActivity extends AppCompatActivity {

    public GameManager game;
    final private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the game instance
        game = new GameManager();

        fragmentManager.beginTransaction().add(R.id.fragment_container, new LauncherFragment()).commit();
    }
}
