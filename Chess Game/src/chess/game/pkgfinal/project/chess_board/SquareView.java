package chess.game.pkgfinal.project.chess_board;

import chess.game.pkgfinal.project.models.Square;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * SquareView.java - This class creates a square with alternate colour combinations
 *
 * @author Abhishek Shah
 */
public class SquareView extends Rectangle2D.Double {

    public static final int squareWidth = 80;
    public static final int squareHeight = 80;
    private final Color dark = new Color(181, 136, 99); //Colour of dark square
    private final Color light = new Color(240, 217, 181);//Colour of light square
    private final Square square;

    /**
     * Renders the squares with their designated heights and widths
     * @param square represents one square
     */
    public SquareView(Square square) {
        this.square = square;

        int x = column2X(square.getCoordinate().getColumn());
        int y = row2Y(square.getCoordinate().getRow());

        setRect(x, y, squareWidth, squareHeight);
    }

    /**
     * This the colour of the board either dark or light
     * @return the square's colour depending what colour is it
     */
    public Color getColor() {
        return square.getColor() == Square.Color.Dark ? dark : light;
    }

    // SOURCE CODE//
    public static char x2Column(int x) {
        int between = x == 0 ? 0 : x / squareWidth;
        return (char)(between + 97);
    }

    // SOURCE CODE//
    public static int y2Row(int y) {
        int between = y == 0 ? 0 : y / squareHeight;
        return (between - 8) * -1;
    }

    // SOURCE CODE//
    public static int column2X(char column) {
        return ((int)column - 97) * squareWidth;
    }

    // SOURCE CODE//
    public static int row2Y(int row) {
        return (row - 8) * -1 * squareHeight;
    }
}
