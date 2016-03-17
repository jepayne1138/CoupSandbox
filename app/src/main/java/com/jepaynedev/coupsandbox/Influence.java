package com.jepaynedev.coupsandbox;

/**
 * Created by Jim on 3/16/2016.
 */
public class Influence {

    private Character character;
    private boolean revealed;

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
}