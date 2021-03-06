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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brian
 */
public class GUI extends javax.swing.JFrame {

   private CardListener _cardListener = new CardListener();
    int _seed = new Random(10000).nextInt();
    private ArrayList<ArrayList> _deck;
    private ArrayList<Card> _baseDeck;
    private ArrayList<Player> _players;
    private ArrayList<Card> _kitty;
    private boolean _discard = false;
    private int dealer = 1;
    private volatile boolean _hasBid = false;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        btnBid.setVisible(false);
        sldBid.setVisible(false);
        btnPass.setVisible(false);
        btnDiscard.setVisible(false);
        btnPlay.setVisible((false));
        // btnPlay.setLocation(btnBid.getLocation());
        setResizable(false);
        _baseDeck = new ArrayList<Card>();
        _deck = new ArrayList();
        _kitty = new ArrayList();
        _players = new ArrayList();
    }

    /**
     * Deals Cards to players.
     */
    private void deal() {
        int y = 0;
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
        Collections.shuffle(_baseDeck);
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
        _players.get(0).setLocationOffset(630, 150, 0, 20);
        _players.get(1).setLocationOffset(20, 150, 0, 20);
        _players.get(2).setLocationOffset(10, 60, 50, 0);
        _players.get(3).setLocationOffset(10, 560, 50, 0);
        _players.get(3).turnOver();
        Player leader = bid();
    }

    /**
     * Method for having all the players bid.
     */
    public Player bid() {
        if (dealer == 3) {
            _players.get(3).setDealer(true);
            _players.get(0).bid();
            _players.get(1).bid();
            _players.get(2).bid();
            lblPlayer1.setText("Player 1 Bid: " + _players.get(0).getBid());
            lblPlayer2.setText("Player 2 Bid: " + _players.get(1).getBid());
            lblPlayer3.setText("Player 3 Bid: " + _players.get(2).getBid());
            btnBid.setVisible(true);
            sldBid.setVisible(true);
            btnPass.setVisible(true);
            dealer = 1;
        } else if (dealer == 1) {
            _players.get(1).setDealer(true);
            _players.get(2).bid();
            _players.get(0).bid();
            lblPlayer2.setText("Player 2 Bid: " + _players.get(1).getBid());
            lblPlayer3.setText("Player 3 Bid: " + _players.get(2).getBid());
            btnBid.setVisible(true);
            sldBid.setVisible(true);
            btnPass.setVisible(true);
        } else if (dealer == 2) {

        } else if (dealer == 0) {

        }

        return null;
    }

    /**
     *
     */
    public void reDeal() {
        _deck.clear();
        _baseDeck.clear();
        _players.clear();
        _kitty.clear();
        deal();
        createPlayers();
        _players.get(3).orderCards();
        _players.get(2).orderCards();
        _players.get(1).orderCards();
        _players.get(0).orderCards();
        startGame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sldBid = new javax.swing.JSlider();
        btnBid = new javax.swing.JButton();
        btnPass = new javax.swing.JButton();
        lblPlayer1 = new javax.swing.JLabel();
        lblPlayer2 = new javax.swing.JLabel();
        lblPlayer3 = new javax.swing.JLabel();
        lblInstructions = new javax.swing.JLabel();
        btnDiscard = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        javax.swing.JMenuItem menuStartGame = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bidwist");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        sldBid.setMajorTickSpacing(1);
        sldBid.setMaximum(7);
        sldBid.setMinimum(4);
        sldBid.setMinorTickSpacing(1);
        sldBid.setPaintLabels(true);
        sldBid.setPaintTicks(true);
        sldBid.setToolTipText("");
        sldBid.setValue(4);

        btnBid.setText("Bid");
        btnBid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBidActionPerformed(evt);
            }
        });

        btnPass.setText("Pass");
        btnPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPassActionPerformed(evt);
            }
        });

        lblPlayer1.setText("Player 2");

        lblPlayer2.setText("Player 3");

        lblPlayer3.setText("Player 1");

        lblInstructions.setText("Player 4");

        btnDiscard.setText("Discard");
        btnDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscardActionPerformed(evt);
            }
        });

        btnPlay.setText("Play");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(lblPlayer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPlayer3)
                .addGap(189, 189, 189))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldBid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBid, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPass))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(btnDiscard))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(342, 342, 342)
                        .addComponent(btnPlay))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblInstructions)
                            .addComponent(lblPlayer2))))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlayer1)
                            .addComponent(lblPlayer3))
                        .addGap(209, 209, 209))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(lblPlayer2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                        .addComponent(lblInstructions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDiscard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldBid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBid)
                            .addComponent(btnPass))
                        .addGap(26, 26, 26)))
                .addComponent(btnPlay)
                .addGap(112, 112, 112))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStartGameActionPerformed
        deal();
        createPlayers();
        _players.get(3).orderCards();
        _players.get(2).orderCards();
        _players.get(1).orderCards();
        _players.get(0).orderCards();
        startGame();

    }//GEN-LAST:event_menuStartGameActionPerformed

    private void btnBidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBidActionPerformed

