package com.gmail.jepayne1138.coupsandbox;

import android.databinding.DataBindingUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gmail.jepayne1138.coupsandbox.databinding.PlayerListBinding;

/**
 * Created by Jim on 3/7/2016.
 */
public class PlayerListAdapter extends BaseAdapter {

    final private Game game;
    final private LayoutInflater inflater;
    private PlayerListBinding binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.player_list, parent, false);

        binding.setPlayer(game.getPlayer(position));

        return binding.getRoot();
    }
}
