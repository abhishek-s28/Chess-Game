package chess.game.pkgfinal.project.chess_board;

import chess.game.pkgfinal.project.models.Coordinate;
import chess.game.pkgfinal.project.models.Piece;
import chess.game.pkgfinal.project.models.Square;
import chess.game.pkgfinal.project.pieces.Queen;
import chess.game.pkgfinal.project.pieces.Rook;
import chess.game.pkgfinal.project.pieces.King;
import chess.game.pkgfinal.project.pieces.Pawn;
import chess.game.pkgfinal.project.pieces.Knight;
import chess.game.pkgfinal.project.pieces.Bishop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Board.java - This class creates a square with alternate colour combinations
 *
 * @author Abhishek Shah
 */
public class Board {
    private static final Board instance = new Board();
    private BoardMap squares;
    private Piece.Color currentPlayer = Piece.Color.White;
    private Coordinate selection;  
    private final List<BoardListener> listeners = new ArrayList<>();
    private HashMap<Piece.Color, King> kings; // SOURCE CODE //

    /**
     * Creates new game and also starts new game
     */
    private Board() {
        newGame();
    }

    public static Board getInstance() {
        return instance;
    }

    /**
     * This is the initial board design before starting the game
     */
    private void initBoard() {
        for (char column = 'a'; column <= 'h'; column++) {
            for (int row = 1; row <= 8; row++) {
                Coordinate c = new Coordinate(column, row);
                Square square = new Square((column + row) % 2 == 0 ? 
                        Square.Color.Dark : Square.Color.Light, c);
                squares.put(c, square);
            }
        }
    }

    /**
     * If you restart the piece gets arranged to their designated coordinates
     */
    private void initPieces() {
        // King is the main point 
        kings.put(Piece.Color.White, new King(Piece.Color.White));
        kings.put(Piece.Color.Black, new King(Piece.Color.Black));

        // White pieces
        squares.get("a1").setPiece(new Rook(Piece.Color.White));
        squares.get("b1").setPiece(new Knight(Piece.Color.White));
        squares.get("c1").setPiece(new Bishop(Piece.Color.White));
        squares.get("d1").setPiece(new Queen(Piece.Color.White));
        squares.get("e1").setPiece(kings.get(Piece.Color.White));
        squares.get("f1").setPiece(new Bishop(Piece.Color.White));
        squares.get("g1").setPiece(new Knight(Piece.Color.White));
        squares.get("h1").setPiece(new Rook(Piece.Color.White));

        // Sets all white pawns on column 2
        for (char column = 'a'; column <= 'h'; column++) {
            squares.get(new Coordinate(column, 2)).setPiece(new Pawn(Piece.Color.White));
        }

        // Black pieces
        squares.get("a8").setPiece(new Rook(Piece.Color.Black));
        squares.get("b8").setPiece(new Knight(Piece.Color.Black));
        squares.get("c8").setPiece(new Bishop(Piece.Color.Black));
        squares.get("d8").setPiece(new Queen(Piece.Color.Black));
        squares.get("e8").setPiece(kings.get(Piece.Color.Black));
        squares.get("f8").setPiece(new Bishop(Piece.Color.Black));
        squares.get("g8").setPiece(new Knight(Piece.Color.Black));
        squares.get("h8").setPiece(new Rook(Piece.Color.Black));

        // Sets all black pawns on column 7
        for (char column = 'a'; column <= 'h'; column++) {
            squares.get(new Coordinate(column, 7)).setPiece(new Pawn(Piece.Color.Black));
        }
    }

    /**
     * Game restarts
     */
    public void newGame() {
        currentPlayer = Piece.Color.White;
        selection = null;
        kings = new HashMap<>();
        squares = new BoardMap();

        initBoard();
        initPieces();

        // Notify
        for (BoardListener listener : listeners) {
            listener.newGame();
        }
    }

