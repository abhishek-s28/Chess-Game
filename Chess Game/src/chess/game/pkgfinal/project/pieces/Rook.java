package chess.game.pkgfinal.project.pieces;

import chess.game.pkgfinal.project.chess_board.Board;
import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import java.util.ArrayList;

/**
 * Rook.java - This is a rook class where it can move in either vertical or 
 * horizontal in direction.
 *
 * @author Abhishek Shah
 */
public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    /**
     * The possible coordinates where rook can move
     * @return possible destinations of rook (vertical and horizontal)
     */
    @Override
    public ArrayList<Coordinate> getPossibleCoordinates() {
        ArrayList<Coordinate> destinations = new ArrayList<>();

        // Can move in horizontally or vertically
        for (Coordinate.Direction direction : Coordinate.Direction.lines()) {
            for (Coordinate c : coordinate.getByDirection(direction)) {
                Piece piece = Board.getInstance().getPiece(c);

                // If the square is empty it can move
                if (piece == null) {
                    destinations.add(c);
                }
                else if (piece.getColor() != COLOUR) {
                    destinations.add(c);
                    break;
                }
                else {
                    break;
                }
            }
        }
        return destinations;
    }  
}