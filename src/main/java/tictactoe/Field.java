package tictactoe;

import static java.util.Objects.requireNonNull;

/**
 * Represents a field which can be occupied by a {@link Player}.
 * Each field is represented with a coordinate consisting of the X-Axis and Y-Axis defined as A-C and 1-3.
 * 
 * Examples:
 *      Field top left has the coordinate A1
 *      Field in the middle has the coordinate B2
 *      Field bottom right has coordinate C3
 */
public class Field {

    private Player occupiedByPlayer;
    private final String coordinate;

    /**
     * Creates a new Field object using the passed coordinate.
     *
     * @param coordinate the coordinate associated to this field
     */
    public Field(String coordinate) {
        this.coordinate = requireNonNull(coordinate);
    }

    /**
     * Sets the {@link Player} who occupies this field.
     * 
     * @param player the player who occupies this field
     *
     */
    public void setOccupiedByPlayer(Player player) {
        occupiedByPlayer = player;
    }

    /**
     * Returns the {@link Player} that occupies this field.
     *
     * @return the {@link Player} that occupies this field
     */
    public Player getOccupiedByPlayer() {
        return occupiedByPlayer;
    }

    /**
     * Returns the coordinate of the field.
     * 
     * @return the coordinate of the field
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * Returns the symbol of the {@link Player} who is currently occupying this.
     *
     * @return The symbol dedicated to the {@link Player} occupying this, or an empty {@code String} if this is
     *         currently unoccupied.
     */
    public char getSymbolIfOccupied() {
        return occupiedByPlayer != null
                ? occupiedByPlayer.getSymbol()
                : ' ';
    }

}
