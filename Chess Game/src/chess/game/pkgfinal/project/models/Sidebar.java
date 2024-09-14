package chess.game.pkgfinal.project.models;

import chess.game.pkgfinal.project.chess_board.Board;
import chess.game.pkgfinal.project.chess_board.BoardListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Sidebar.java - This class creates a sidebar beside the chess layout to let user
 * know who's turn is it and if it is check, checkmate, needs help, and restart.
 *
 * @author Abhishek Shah
 * @since Jun-11-2022
 */
public class Sidebar extends JPanel implements BoardListener {

    private final JLabel player = new JLabel("White's turn");
    private final JLabel state = new JLabel();
    private final JButton btnNewGame = new JButton("Restart");
    private final JButton btnInfo = new JButton("?");

    public Sidebar() {
        JPanel texts = new JPanel();
        texts.setLayout(new GridLayout(2, 1));
        texts.add(player);
        texts.add(state);

        player.setFont(player.getFont().deriveFont(Font.PLAIN, 16));
        state.setFont(state.getFont().deriveFont(Font.BOLD, 16));

        Board.getInstance().addListener(this);

        //Changing and adding properties to some buttons
        btnNewGame.addActionListener(new ButtonListener());
        btnNewGame.setPreferredSize(new Dimension(140, 40));
        btnNewGame.setFont(btnNewGame.getFont().deriveFont(Font.PLAIN, 16));
        btnNewGame.setBorderPainted(true);

        btnInfo.addActionListener(new ButtonListener());
        btnInfo.setPreferredSize(new Dimension(40, 40));
        btnInfo.setFont(btnInfo.getFont().deriveFont(Font.PLAIN, 16));

        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setPreferredSize(new Dimension(250, 100));

        JPanel buttons = new JPanel();
        buttons.add(btnNewGame);
        buttons.add(btnInfo);

        setLayout(new BorderLayout());
        add(texts, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);
    }

    /**
     * Let's user know who's turn is it
     * 
     * @param color of the player
     */
    @Override
    public void playerChanged(Piece.Color color) {
        player.setText(color.translate() + "'s turn");
        state.setText("");
    }

    /**
     * Notifies which user is in check
     * @param color of the player
     */
    @Override
    public void playerInCheck(Piece.Color color) {
        state.setText(color.translate() + " it's check");
    }

    /**
     * Notifies which user is checkmate
     * @param color of the player
     */
    @Override
    public void playerInCheckmate(Piece.Color color) {
        player.setText("");
        state.setText(color.translate() + " it's checkmate!");
    }

    @Override
    public void newGame() {
        // It can stay empty
    }

    /**
     * Listens for which button is clicked
     */
    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent buttonClicked) {
            if (buttonClicked.getSource().equals(btnNewGame))
                Board.getInstance().newGame();
            else if (buttonClicked.getSource().equals(btnInfo)) {
                JOptionPane.showMessageDialog(null, 
                "How to play: \n\n"                                       +
                "Pawn: -> moves forward only one square unless it "       +
                         "is that pawn’s first move. \n\n"                +
                "Rook: -> moves forward, backward or sideways, but "      +
                         "cannot move diagonally.\n\n"                    +
                "Bishop: -> moves only diagonally in any directions.\n\n" +
                "Knight: -> moves in L-Shape directions.\n\n"             +
                "Queen: -> moves in any square in all direction. \n\n"    + 
                "King: -> can move only one step in any direction.",
                "Help",
                JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}