//        _players.get(3).setDealer(true);
//        _players.get(0).bid();
//        _players.get(1).bid();
//        _players.get(2).bid();
//        lblPlayer1.setText("Player 1 Bid: " + _players.get(0).getBid());
//        lblPlayer2.setText("Player 2 Bid: " + _players.get(1).getBid());
//        lblPlayer3.setText("Player 3 Bid: " + _players.get(2).getBid());
        _players.get(3).setBid(sldBid.getValue());
        //  System.out.println("You Bid a " + _players.get(3).getBid());
        btnBid.setVisible(false);
        sldBid.setVisible(false);
        btnPass.setVisible(false);
        btnDiscard.setVisible(true);
        _discard = true;
        Player leader = _players.get(3);
        leader.giveKitty(_kitty);
        leader.orderCards();
        leader.setLocationOffset(10, 540, 35, 0);
        leader.turnOver();
        _hasBid = true;


    }//GEN-LAST:event_btnBidActionPerformed

    private void btnPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPassActionPerformed
        //   if (_players.get(3).getDealer() == true) {
        reDeal();
        //   }
    }//GEN-LAST:event_btnPassActionPerformed

    private void btnDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscardActionPerformed

        if (_players.get(3).getRaisedCards() == 6) {
            _players.get(3).discard();
            _players.get(3).orderCards();
            _players.get(3).setLocationOffset(10, 560, 50, 0);
            _players.get(3).turnOver();
            btnDiscard.setVisible(false);
            btnPlay.setLocation(btnDiscard.getLocation().x, btnDiscard.getLocation().y - 40);
            btnPlay.setVisible(true);
            lblInstructions.setText("Player 4");
        } else {
            lblInstructions.setText("Please Select 6 Cards to Discard!");
        }
    }//GEN-LAST:event_btnDiscardActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnPlayActionPerformed

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

    /**
     *
     */
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
        if (_discard == false) {
            return;
        }
        //System.out.println("Handle Card Pick Called");
        Point loc = e.getPoint(); // get coordinates of mouse event in panel
        Card card = (Card) e.getSource();
        if (_players.get(3).hasCard(card) == true) {
            card.toggleRaised();
        }
//        System.out.println("Card Suite: " + card.getSuit().toString() + "\nCard Rank: " + card.getRank().toString());
        loc.x = loc.x + card.getX();;
        loc.y = loc.y + card.getY();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBid;
    private javax.swing.JButton btnDiscard;
    private javax.swing.JButton btnPass;
    private javax.swing.JButton btnPlay;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblInstructions;
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JLabel lblPlayer2;
    private javax.swing.JLabel lblPlayer3;
    private javax.swing.JSlider sldBid;
    // End of variables declaration//GEN-END:variables
}
