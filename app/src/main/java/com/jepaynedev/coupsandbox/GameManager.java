package com.jepaynedev.coupsandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim on 3/16/2016.
 */
public class GameManager {

    private final Deck deck = new Deck();
    private final List<Player> players = new ArrayList<Player>();
    private Player currentPlayer;

    GameManager() {}

    /*
     * Add a player to the game and returns the new instance
     */
    public Player addPlayer(String screenName) {
        Player newPlayer = new Player(screenName, this);
        players.add(newPlayer);
        return newPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    /*
     * Return a copy of the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /*
     * Returns a copy of the list of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int position) {
        return players.get(position);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
