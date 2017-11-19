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
    Card _cardPlayed;
    int trumpPlayed = 0;
    int trumpInHand = 0;
    ArrayList<Card> _spades, _clubs, _diamonds, _hearts, _jokers;

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
        _spades = new ArrayList<Card>();
        _clubs = new ArrayList<Card>();
        _diamonds = new ArrayList<Card>();
        _hearts = new ArrayList<Card>();
        _jokers = new ArrayList<Card>();
        
    }

    /**
     * Returns the players bid at the begining of the game
     *
     */
    public void bid() {
        int points = 0, hearts = 0, clubs = 0, spades = 0, diamonds = 0, jokers = 0;
        int spadesCount = 0, diamondsCount = 0, heartsCount = 0, clubsCount = 0, jokersCount = 0;
        for (Card c : _hand) {
            if (c.getSuit() == Card.Suit.CLUBS) {
                clubsCount++;
                clubs += c.getRank().ordinal();
                _clubs.add(c);
            }
            if (c.getSuit() == Card.Suit.DIAMONDS) {
                diamondsCount++;
                diamonds += c.getRank().ordinal();
                _diamonds.add(c);
            }
            if (c.getSuit() == Card.Suit.HEARTS) {
                heartsCount++;
                hearts += c.getRank().ordinal();
                _hearts.add(c);
            }
            if (c.getSuit() == Card.Suit.SPADES) {
                spadesCount++;
                spades = c.getRank().ordinal();
                _spades.add(c);
            }
            if (c.getSuit() == Card.Suit.JOKER) {
                jokersCount++;
                jokers += 15;
                _jokers.add(c);
            }
        }
        if (clubsCount < 4) {
            clubs = 0;
        }
        if (diamondsCount < 4) {
            diamonds = 0;
        }
        if (heartsCount < 4) {
            hearts = 0;
        }
        if (spades < 4) {
            spades = 0;
        }
        points = (clubs + hearts + spades + diamonds + jokers) / 11;
        if (points < 4) {
            _bid = 0;
        } else {
            _bid = points;
        }
    }

    /**
     * Returns the number of cards that are in the raised state
     *
     * @return raisedCards
     */
    public int getRaisedCards() {
        int count = 0;
        for (Card card : _hand) {
            if (card.getRaised() == true) {
                count++;
            }
        }
        return count;
    }

    /**
     * Discards selected Cards.
     */
    public void discard() {
        ArrayList<Card> cardsToRemove = new ArrayList<Card>();
        for (int i = 0; i < _hand.size(); i++) {
            if (_hand.get(i).getRaised() == true) {
                cardsToRemove.add(_hand.get(i));
            }
        }
        for (Card c : cardsToRemove) {
            _hand.remove(c);
            c.SetLocation(-100, -100);
        }
        
    }

    /**
     * Returns the card that the player will play. Probably will need input
     *
     * @param hasBeenPlayed
     * @return
     */
    public Card play(ArrayList<Card> hasBeenPlayed, Card.Suit leadSuit) {
        Collections.sort(hasBeenPlayed);
        if (hasBeenPlayed == null && leadSuit == null) {
            if (_dealer == true) {
                if (trumpPlayed + trumpInHand != 12) {
                    if (_jokers.size() != 0) {
                        return (_jokers.get(0));
                    }
                    else
                    {
                        return(null);
                    }
                     
                } else {
                   
                }
            } else if (hasBeenPlayed.get(0).getSuit() == _trump) {
                
            } else {
                leadSuit = hasBeenPlayed.get(0).getSuit();
            }
            
        }
        
        return null;
    }

    /**
     * Adds the kitty to a players hand.
     *
     * @param kitty Arraylist
     */
    public void giveKitty(ArrayList<Card> kitty) {
        _hand.addAll(kitty);
        _parent.repaint();
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
        ArrayList<Card> jokers, spades, hearts, clubs, diamonds, temp, jokers2;
        jokers = new ArrayList<Card>();
        jokers2 = new ArrayList<Card>();
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
                jokers2.add(0, c);
            } else if (c.getRank() == Card.Rank.BIG_JOKER) {
                jokers2.add(0, c);
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
        for (Card c : _hand) {
            if (c.getSuit() == suit) {
                trumpInHand++;
            }
        }
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

    /**
     * returns true if the player just played the card passed to it.
     *
     * @param c Card to check if the player played
     * @return true/false
     */
    public boolean playedCard(Card c) {
        if (c == _cardPlayed) {
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * Returns the bid
     *
     * @return int bid
     */
    public int getBid() {
        return _bid;
    }

    /**
     * Sets players bid.
     *
     * @param bid
     */
    public void setBid(int bid) {
        _bid = bid;
    }
}
