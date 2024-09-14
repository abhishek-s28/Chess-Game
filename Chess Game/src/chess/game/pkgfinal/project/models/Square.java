package chess.game.pkgfinal.project.models;

/**
 * Square.java - Creates one square which would be either light or dark and
 * get the colour of it alternatively.
 *
 * @author Abhishek Shah
 */
public class Square {
    
    private final Color color;
    private final Coordinate coordinate;
    private Piece piece;
    private boolean selected = false;

    public enum Color {
        Light, Dark
    }

    /**
     * Creates a square
     * @param color alternates in different squares
     * @param coordinate is a coordinate 
     */
    public Square(Color color, Coordinate coordinate) {
        this.color = color;
        this.coordinate = coordinate;
    }

    /**
     * @return coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * It piece selected
     * @return selected piece
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Which piece/square is selected
     * @param selected piece/square
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Returns the piece
     * @return piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets piece at its starting position
     * @param piece sets the on coordinate scale
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null)
            piece.setCoordinate(coordinate);
    }
    
    /**
     * Removes piece 
     */
    public void removePiece() {
        piece = null;
    }

    /**
     * Accessor method of colour
     * @return colour
     */
    public Color getColor() {
        return color;
    }

    /**
     * Checks if the coordinate is empty
     * @return nothing if there is no piece
     */
    public boolean isEmpty() {
        return piece == null;
    }
}