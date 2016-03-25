package com.jepaynedev.coupsandbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

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
        for (int i = 0; i < STARTING_INFLUENCE; i++) {
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
        Log.d("Player", "returning card with id = " + Integer.toString(id));
        Influence inf;
        for (int i = 0; i < influence.size(); i++) {
            if (influence.get(i).getId() == id) {
                deck.returnCard(influence.remove(i).getCharacter());
            }
        }
        return false;
    }

    public String toString() {
        String returnString = "ScreenName:  " + getScreenName();

        // Add coins
        returnString += "\nCoins:  " + Integer.toString(getCoins());

        // Display influences
        List<String> influenceStrings = new ArrayList<String>();
        for (Influence inf : influence) {
            influenceStrings.add(inf.toString());
        }
        returnString += "\nHand:  " + influenceStrings.toString();

        return returnString;
    }

    /*
    public Integer getInfluenceDrawableId1() {
        return getInfluenceDrawableId(0);
    }

    public Integer getInfluenceDrawableId2() {
        return getInfluenceDrawableId(1);
    }

    private Integer getInfluenceDrawableId(int index) {
        if (index < 0 || index > 1) {
            throw new IllegalArgumentException();
        }
        Influence inf = getInfluence().get(index);
        if (!inf.getRevealed()) {
            return null;
        }
        switch (inf.getCharacter()) {
            case DUKE:
                return R.drawable.duke_icon;
            case ASSASSIN:
                return R.drawable.assassin_icon;
            case AMBASSADOR:
                return R.drawable.ambassador_icon;
            case CONTESSA:
                return R.drawable.contessa_icon;
            case CAPTAIN:
                return R.drawable.captain_icon;
            default:
                return null;
        }
    }

    @BindingAdapter({"bind:influenceDrawableId1"})
    public static void loadImage1(ImageView view, Integer influenceDrawableId) {
        Picasso.with(view.getContext())
                .load(influenceDrawableId)
                .placeholder(R.drawable.hidden_icon);
    }

    @BindingAdapter({"bind:influenceDrawableId2"})
    public static void loadImage2(ImageView view, Integer influenceDrawableId) {
        Picasso.with(view.getContext())
                .load(influenceDrawableId)
                .placeholder(R.drawable.hidden_icon);
    }
    */
}