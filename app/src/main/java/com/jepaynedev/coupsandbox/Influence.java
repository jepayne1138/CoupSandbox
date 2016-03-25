package com.jepaynedev.coupsandbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Jim on 3/16/2016.
 */
public class Influence extends BaseObservable {

    private Character character;
    private boolean revealed;
    private int id;
    private int drawableIconId;

    /*
     * Constructs a new Influence instance, defaulting revealed to false
     */
    Influence(Character character) {
        this.character = character;
        this.revealed = false;
        setDrawableIconId(getCharacterIconId());
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
    @Bindable
    public boolean getRevealed() {
        return revealed;
    }

    /*
     * Set revealed status
     */
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
        notifyPropertyChanged(BR.revealed);
        Log.d("Influence", "setRevealed = "+ this.revealed);
    }

    /*
     * Returns the id for the drawable resource for the given type
     */
    @Bindable
    public int getDrawableIconId() {
        return drawableIconId;
    }

    public void setDrawableIconId(int drawableIconId) {
        this.drawableIconId = drawableIconId;
        notifyPropertyChanged(BR.drawableIconId);
        Log.d("Influence", "setDrawableIconId = " + this.drawableIconId);
    }

    @BindingAdapter({"bind:revealed", "bind:drawableIconId"})
    public static void loadImage(ImageView view, boolean revealed, int drawableId) {
        Log.d("Influence", "loadImage(revealed = " + revealed + ", drawableId = " + Integer.toString(drawableId) + ")");
        if (revealed) {
            Picasso.with(view.getContext()).load(drawableId).into(view);
        }
        else {
            Picasso.with(view.getContext()).load(R.drawable.hidden_icon).into(view);
        }
    }

    /*
     * Gets a drawable id for the proper character
     */
    private Integer getCharacterIconId() {
        switch (getCharacter()) {
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

    public Integer getCharacterCardId() {
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
                ", Revealed: " + getRevealed() + ")";
    }
}