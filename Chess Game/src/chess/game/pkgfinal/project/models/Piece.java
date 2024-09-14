package chess.game.pkgfinal.project.models;

import chess.game.pkgfinal.project.chess_board.Board;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Piece.java - Abstract class to define general characteristics of 
 * pieces in chess also to load an image of each piece.
 *
 * @author Abhishek Shah
 */
public abstract class Piece {
    
    protected Coordinate coordinate;
    protected final Color COLOUR;
    protected Coordinate initialPosition;

    /**
     * This methods toggles between black and white players
     */
    public enum Color {
        Black, White;

        /**
         * Switches between colours
         * @return opposite colours after each player's turn
         */
        public Color toggle() {
            return this == White ? Black : White; 
        }

        /**
         * Tells which player's colour
         * @return player's colour 
         */
        public String translate() { 
            return this == White ? "White" : "Black";  
        }
    }

    /**
     * Sets the colour of the piece
     * @param color of the piece/player
     */
    public Piece(Color color) {
        this.COLOUR = color;
    }

    /**
     * Sets the all coordinate if the initial position equals null 
     * @param coordinate represents the coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        if (initialPosition == null)
            initialPosition = coordinate;
        this.coordinate = coordinate;
    }
    
    /**
     * Returns the coordinate
     * @return coordinate 
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @return colour 
     */
    public Color getColor() {
        return COLOUR;
    }

    /**
     * This is an abstract method that returns possible coordinate moves
     * @return possible coordinates of movement depending upon pieces
     */
    protected abstract ArrayList<Coordinate> getPossibleCoordinates();

    /**
     * //SOURCE CODE// Also some help of stackOverflow
     * 
     * This method tells possible moves of coordinates depending on the pieces
     * @return the possible moves
     */
    public ArrayList<Coordinate> getPossibleMoves() {
        Coordinate from = coordinate;
        CoordinateList destinations = new CoordinateList();

        for (Coordinate to : getPossibleCoordinates()) {
            
            // Backup
            Piece fromPiece = Board.getInstance().getPiece(from);
            Piece toPiece = Board.getInstance().getPiece(to);

            // Move
            Board.getInstance().getSquare(from).removePiece();
            Board.getInstance().getSquare(to).setPiece(fromPiece);

            if (!Board.getInstance().isInCheck(COLOUR)) {
                destinations.add(to);
            }

            // Cancels to move
            Board.getInstance().getSquare(from).setPiece(fromPiece);
            Board.getInstance().getSquare(to).setPiece(toPiece);
        }
        return destinations;
    }

    /**
     * Checks if the piece if able to move to different destination
     * @param destination ending point of the destination
     * @return the possible coordinates up to player's destination
     */
    public boolean canMoveTo(Coordinate destination) {
        return getPossibleCoordinates().contains(destination);
    }

    /**
     * Checker for initial position mainly used for pawn
     * @return if initial position equals coordinate
     */
    public boolean isInitialPosition() {
        return initialPosition.equals(coordinate);
    }

    /**
     * Created this method with the help of stackOverflow
     * Images of the pieces
     * @return the image of each piece
     */
    public Image getImage() {
        Image img = null;
        try {
            String filename = COLOUR.toString().toLowerCase().charAt(0) 
                    + "-" + getClass().getSimpleName().toLowerCase();
            InputStream imagePath = Piece.class.getResourceAsStream
                                    ("/images/" + filename + ".png");
            img = ImageIO.read(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}

