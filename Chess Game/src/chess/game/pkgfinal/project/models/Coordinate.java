package chess.game.pkgfinal.project.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Coordinate.java - Organizing columns and rows in the chess board
 *
 * @author Abhishek Shah
 */
public class Coordinate {
    
    private final char column;       // column : horizontal : a to h
    private final int row;           // row : vertical 1 to 8

    /**
     * Enumeration to calculate how many squares 
     */
    public enum Direction {
                                            
        N(0, 1), E(1, 0), W(-1, 0), S(0, -1), // Horizontal and vertical lines
        NW(-1, 1), NE(1, 1), SW(-1, -1), SE(1, -1);           // Diagnol lines

        private final int dColumn;
        private final int dRow;

        /**
         * Direction of columns abd rows
         * @param dColumn represents the column
         * @param dRow represents the row
         */
        Direction(int dColumn, int dRow) {
            this.dColumn = dColumn;
            this.dRow = dRow;
        }

        /**
         * 4 directions that are straight
         * @return directions in up, down, left, right
         */
        public static Direction[] lines() {
            return new Direction[] {N, S, E, W};
        }

        /**
         * 4 directions that are diagonal
         * @return directions in topRight, topLeft, bottomRight, bottomLeft
         */
        public static Direction[] diagonals() {
            return new Direction[] {NW, NE, SW, SE};
        }
    }

    /**
     * Coordinate is created with columns and rows
     * @param column
     * @param row 
     */
    public Coordinate(char column, int row) {
        this.column = column;
        this.row = row;
    }

    public Coordinate(String c) {
        this.column = c.charAt(0);
        this.row = Character.getNumericValue(c.charAt(1));
    }

    /**
     * Gets and return column
     * @return column
     */
    public char getColumn() {
        return column;
    }

    /**
     * Returns the row
     * @return row 
     */
    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return String.format("%s%s", column, row);
    }

    // Created this method with the help of source code //
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coordinate that = (Coordinate) object;
        return column == that.column &&
                row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    /**
     * This get the first/forward line from the pieces from their starting
     * point.
     * 
     * @param color of the player/piece.
     * @return if the colour is white add the number if not subtract the number.
     */
    public Coordinate getForwardLine(Piece.Color color, int num) {
        if (color == Piece.Color.White) {
            return new Coordinate(column, row + num);
        }
        else {
            return new Coordinate(column, row - num);
        }
    }

    /**
     * This is mainly used for queen, king, and bishop pieces.
     * @param color of player/piece.
     * @return the diagonal coordinates by some math calculations.
     */
    public ArrayList<Coordinate> getForwardDiagonal(Piece.Color color) {
        CoordinateList coordinates = new CoordinateList();

        if (color == Piece.Color.White) {
            coordinates.add(new Coordinate((char)(column + 1), row + 1));
            coordinates.add(new Coordinate((char)(column - 1), row + 1));
        }
        else {
            coordinates.add(new Coordinate((char)(column + 1), row - 1));
            coordinates.add(new Coordinate((char)(column - 1), row - 1));
        }
        return coordinates;
    }

    /**
     * Tells coordinates of the square in the requested direction of piece
     * @return list of coordinates
     */
    public ArrayList<Coordinate> getByDirection(Direction direction) {
        return getByDirection(direction, 9);
    }

    /**
     * Tells coordinates up to the end of the chess board
     * @param direction direction wanted depending upon piece.
     * @param limit how many coordinates are free to move
     * @return list of coordinates up to end of the chess board
     */
    public ArrayList<Coordinate> getByDirection(Direction direction, int limit) {
        CoordinateList squares = new CoordinateList();

        int i = 1;
        Coordinate c;
        do {
            c = new Coordinate((char) (column + i * direction.dColumn), 
                    row + i * direction.dRow);
            i++;
        } while (squares.add(c) && i < limit);
        return squares;
    }

    /**
     * Knight has four possible moves which are are 8 directions.
     * @return knight moves where possible square could be occupied by knight
     */
    public ArrayList<Coordinate> knightMoves() {
        CoordinateList squares = new CoordinateList();

        squares.add(new Coordinate((char)(column + 1), row + 2));
        squares.add(new Coordinate((char)(column + 1), row - 2));
        squares.add(new Coordinate((char)(column - 1), row + 2));
        squares.add(new Coordinate((char)(column - 1), row - 2));
        squares.add(new Coordinate((char)(column + 2), row + 1));
        squares.add(new Coordinate((char)(column + 2), row - 1));
        squares.add(new Coordinate((char)(column - 2), row + 1));
        squares.add(new Coordinate((char)(column - 2), row - 1));

        return squares;
    }

    /**
     * @return valid moves between columns and rows 
     */
    public boolean isValid() {
        return column >= 'a' && column <= 'h' && row >= 1 && row <= 8;
    }

    /**
     * Little bit of source code used
     * Gives coordinates between two same colours
     * @param other other box
     * @return coordinates between two same colours
     */
    public ArrayList<Coordinate> between(Coordinate other) {
        CoordinateList coordinates = new CoordinateList();

        if (column == other.getColumn() || row == other.getRow() 
            || column - other.getColumn() == row - other.getRow()) {

            int direction = 
                    column > other.getColumn() || row > other.getRow() ? -1 : 1;

            for (int i = 1; i < 8; i++) {
                char newColumn = other.getColumn() != column 
                        ? (char)(column + i * direction) : column;
                int newRow = other.getRow() != row ? row + i * direction : row;
                Coordinate c = new Coordinate(newColumn, newRow);

                if (c.equals(other)) break;

                coordinates.add(c);
            }
        }
        return coordinates;
    }

    /**
     * This is the last row
     * @param color set colour alternately
     * @return the colours depending on last row
     */
    public boolean isLastRow(Piece.Color color) {
        return color == Piece.Color.Black && row == 1 
            || color == Piece.Color.White && row == 8;
    }

    /**
     * This is the first row
     * @param color set colour alternately
     * @return the colours depending on first row
     */
    public static int getFirstRow(Piece.Color color) {
        return color == Piece.Color.Black ? 8 : 1;
    }
}

