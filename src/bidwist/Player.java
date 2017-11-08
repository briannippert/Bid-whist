/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidwist;

import java.awt.Container;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brian
 */
public class Player {

    ArrayList<Card> _hand;
    Container _parent;
    int _bid;
    boolean _dealer;

    /**
     * Constructor for the player class
     *
     * @param hand cards in the players hand
     * @param parent parent object used for Z order
     */
    public Player(ArrayList hand, Container parent) {
        _hand = hand;
        _dealer = false;
        _parent = parent;

    }

    /**
     * Returns the players bid at the begining of the game
     *
     * @return
     */
    public int bid() {
        return 0;
    }

    /**
     * Returns the card that the player will play. Probably will need input
     *
     * @return
     */
    public Card play() {
        return null;
    }

    /**
     * sets the location of all of the players cards
     *
     * @param x X location of the cards
     * @param y Y Location of the cards
     */
    public void setLocation(int x, int y) {
        for (Card c : _hand) {
            c.setLocation(x, y);
        }
    }

    /**
     * turns over players hand.
     */
    public void turnOver() {
        for (Card c : _hand) {
            c.setFaceUp(true);
            //_parent.setComponentZOrder(c, 0);
        }
    }

    /**
     * Draws players cards in a line
     *
     * @param startX initial X position
     * @param startY initial Y position
     * @param offsetX X offset for each card
     * @param offsetY Y offeset for each Card
     */
    public void setLocationOffset(int startX, int startY, int offsetX, int offsetY) {
        int offX = offsetX;
        int offY = offsetY;
        for (Card c : _hand) {
            _parent.setComponentZOrder(c, 0);
            c.setLocation(startX + offX, startY + offY);
            offX += offsetX;
            offY += offsetY;

        }
    }

    /**
     * Returns if the player is the dealer
     *
     * @return true or false
     */
    public boolean getDealer() {
        return _dealer;
    }

    /**
     * Sets if the player is the dealer.
     *
     * @param dealer
     */
    public void setDealer(boolean dealer) {
        _dealer = dealer;
    }

    /**
     * Checks to see if the player has the card
     *
     * @param c card to check
     * @return true if the player has the card otherwise false.
     */
    public boolean hasCard(Card c) {
        if (_hand.contains(c)) {
            return true;
        } else {
            return false;
        }

    }
}
