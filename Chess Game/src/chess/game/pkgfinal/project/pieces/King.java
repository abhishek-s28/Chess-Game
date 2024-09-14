package chess.game.pkgfinal.project.pieces;

import chess.game.pkgfinal.project.chess_board.Board;
import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import java.util.ArrayList;

/**
 * King.java - This is a King class where it is only allowed to move 
 * one step in any direction. 
 *
 * @author Abhishek Shah
 */
public class King extends Piece {
    
    /**
     * Colour of the king
     * @param color of player
     */
    public King(Color color) {
        super(color);
    }

    /**
     * List of the possible king coordinates to move
     * @return possible destinations with limited to one move.
     */
    @Override
    public ArrayList<Coordinate> getPossibleCoordinates() {
        ArrayList<Coordinate> destinations = new ArrayList<>();

        // Any direction 
        for (Coordinate.Direction direction : Coordinate.Direction.values()) {
            for (Coordinate c : coordinate.getByDirection(direction, 1)) {
                Piece piece = Board.getInstance().getPiece(c);

                // It can move in a square where it is oppenent's piece or empty
                if (piece == null || piece.getColor() != COLOUR) {
                    destinations.add(c);
                }
            }
        }
        return destinations;
    }
}

