package com.jepaynedev.coupsandbox;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.jepaynedev.coupsandbox.databinding.FragmentGameBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnClickListener {

    private GameManager game;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CoupActivity activity = (CoupActivity)getActivity();
        game = activity.game;

        // Inflate the layout for this fragment
        FragmentGameBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false);

        // Set the current user to the data model in the binding
        binding.setCurPlayer(game.getCurrentPlayer());

        // Initialize the player list view
        ListView playerListView = (ListView) binding.getRoot().findViewById(R.id.listPlayers);
        PlayerListAdapter playerListAdapter = new PlayerListAdapter(game, getActivity());
        playerListView.setAdapter(playerListAdapter);

        // Set buttons to use this interface as the onClick listener
        ((ImageButton)(binding.getRoot().findViewById(R.id.buttonCoinUp))).setOnClickListener(this);
        ((ImageButton)(binding.getRoot().findViewById(R.id.buttonCoinDown))).setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCoinUp:
                game.getCurrentPlayer().incrementCoins();
                break;
            case R.id.buttonCoinDown:
                game.getCurrentPlayer().decrementCoins();
                break;
        }
    }
}
