package com.jepaynedev.coupsandbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim on 3/16/2016.
 */
public class Player extends BaseObservable {

    private final int STARTING_COINS = 2;
    private final int COIN_MIN = 0;
    private final int COIN_MAX = 12;
    private final int STARTING_INFLUENCE = 2;
    private final List<Influence> influence = new ArrayList<Influence>();

    private String screenName;
    private int coins;

    Player(String screenName, GameManager game) {
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

    /*
     * Returns a reference to the list of Influence instances
     */
    @Bindable
    public List<Influence> getInfluence() {
        return influence;
    }

    /*
     * Adds a new influence, returns null if no cards left in the deck
     */
    public boolean drawInfluenceCard(Deck deck) {
        Character character = deck.drawCard();
        if (character == null) {
            return false;
        }
        influence.add(new Influence(character));
        notifyPropertyChanged(BR.influence);

        return true;
    }

    /*
     * Returns a card with a specified id to the given deck.
     * Used by the return swipe to return a card from the hand to the deck.
     *
     * Returns true if successfully returned
     */
    public boolean returnInfluenceCard(Deck deck, int id) {
        Influence inf;
        for (int i=0; i<influence.size(); i++) {
            if (influence.get(i).getId() == id) {
                deck.returnCard(influence.remove(1).getCharacter());
            }
        }
        return false;
    }
}
