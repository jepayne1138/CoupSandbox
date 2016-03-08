package jepayne1138.gmail.com.coupsandbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the game instance
        final Game game = new Game();
        game.setCurrentPlayer(game.addPlayer("Jim"));
        game.addPlayer("Casey");
        game.addPlayer("Caitlin");
        game.addPlayer("Adrian");
        game.addPlayer("Ian");

        // Initialize the player list view
        final ListView playerListView = (ListView) findViewById(R.id.listPlayers);
        final PlayerListAdapter playerListAdapter = new PlayerListAdapter(game, this);
        playerListView.setAdapter(playerListAdapter);
    }
}
