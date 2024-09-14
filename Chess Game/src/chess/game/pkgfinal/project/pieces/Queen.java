package chess.game.pkgfinal.project.pieces;

import chess.game.pkgfinal.project.chess_board.Board;
import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import java.util.ArrayList;

/**
 * Queen.java - This is a queen class where it can move in any direction.
 *
 * @author Abhishek Shah
 */
public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    /**
     * The possible coordinates where queen can move
     * @return possible destinations of queen (pretty much 
     * everywhere, except can't jump over a piece)
     */
    @Override
    public ArrayList<Coordinate> getPossibleCoordinates() {
        ArrayList<Coordinate> destinations = new ArrayList<>();

        // Can move as many steps in any direction
        for (Coordinate.Direction direction : Coordinate.Direction.values()) {
            for (Coordinate c : coordinate.getByDirection(direction)) {
                Piece piece = Board.getInstance().getPiece(c);

                // If the square is empty it can move
                if (piece == null) {
                    destinations.add(c);
                }
                // It can only until opponent's piece, cannot jump over
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
