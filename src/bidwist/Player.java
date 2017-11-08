package bidwist;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    Card.Suit _trump = null;
    Card cardPlayed;

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
        int points = 0, hearts = 0, clubs = 0, spades = 0, diamonds = 0, jokers = 0;
        int spadesCount = 0, diamondsCount = 0, heartsCount = 0, clubsCount = 0, jokersCount = 0;
        for (Card c : _hand) {
            if (c.getSuit() == Card.Suit.CLUBS) {
                clubsCount++;
                clubs += c.getRank().ordinal();
            }
            if (c.getSuit() == Card.Suit.DIAMONDS) {
                diamondsCount++;
                diamonds += c.getRank().ordinal();
            }
            if (c.getSuit() == Card.Suit.HEARTS) {
                heartsCount++;
                hearts += c.getRank().ordinal();
            }
            if (c.getSuit() == Card.Suit.SPADES) {
                spadesCount++;
                spades = c.getRank().ordinal();
            }
            if (c.getSuit() == Card.Suit.JOKER) {
                jokersCount++;
                jokers += 15;
            }
        }
        System.out.println("Spades: " + spadesCount + " Points: " + spades);
        System.out.println("Diamonds: " + diamondsCount + " Points: " + diamonds);
        System.out.println("Clubs: " + clubsCount + " Points: " + clubs);
        System.out.println("Hearts: " + heartsCount + " Points: " + hearts);
        System.out.println("Jokers: " + jokersCount + " Points: " + jokers);
        if (clubsCount < 5) {
            clubs = 0;
        }
        if (diamondsCount < 5) {
            diamonds = 0;
        }
        if (heartsCount < 5) {
            hearts = 0;
        }
        if (spades < 5) {
            spades = 0;
        }
        points = (clubs + hearts + spades + diamonds + jokers) / 9;
        if (points < 4) {
            return 0;
        } else {
            return points;
        }
    }

    /**
     * Returns the card that the player will play. Probably will need input
     *
     * @return
     */
    public Card play(ArrayList<Card> hasBeenPlayed) {
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
     * Sorts cards by suit then by rank in suit.
     */
    public void orderCards() {
        ArrayList<Card> jokers, spades, hearts, clubs, diamonds, temp;
        jokers = new ArrayList<Card>();
        spades = new ArrayList<Card>();
        hearts = new ArrayList<Card>();
        clubs = new ArrayList<Card>();
        temp = new ArrayList<Card>();
        diamonds = new ArrayList<Card>();
        for (Card c : _hand) {
            if (c.getSuit() == Card.Suit.CLUBS) {
                clubs.add(c);
            }
            if (c.getSuit() == Card.Suit.DIAMONDS) {
                diamonds.add(c);
            }
            if (c.getSuit() == Card.Suit.HEARTS) {
                hearts.add(c);
            }
            if (c.getSuit() == Card.Suit.SPADES) {
                spades.add(c);
            }
            if (c.getSuit() == Card.Suit.JOKER) {
                jokers.add(c);
            }
        }
        for (Card c : jokers) {
            if (c.getRank() == Card.Rank.LITTLE_JOKER) {

            } else if (c.getRank() == Card.Rank.BIG_JOKER) {
                jokers.add(0, c);
                jokers.remove(c);
            }
        }
        Collections.sort(spades);
        Collections.sort(clubs);
        Collections.sort(hearts);
        Collections.sort(diamonds);
        _hand.clear();
        _hand.addAll(jokers);
        _hand.addAll(spades);
        _hand.addAll(hearts);
        _hand.addAll(clubs);
        _hand.addAll(diamonds);

    }

    /**
     * turns over players hand.
     */
    public void turnOver() {
        for (Card c : _hand) {
            c.setFaceUp(true);
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

    /**
     * Sets trump for the player
     *
     * @param suit the suit that trump has been called in.
     */
    public void setTrump(Card.Suit suit) {
        _trump = suit;
    }

    /**
     * Returns trump for the current player.
     *
     * @return card Suit that is trump
     */
    public Card.Suit getTrump() {
        return _trump;
    }

}
