package jepayne1138.gmail.com.coupsandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim on 3/7/2016.
 */
public class Player {

    private final int STARTING_COINS = 2;
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
    public String getScreenName() {
        return screenName;
    }

    /*
     * Returns the number of coins the player has
     */
    public int getCoins() {
        return coins;
    }
}
