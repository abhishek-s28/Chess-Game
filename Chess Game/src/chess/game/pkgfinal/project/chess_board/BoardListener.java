package chess.game.pkgfinal.project.chess_board;

import chess.game.pkgfinal.project.models.Piece;

/**
 * BoardListener.java - Interface class to create the which player is in check 
 * or checkmate and the colour also to restart the game
 *
 * @author Abhishek Shah
 */
public interface BoardListener {
    
    /**
     * The player is changed from which colour to which
     * @param color of the player
     */
    void playerChanged(Piece.Color color);
    
    /**
     * Checks for player in check
     * @param color which colour player is in check
     */
    void playerInCheck(Piece.Color color);
    
    /**
     * Which player is in checkmate position
     * @param color of the player
     */
    void playerInCheckmate(Piece.Color color);
    
    /**
     * Creates new game
     */
    void newGame();
}