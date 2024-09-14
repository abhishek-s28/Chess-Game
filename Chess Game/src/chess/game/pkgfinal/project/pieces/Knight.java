package chess.game.pkgfinal.project.pieces;

import chess.game.pkgfinal.project.chess_board.Board;
import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import java.util.ArrayList;

/**
 * Knight.java - This is a knight class where it is only allowed to move 
 * 2 vertically and 1 horizontally or 1 vertically and 2 horizontally.
 *
 * @author Abhishek Shah
 */
public class Knight extends Piece {
    
    /**
     * The colour of knight is it black or white
     * @param color of the piece
     */
    public Knight(Color color) {
        super(color);
    }

    /**
     * The possible coordinates where knight can move
     * @return possible destinations of knight
     */
    @Override
    public ArrayList<Coordinate> getPossibleCoordinates() {  // Possible knight
        ArrayList<Coordinate> destinations = new ArrayList<>(); //       moves
        for (Coordinate c : coordinate.knightMoves()) {
            Piece piece = Board.getInstance().getPiece(c);
            // Cannot move to same colour piece
            if (piece == null || piece.getColor() != COLOUR) {
                destinations.add(c);
            }
        }
        return destinations;
    }
}