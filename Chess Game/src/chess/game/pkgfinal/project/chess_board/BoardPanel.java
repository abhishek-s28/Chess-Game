package chess.game.pkgfinal.project.chess_board;

import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import chess.game.pkgfinal.project.models.Square;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * BoardPanel.java - This class implements mouseListener for a click on square
 * and also implement boardListener, It draw a circle and highlights the square
 * selected.
 *
 * @author Abhishek Shah
 */
public class BoardPanel extends JPanel implements MouseListener, BoardListener {

    public BoardPanel() {
        addMouseListener(this);
        Board.getInstance().addListener(this);
        setPreferredSize(new Dimension(8 * SquareView.squareWidth, 
                8 * SquareView.squareHeight));
    }

    /**
     * It paints the board wit the piece, possible coordinates with circles
     * and the the selected coordinate with light blue colour
     * @param g represents the graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // SOURCE CODE//
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, 
                RenderingHints.VALUE_RENDER_QUALITY);

        for (int row = 8; row >= 1; row--) {
            for (char column = 'a'; column <= 'h'; column++) {
                Coordinate c = new Coordinate(column, row);

                // Square
                Square square = Board.getInstance().getSquare(c);
                SquareView rect = new SquareView(square);
                g2.setPaint(rect.getColor());
                g2.fill(rect);

                // Piece
                Piece piece = Board.getInstance().getPiece(c);
                if (piece != null) {
                    g2.drawImage(
                            piece.getImage(), 
                            (int) rect.getX() + 2, 
                            (int) rect.getY() + 2, 
                            76, 
                            76, 
                            this
                    );
                }
            }
        }

        Coordinate selection = Board.getInstance().getSelection();
        if (selection != null) {
            // Selected box colours
            g2.setColor(new Color(19, 31, 84, 70));
            g2.fillRect(SquareView.column2X(selection.getColumn()),
                    SquareView.row2Y(selection.getRow()),
                    SquareView.squareWidth,
                    SquareView.squareHeight);

            // Dot displays where possible piece could move to
            Piece piece = Board.getInstance().getPiece(selection);
            showPossibleSquares(piece, g2);
        }
        g2.dispose();
    }

    /**
     * Shows the possible squares where piece could  move
     * @param piece represents player's piece 
     * @param g2 is the graphics to fill circles for possible moves
     */
    private void showPossibleSquares(Piece piece, Graphics2D g2) {
        g2.setColor(new Color(19, 31, 84, 199)); 
        ArrayList<Coordinate> possibleSquares = piece.getPossibleMoves();
        for (Coordinate c : possibleSquares) {
            g2.fillOval(SquareView.column2X(c.getColumn()) + 20, 
                    SquareView.row2Y(c.getRow()) + 20, 40, 40);
        }
    }

    @Override
    public void mouseClicked(MouseEvent eventPosition) {
        
        char column = SquareView.x2Column(eventPosition.getX());
        int row = SquareView.y2Row(eventPosition.getY());
        Coordinate clickedSquare = new Coordinate(column, row);

        if (clickedSquare.isValid()) {
            // Check if a clicked square is a possible move
            Coordinate selection = Board.getInstance().getSelection();
            if (selection != null && Board.getInstance()
                    .getPiece(selection).canMoveTo(clickedSquare)) {
                Board.getInstance().movePiece(selection, clickedSquare);
            }
            else {
                Board.getInstance().setSelection(new Coordinate(column, row));
            }
            repaint();
        }
    }

    // This methods can stay empty
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void playerChanged(Piece.Color color) {

    }

    @Override
    public void playerInCheck(Piece.Color color) {

    }

    @Override
    public void playerInCheckmate(Piece.Color color) {

    }

    /**
     * Restarts or starts brand new game of chess
     */
    @Override
    public void newGame() {
        repaint();
    }
}

