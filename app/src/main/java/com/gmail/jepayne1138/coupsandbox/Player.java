package com.gmail.jepayne1138.coupsandbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim on 3/7/2016.
 */
public class Player extends BaseObservable {

    private final int STARTING_COINS = 2;
    private final int COIN_MIN = 0;
    private final int COIN_MAX = 12;
    private final int STARTING_INFLUENCE = 2;
    private final List<Influence> influence = new ArrayList<Influence>();

    private String screenName;
    private int coins;

    Player(String screenName, Game game) {
        // Player specific information
        this.screenName = screenName;

        // Give the player the starting influence
        for (int i=0; i<STARTING_INFLUENCE; i++) {
            influence.add(new Influence(game.getDeck().drawCard()));
        }
        coins = STARTING_COINS;
    }

    /*
     * Player duplication constructor
     */
    Player(Player player) {
        screenName = player.screenName;
        coins = player.coins;
        influence.addAll(player.influence);
    }

    /*
     * Returns copy of the player screen name
     */
    @Bindable
    public String getScreenName() {
        return screenName;
    }

    /*
     * Returns the number of coins the player has
     */
    @Bindable
    public int getCoins() {
        return coins;
    }

    /*
     * Increments the players coin count and returns true if successful
     */
    public boolean incrementCoins() {
        if (coins < COIN_MAX) {
            coins += 1;
            notifyPropertyChanged(BR.coins);
            return true;
        }
        return false;
    }

    /*
     * Decrements the players coin count and returns true if successful.  Cannot go negative.
     */
    public boolean decrementCoins() {
        if (coins > COIN_MIN) {
            coins -= 1;
            notifyPropertyChanged(BR.coins);
            return true;
        }
        return false;
    }
}