    /**
     * Gets the current player
     * @return current player white or black
     */
    public Piece.Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Which will be next player 
     */
    public void nextPlayer() {
        currentPlayer = currentPlayer.toggle();

        // Notify
        for (BoardListener listener : listeners) {
            listener.playerChanged(currentPlayer);
        }

        if (isInCheck(currentPlayer)) {
            if (isInCheckmate(currentPlayer)) {
                // Notify
                for (BoardListener listener : listeners) {
                    listener.playerInCheckmate(currentPlayer);
                }
            }
            else {
                // Notify
                for (BoardListener listener : listeners) {
                    listener.playerInCheck(currentPlayer);
                }
            }
        }
    }

    /**
     * Moves piece from one place to another
     * @param from one departure
     * @param to arrival
     */
    public void movePiece(Coordinate from, Coordinate to) {
        squares.movePiece(from, to);
        selection = null;
        nextPlayer();
    }

    /**
     * If square is empty
     * @param c = coordinate
     * @return squares that are empty
     */
    public boolean isEmpty(Coordinate c) {
        return squares.get(c).isEmpty();
    }

    /**
     * Gets piece on which square
     * @param c = coordinate
     * @return squares with piece
     */
    public Piece getPiece(Coordinate c) {
        return squares.get(c).getPiece();
    }

    public Square getSquare(Coordinate c) {
        return squares.get(c);
    }

    /**
     * Gets the selected coordinate
     * @return selected coordinate
     */
    public Coordinate getSelection() {
        return selection;
    }

    /**
     * Sets the selection on the piece that was clicked
     * @param selection which piece was selected, colour of them
     */
    public void setSelection(Coordinate selection) {
        if (this.selection != null && this.selection.equals(selection) ||
                isEmpty(selection) ||
                getPiece(selection).getColor() != getCurrentPlayer()) {
            selection = null;
        }

        this.selection = selection;
    }

    /**
     * Is the king in check
     * @return boolean yes or no
     */
    public boolean isInCheck(Piece.Color player) {
        Coordinate kingCoordinate = kings.get(player).getCoordinate();

        for (Map.Entry<String, Square> entry : squares.entrySet()) {
            Square square = entry.getValue();
            Piece piece = square.getPiece();
            if (piece != null && piece.getColor() 
                    != player && piece.canMoveTo(kingCoordinate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Map of chessboard
     * @return squares in chessboard
     */
    public BoardMap getSquares() {
        return squares;
    }

    
    /**
     * Is it checkmate or not
     *
     * @param player to check
     * @return boolean yes or no checkmate
     */
    public boolean isInCheckmate(Piece.Color player) {
        
        // SOURCE CODE // used for this method
        
        // If king has space to move its not checkmate
        if (!kings.get(player).getPossibleMoves().isEmpty()) {
            return false;
        }

        Coordinate kingCoordinate = kings.get(player).getCoordinate();
        ArrayList<Piece> attackers = new ArrayList<>();
        for (Map.Entry<String, Square> entry : squares.entrySet()) {
            Square square = entry.getValue();
            Piece piece = square.getPiece();
            if (piece != null && piece.getColor() 
                    != player && piece.canMoveTo(kingCoordinate)) {
                attackers.add(piece);
            }
        }

        // If there is only one opponent piece
        if (attackers.size() == 1) {
            Piece attacker = attackers.get(0);
            Coordinate attackerCoordinate = attacker.getCoordinate();

            for (Map.Entry<String, Square> entry : squares.entrySet()) {
                Square square = entry.getValue();
                Piece piece = square.getPiece();

                // Cannot move at teammate's spot
                if (piece != null && piece.getColor() == player) {
                    if (piece.getPossibleMoves().contains(attackerCoordinate)) {
                        return false;
                    }
                    //More scenarios for checking...
                    if (attacker.getClass() != Knight.class) {
                        ArrayList<Coordinate> possibleMoves = 
                                piece.getPossibleMoves();
                        ArrayList<Coordinate> squaresBetween = 
                                attackerCoordinate.between(kingCoordinate);
                        possibleMoves.retainAll(squaresBetween);
                        if (!possibleMoves.isEmpty()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Gets which colour is king
     * @param color is it black or white
     * @return the colour of king
     */
    public King getKing(Piece.Color color) {
        return kings.get(color);
    }

    /**
     * Listens for any click 
     * @param listener for mouse click on chess board
     */
    public void addListener(BoardListener listener) {
        listeners.add(listener);
    }
}