/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidwist;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brian
 */
public class Player {

    ArrayList<Card> _hand;
    int _bid;
    boolean _dealer;

    public Player(ArrayList hand) {
        _hand = hand;
        _dealer = false;

    }

    public int bid() {
        return 0;
    }

    public Card play() {
        return null;
    }

    public void setLocation(int x, int y) {
        for (Card c : _hand) {
            c.setLocation(x, y);
        }
    }

    public void turnOver() {
        for (Card c : _hand) {
            c.setFaceUp(true);

        }
    }

    public void setLocationOffset(int startX, int startY, int offsetX, int offsetY) {
        int offX = offsetX;
        int offY = offsetY;
        for (Card c : _hand) {
            c.setLocation(startX + offX, startY + offY);
            offX += offsetX;
            offY += offsetY;
            System.out.println("Card Location Set: X:" + (startX + offX) + " Y:" + (startY + offY));
            c.repaint();
        }
    }

    public boolean getDealer() {
        return _dealer;
    }

    public void setDealer(boolean dealer) {
        _dealer = dealer;
    }
}
