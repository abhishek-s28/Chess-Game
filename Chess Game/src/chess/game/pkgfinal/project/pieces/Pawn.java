package chess.game.pkgfinal.project.pieces;

import chess.game.pkgfinal.project.chess_board.Board;
import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import java.util.ArrayList;

/**
 * Pawn.java - This is a pawn class where it can move either 1 or 2 spots
 * if it is not yet moved else only one spot it can move.
 *
 * @author Abhishek Shah
 */
public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    /**
     * The possible coordinates where pawn can move
     * @return possible destinations of pawn
     */
    @Override
    public ArrayList<Coordinate> getPossibleCoordinates() {
        ArrayList<Coordinate> destinations = new ArrayList<>();

        // Checks square in front, if it is empty
        Coordinate newCoord = coordinate.getForwardLine(COLOUR, 1);
        if (Board.getInstance().isEmpty(newCoord)) {
            destinations.add(newCoord);
        }

        // If its first move for pawn it can move 1 or 2 squares in front
        newCoord = coordinate.getForwardLine(COLOUR, 2);
        if (isInitialPosition() && Board.getInstance().isEmpty(newCoord)) {
            destinations.add(newCoord);
        }

        // It can only move diagnollay it the piece is in different colour 
        for (Coordinate c : coordinate.getForwardDiagonal(COLOUR)) {
            Piece piece = Board.getInstance().getPiece(c);
            if (piece != null && piece.getColor() != COLOUR) {
                destinations.add(c);
            }
        }
        return destinations;
    } 
}

