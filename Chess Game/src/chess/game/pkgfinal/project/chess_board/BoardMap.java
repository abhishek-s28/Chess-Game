package chess.game.pkgfinal.project.chess_board;

import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import chess.game.pkgfinal.project.models.Square;
import chess.game.pkgfinal.project.pieces.Queen;
import chess.game.pkgfinal.project.pieces.Pawn;
import java.util.HashMap;

/**
 * BoardMap.java - Layout of the chess game
 *
 * @author Abhishek Shah
 */
public class BoardMap extends HashMap<String, Square> {
    
    /**
     * This methods gets the coordinate and outputs as string
     * @param coordinate gives the actual coordinate
     * @return the coordinate as string
     */
    public Square get(Coordinate coordinate) {
        return get(coordinate.toString());
    }

    // SOURCE CODE //
    public void put(Coordinate coordinate, Square square) {
        put(coordinate.toString(), square);
    }

    /**
     * Accessor method
     * @param coord coordinates
     * @return coordinate name with piece
     */
    public Piece getPiece(String coord) {
        return get(coord).getPiece();
    }

    public Piece getPiece(Coordinate coord) {
        return getPiece(coord.toString());
    }

    /**
     * If pawn reaches end of the board spawn another queen
     * @param from coordinate of pawn
     * @param destination end of the board
     */
    public void movePiece(Coordinate from, Coordinate destination) {
        Piece piece = getPiece(from);

        
        //If pawn touches other end of the board, the player gets the queen
        if (piece.getClass() == Pawn.class && 
                destination.isLastRow(piece.getColor())) {
            piece = new Queen(piece.getColor());
        }
        
        get(from).removePiece();
        get(destination).setPiece(piece);
    }
}
