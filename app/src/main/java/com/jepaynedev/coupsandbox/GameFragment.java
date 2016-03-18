package com.jepaynedev.coupsandbox;


import android.app.ActionBar;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.jepaynedev.coupsandbox.databinding.FragmentGameBinding;

import java.util.InvalidPropertiesFormatException;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnClickListener, View.OnDragListener, View.OnTouchListener {

    private GameManager game;
    FragmentGameBinding binding;
    View fragmentView;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CoupActivity activity = (CoupActivity)getActivity();
        game = activity.game;

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false);
        fragmentView = binding.getRoot();

        // Set the current user to the data model in the binding
        binding.setCurPlayer(game.getCurrentPlayer());

        // Initialize the player list view
        ListView playerListView = (ListView) fragmentView.findViewById(R.id.listPlayers);
        PlayerListAdapter playerListAdapter = new PlayerListAdapter(game, getActivity());
        playerListView.setAdapter(playerListAdapter);

        // Draw the initial hand of cards
        drawInfluence();

        // Set buttons to use this interface as the onClick listener
        ((ImageButton)(fragmentView.findViewById(R.id.buttonCoinUp))).setOnClickListener(this);
        ((ImageButton)(fragmentView.findViewById(R.id.buttonCoinDown))).setOnClickListener(this);
        ((ImageView)(fragmentView.findViewById(R.id.imageDeck))).setOnTouchListener(this);
        ((ImageView)(fragmentView.findViewById(R.id.imageDeck))).setOnDragListener(this);
        ((FrameLayout)(fragmentView.findViewById(R.id.scrollHand))).setOnDragListener(this);

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
//        Log.d("debug", "view.getID() = " + Integer.toString(v.getId()));
//        Log.d("debug", "event.getAction() = " + event.getAction());
        // Handles event actions
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
//                Log.d("debug", "event.getAction() = ACTION_DRAG_STARTED");
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
//                Log.d("debug", "event.getAction() = ACTION_DRAG_ENTERED");
                if (v.getId() == R.id.scrollHand) {
                    v.setBackgroundResource(R.drawable.layout_hover_border);
                }
                break;
            case DragEvent.ACTION_DRAG_EXITED:
//                Log.d("debug", "event.getAction() = ACTION_DRAG_EXITED");
                if (v.getId() == R.id.scrollHand) {
                    v.setBackgroundResource(0);
                }
                break;
            case DragEvent.ACTION_DROP:
                if (v.getId() == R.id.scrollHand) {
                    // Remove the border as it doesn't seem to send ACTION_DRAG_EXITED
                    // TODO:  Look into ACTION_DRAG_EXITED should be called after ACTION_DROP
                    v.setBackgroundResource(0);

                    // Continue with dropping the new card
                    if (game.getCurrentPlayer().drawInfluenceCard(game.getDeck())) {
                        drawInfluence();
                        return true;
                    }
                }
                return false;
        }

        return false;
    }

    private void drawInfluence() {
        // TODO:  Figure out why it's adding 300 more to the right of every image, only want padding left, not right

        // Get a reference to the scrollview that holds the hand
        HorizontalScrollView scrollHand = (HorizontalScrollView) fragmentView.findViewById(R.id.scrollHand);
        // Get a reference to the layout in the scrollview that actually holds the images
        RelativeLayout layoutHand = (RelativeLayout) fragmentView.findViewById(R.id.layoutHand);

        // Remove all existing images from the view
        layoutHand.removeAllViews();
        // Get width for the margin between cards
        int marginWidth = scrollHand.getWidth() / 2;

        // Redraw all cards
        Influence leftInfluence = null;  // Influence image last place
        for (int i=0; i<game.getCurrentPlayer().getInfluence().size(); i++) {
            Influence influence = game.getCurrentPlayer().getInfluence().get(i);
            ImageView influenceImage = new ImageView(getActivity());
            influenceImage.setId(i);
            if (leftInfluence != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT, i-1);
                layoutParams.leftMargin = 300;
                influenceImage.setLayoutParams(layoutParams);
            }
            else {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
            leftInfluence = influence;
            influenceImage.setImageResource(influence.getDrawable());
            layoutHand.addView(influenceImage);
        }

        // Debug widths
        Log.d("scrollHand.getWidth()", Integer.toString(scrollHand.getWidth()));
        Log.d("layoutHand.getWidth()", Integer.toString(layoutHand.getWidth()));
    }
}
