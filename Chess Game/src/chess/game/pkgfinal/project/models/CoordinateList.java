package chess.game.pkgfinal.project.models;

import java.util.ArrayList;

/**
 * CoordinateList.java - Array of coordinates on board 
 *
 * @author Abhishek Shah
 */
public class CoordinateList extends ArrayList<Coordinate> {

    /**
     * Adds coordinates
     * @param coordinate to add
     * @return added coordinates
     */
    @Override
    public boolean add(Coordinate coordinate) {
        if (coordinate.isValid())
            return super.add(coordinate);
        return false;
    }
}

