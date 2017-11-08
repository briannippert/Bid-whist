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

    public Player(ArrayList hand, Container parent) {
        _hand = hand;
        _dealer = false;
        _parent = parent;

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
            _parent.setComponentZOrder(c, 0);
        }
    }

    public void setLocationOffset(int startX, int startY, int offsetX, int offsetY) {
        int offX = offsetX;
        int offY = offsetY;
        for (Card c : _hand) {
//            int xpos = startX + offX;
//            int ypos = startY + offY;
//            System.out.println("X Position: " +xpos);
//            System.out.println("Y Position: " +ypos);
            c.setLocation(startX + offX, startY + offY);
            offX += offsetX;
            offY += offsetY;
            //_parent.setComponentZOrder(c, 0);
            
        }
    }

    public boolean getDealer() {
        return _dealer;
    }

    public void setDealer(boolean dealer) {
        _dealer = dealer;
    }
}
