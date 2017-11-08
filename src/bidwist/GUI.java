/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidwist;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Brian
 */
public class GUI extends javax.swing.JFrame {

    CardListener _cardListener = new CardListener();
    int _seed = 50;
    ArrayList<ArrayList> _deck;
    ArrayList<Card> _baseDeck;
    ArrayList<Player> _players;
    ArrayList<Card> _kitty;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    

    }

    /**
     * Deals Cards to players.
     */
    public void deal() {

        int y = 0;
        _baseDeck = new ArrayList<Card>();
        _deck = new ArrayList();
        _kitty = new ArrayList();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                if (rank == Card.Rank.BIG_JOKER || rank == Card.Rank.LITTLE_JOKER || suit == Card.Suit.JOKER) {
                    break;
                }
                if (rank != Card.Rank.A_HI) {
                    Card card = new Card(rank, suit);
                    card.addListener((MouseListener) _cardListener);
                    _baseDeck.add(0, card);

                    this.add(card);

                }
            }
        }
        Card card = new Card(Card.Rank.LITTLE_JOKER, Card.Suit.JOKER);
        card.addListener((MouseListener) _cardListener);
        _baseDeck.add(0, card);
        this.add(card);
        card = new Card(Card.Rank.BIG_JOKER, Card.Suit.JOKER);
        card.addListener((MouseListener) _cardListener);
        _baseDeck.add(0, card);
        this.add(card);
        Collections.shuffle(_baseDeck, new Random(_seed));
        int numCards = 42;
        for (int i = 0; i <= 4; i++) {
            int x = 0;

            ArrayList<Card> tempDeck = new ArrayList();
            if (i != 4) {
                for (int j = _baseDeck.size() - 1; j >= numCards; j--) {

                    tempDeck.add(_baseDeck.get(j));
                    _baseDeck.remove(j);

                }
                _deck.add(tempDeck);
                for (Card c : tempDeck) {
                    c.setLocation(x, y);

                    x += 50;

                }
                y += 100;
                numCards -= 12;
            } else {

                for (int k = 5; k >= 0; k--) {
                    tempDeck.add(_baseDeck.get(k));
                    _baseDeck.remove(k);

                }
                for (Card c : tempDeck) {
                    c.setLocation(x, y);
                    _kitty.add(c);
                    x += 50;

                }

                y += 100;
            }
        }

    }

    /**
     * Creates Players for the game and assigns them cards.
     */
    public void createPlayers() {
        _players = new ArrayList();
        for (int i = 0; i < 4; i++) {
            Player p1 = new Player(_deck.get(i), this);
            _players.add(p1);
        }

    }

    /**
     * Starts the Game
     */
    public void startGame() {
        for (Card c : _kitty) {
            c.setLocation(325, 262);
        }
        _players.get(0).setLocationOffset(630, 120, 0, 20);
        _players.get(1).setLocationOffset(20, 120, 0, 20);
        _players.get(2).setLocationOffset(10, 25, 50, 0);
        _players.get(3).setLocationOffset(10, 500, 50, 0);
        _players.get(3).turnOver();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        javax.swing.JMenuItem menuStartGame = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bidwist");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("File");

        menuStartGame.setLabel("Start Game");
        menuStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStartGameActionPerformed(evt);
            }
        });
        jMenu1.add(menuStartGame);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStartGameActionPerformed
        deal();
        createPlayers();
        startGame();
        //  validate();
        //   repaint();

    }//GEN-LAST:event_menuStartGameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });

    }

    public class CardListener extends MouseAdapter {

        // --------------------- mousePressed --------------------------
        /**
         * mousePressed -- handled.
         *
         * @param e MouseEvent
         */
        public void mousePressed(MouseEvent e) {
            handleCardPick(e);
        }
    }
// ------------------------- handleCardPick( MouseEvent )
// ------------------

    /**
     * Got a mouse event (clicked) on a card; figure out if it represents a
     * valid move and do the move if it is.
     *
     * @param e MouseEvent
     */
    private void handleCardPick(MouseEvent e) {
        //System.out.println("Handle Card Pick Called");
        Point loc = e.getPoint(); // get coordinates of mouse event in panel
        Card card = (Card) e.getSource();
//        System.out.println("Card Suite: " + card.getSuit().toString() + "\nCard Rank: " + card.getRank().toString());
        loc.x = loc.x + card.getX();;
        loc.y = loc.y + card.getY();
        if (card.getFaceUp() == false) {
            card.setFaceUp(true);
        } else {
            card.setFaceUp(false);
        }
       card.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
