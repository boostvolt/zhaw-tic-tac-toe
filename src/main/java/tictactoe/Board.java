package tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board which contains all the fields.
 * It offers methods to fetch fields by coordinates or players.
 */
public class Board {

    private final List<Field> fields;

    /**
     * Creates a new Board.
     * Instantiates the List fields and adds 9 {@link Field} objects to it, giving each a dedicated coordinate.
     */
    public Board() {
        fields = new ArrayList<>();
        fields.add(new Field("A1"));
        fields.add(new Field("A2"));
        fields.add(new Field("A3"));
        fields.add(new Field("B1"));
        fields.add(new Field("B2"));
        fields.add(new Field("B3"));
        fields.add(new Field("C1"));
        fields.add(new Field("C2"));
        fields.add(new Field("C3"));
    }

    /**
     * Returns a list in which it puts every {@link Field} that is occupied by the passed {@link Player}.
     *
     * @param player {@link Player} for which it should return the occupied fields
     * @return A list with all {@link Field} that are occupied by the passed {@link Player} or an empty list if none are
     *         occupied.
     *
     */
    public List<Field> getFieldsOccupiedByPlayer(Player player) {
        List<Field> fieldsOccupiedByPlayer = new ArrayList<>();
        for (Field field : fields) {
            if (field.getOccupiedByPlayer() == player) {
                fieldsOccupiedByPlayer.add(field);
            }
        }

        return fieldsOccupiedByPlayer;
    }

    /**
     * Returns the {@link Field} matching the passed selected coordinate.
     *
     * @param selectedCoordinate the selected coordinate by the {@link Player}
     * @return The {@link Field} if the selected coordinate matches any of the field's coordinates or null if no match
     *         is found.
     *
     */
    public Field getFieldByCoordinate(String selectedCoordinate) {
        for (Field field : fields) {
            if (field.getCoordinate().equalsIgnoreCase(selectedCoordinate)) {
                return field;
            }
        }

        return null;
    }

}
