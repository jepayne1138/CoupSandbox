package jepayne1138.gmail.com.coupsandbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Jim on 3/7/2016.
 */
public class PlayerListAdapter extends BaseAdapter {

    final private Game game;
    final private LayoutInflater inflater;

    PlayerListAdapter(Game game, Context context) {
        // Constructor
        this.game = game;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return game.getPlayers().size();
    }

    @Override
    public Object getItem(int position) {
        return game.getPlayers().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.player_list_layout, parent, false);
        }

        TextView textScreenName = (TextView)convertView.findViewById(R.id.textListPlayerScreenName);
        TextView textCoins = (TextView)convertView.findViewById(R.id.textListPlayerCoins);

        Player thisPlayer = game.getPlayer(position);

        textScreenName.setText(thisPlayer.getScreenName());
        textCoins.setText(Integer.toString(thisPlayer.getCoins()));

        return convertView;
    }
}
