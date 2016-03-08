package jepayne1138.gmail.com.coupsandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jim on 3/7/2016.
 */
public class Deck {

    private final int NUMBER_OF_COPIES = 3;
    private final List<Character> cards = new ArrayList<Character>();

    /*
     * Construct a new deck instance
     */
    Deck() {
        // Add 3 copies of each card to the deck to start
        for (int i=0; i<NUMBER_OF_COPIES; i++) {
            cards.add(Character.DUKE);
            cards.add(Character.ASSASSIN);
            cards.add(Character.AMBASSADOR);
            cards.add(Character.CAPTAIN);
            cards.add(Character.CONTESSA);
        }
    }

    /*
     * Duplication constructor
     */
    Deck(Deck deck) {
        this.cards.addAll(deck.cards);
    }

    /*
     * Draws (picks, removes, and returns) a new character from the deck, or null if empty
     */
    public Character drawCard() {
        // If the deck is empty, return null
        if (cards.size() == 0) {
            return null;
        }

        // Otherwise pick a random card from the deck
        return cards.remove(new Random().nextInt(cards.size()));
    }

    /*
     * Returns a card to the deck
     */
    public void returnCard(Character character) {
        cards.add(character);
    }
}
