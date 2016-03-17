package com.jepaynedev.coupsandbox;


import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.jepaynedev.coupsandbox.databinding.FragmentGameBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnClickListener, View.OnDragListener, View.OnTouchListener {

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
        ((ImageView)(binding.getRoot().findViewById(R.id.imageDeck))).setOnTouchListener(this);
        ((ImageView)(binding.getRoot().findViewById(R.id.imageDeck))).setOnDragListener(this);
        ((FrameLayout)(binding.getRoot().findViewById(R.id.frameHand))).setOnDragListener(this);

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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v.startDrag(null, new View.DragShadowBuilder(v), v, 0);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        Log.d("debug", "view.getID() = " + Integer.toString(v.getId()));
        Log.d("debug", "event.getAction() = " + event.getAction());
        // Handles event actions
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                if (v.getId() == R.id.frameHand) {
                    // TODO:  Somehow hightlight the layout so that the user knows it can be dropped
                }
            case DragEvent.ACTION_DRAG_EXITED:
                if (v.getId() == R.id.frameHand) {
                    // TODO:  Reverse the highlighting done in the enter event
                }
        }

        return false;
    }
}
