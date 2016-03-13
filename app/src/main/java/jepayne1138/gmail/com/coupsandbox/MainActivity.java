package jepayne1138.gmail.com.coupsandbox;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import jepayne1138.gmail.com.coupsandbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    final Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Create the game instance
        game.setCurrentPlayer(game.addPlayer("Jim"));
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
