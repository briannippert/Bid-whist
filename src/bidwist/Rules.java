/*
 * Copyright (C) 2017 Brian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bidwist;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brian
 */
public class Rules {

    /**
     * Analyzes 4 cards to determine the winner.
     *
     * @param cards Arraylist Of Cards that have been played
     * @param trump Suit that Trump represents
     * @return
     */
    public static Card analyzeTrick(ArrayList<Card> cards, Card.Suit trump, Card.Suit leadSuit) {
        ArrayList<Card> trumpCards = new ArrayList<Card>();
        ArrayList<Card> leadSuitCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getSuit() == trump || card.getSuit() == Card.Suit.JOKER) {
                trumpCards.add(card);
            }
            if (card.getSuit() == leadSuit) {
                leadSuitCards.add(card);
            }
        }
        if (trumpCards.size() > 0) {
            Collections.sort(trumpCards);
            return trumpCards.get(trumpCards.size()-1);
        } else {
            Collections.sort(leadSuitCards);
            return (leadSuitCards.get(leadSuitCards.size()-1));
        }

    }
    
    

    public static void main(String args[]) {
        //Analyze Trick Unit Testing.
        ArrayList<Card> cards = new ArrayList<Card>();
        Card c1 = new Card(Card.Rank.BIG_JOKER, Card.Suit.JOKER);
        Card c2 = new Card(Card.Rank.EIGHT, Card.Suit.HEARTS);
        Card c3 = new Card(Card.Rank.KING, Card.Suit.HEARTS);
        Card c4 = new Card(Card.Rank.TWO, Card.Suit.SPADES);
        Card c5 = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS);
        Card c6 = new Card(Card.Rank.JACK, Card.Suit.CLUBS);
        Card c7 = new Card(Card.Rank.FOUR, Card.Suit.CLUBS);
        Card c8 = new Card(Card.Rank.A_HI, Card.Suit.SPADES);
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        Card winner = Rules.analyzeTrick(cards, Card.Suit.HEARTS, Card.Suit.HEARTS);
        System.out.println("Big Joker Should be the winner");
        System.out.println(winner.getRank() + " " + winner.getSuit() + " is the Winning Card");
        cards.clear();
        cards.add(c5);
        cards.add(c6);
        cards.add(c7);
        cards.add(c8);
        winner = Rules.analyzeTrick(cards, Card.Suit.HEARTS, Card.Suit.DIAMONDS);
        System.out.println("Queen Of Diamonds should be the winner");
        System.out.println(winner.getRank() + " " + winner.getSuit() + " is the Winning Card");
        winner = Rules.analyzeTrick(cards, Card.Suit.CLUBS, Card.Suit.DIAMONDS);
        System.out.println("Jack of Clubs should be the winner");
        System.out.println(winner.getRank() + " " + winner.getSuit() + " is the Winning Card");
    }
}
