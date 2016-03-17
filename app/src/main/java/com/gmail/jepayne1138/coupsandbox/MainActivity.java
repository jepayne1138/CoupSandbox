package com.gmail.jepayne1138.coupsandbox;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.gmail.jepayne1138.coupsandbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    final Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Intent intent = getIntent();
        // Create the game instance
        game.setCurrentPlayer(game.addPlayer(intent.getStringExtra(LauncherActivity.CUR_PLAYER_SCREENNAME)));
        game.addPlayer("Casey");
        game.addPlayer("Caitlin");
        game.addPlayer("Adrian");
        game.addPlayer("Ian");

        // Set the current user to the data model in the binding
        binding.setCurPlayer(game.getCurrentPlayer());

        // Initialize the player list view
        final ListView playerListView = (ListView) findViewById(R.id.listPlayers);
        final PlayerListAdapter playerListAdapter = new PlayerListAdapter(game, this);
        playerListView.setAdapter(playerListAdapter);
    }

    /*
     * Button callback
     */
    public void buttonCoinUpOnClick(View v) {
//        Log.d("DEBUG", "buttonCoinUp clicked:  buttonCoinUpOnClick() called");
        game.getCurrentPlayer().incrementCoins();
//        Log.d("DEBUG", "cuPlayer coins: " + Integer.toString(game.getCurrentPlayer().getCoins()));
    }

    public void buttonCoinDownOnClick(View v) {
//        Log.d("DEBUG", "buttonCoinDown clicked:  buttonCoinDownOnClick() called");
        game.getCurrentPlayer().decrementCoins();
//        Log.d("DEBUG", "cuPlayer coins: " + Integer.toString(game.getCurrentPlayer().getCoins()));
    }


}
