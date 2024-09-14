package chess.game.pkgfinal.project.pieces;

import chess.game.pkgfinal.project.chess_board.Board;
import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import java.util.ArrayList;

/**
 * Bishop.java - This is a bishop class where it is only allowed to move 
 * diagonally. 
 *
 * @author Abhishek Shah
 */
public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    /**
     * Gets which squares can bishop move to
     * @return possible places for bishop
     */
    @Override
    public ArrayList<Coordinate> getPossibleCoordinates() {
        
        //SOURCE CODE, Stackoverflow, and other websites to figure out the code//
        
        ArrayList<Coordinate> destinations = new ArrayList<>();

        // Only allowed to move diagonally
        for (Coordinate.Direction direction : Coordinate.Direction.diagonals()) {
            for (Coordinate c : coordinate.getByDirection(direction)) {
                Piece piece = Board.getInstance().getPiece(c);

                //Checks if the squares are empty
                if (piece == null) {
                    destinations.add(c);
                }
                //If the square containes opponent's piece it can move 
                //but not any further
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