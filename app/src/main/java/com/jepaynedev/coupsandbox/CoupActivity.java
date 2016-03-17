package com.jepaynedev.coupsandbox;

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

        game = new GameManager();

        // Create the game instance
        game.setCurrentPlayer(game.addPlayer("Jim"));
        game.addPlayer("Casey");
        game.addPlayer("Caitlin");
        game.addPlayer("Adrian");
        game.addPlayer("Ian");

        GameFragment gameFragment = new GameFragment();

        fragmentManager.beginTransaction().add(R.id.fragment_container, gameFragment).commit();
    }
}
