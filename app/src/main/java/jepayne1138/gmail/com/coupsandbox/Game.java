package jepayne1138.gmail.com.coupsandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim on 3/7/2016.
 */
public class Game {

    private final Deck deck = new Deck();
    private final List<Player> players = new ArrayList<Player>();
    private Player currentPlayer;

    Game() {
        // TODO: Basically everything, just want to make a dummy game for now
    }

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
        return new Deck(deck);
    }

    /*
     * Returns a copy of the list of players
     */
    public List<Player> getPlayers() {
        return new ArrayList<Player>(players);
    }

    public Player getPlayer(int position) {
        return new Player(players.get(position));
    }
}
