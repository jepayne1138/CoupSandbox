package com.jepaynedev.coupsandbox;

/**
 * Created by Jim on 3/16/2016.
 */
public class Influence {

    private Character character;
    private boolean revealed;
    private int id;

    /*
     * Constructs a new Influence instance, defaulting revealed to false
     */
    Influence(Character character) {
        this.character = character;
        this.revealed = false;
    }

    /*
     * Alternate constructor to allow for setting the revealed flag
     */
    Influence(Character character, boolean revealed) {
        this(character);
        this.revealed = revealed;
    }


    /*
     * Returns the character that this influence represents
     */
    public Character getCharacter() {
        return character;
    }

    /*
     * Sets this character to a new given character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /*
     * Returns true if the influence is visible to all players
     */
    public boolean isRevealed() {
        return revealed;
    }

    /*
     * Marks the influence card as revealed to all players
     */
    public void reveal() {
        revealed = true;
    }

    /*
     * Marks the influence card as hidden
     */
    public void hide() {
        revealed = false;
    }

    /*
     * Returns the id for the drawable resource for the given type
     */
    public int getDrawableId() {
        switch (character) {
            case DUKE:
                return R.drawable.card_duke;
            case AMBASSADOR:
                return R.drawable.card_ambassador;
            case ASSASSIN:
                return R.drawable.card_assassin;
            case CONTESSA:
                return R.drawable.card_contessa;
            case CAPTAIN:
                return R.drawable.card_captain;
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "(Char: " + getCharacter().name() + ", Id: " + Integer.toString(getId()) +
                ", Revealed: " + isRevealed() + ")";
    }
}