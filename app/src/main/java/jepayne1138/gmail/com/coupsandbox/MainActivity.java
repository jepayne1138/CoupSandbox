package jepayne1138.gmail.com.coupsandbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the player list view
        final ListView playerListView = (ListView) findViewById(R.id.listPlayers);
    }
}
