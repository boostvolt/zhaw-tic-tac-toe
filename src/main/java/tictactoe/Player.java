package tictactoe;

/**
 * Represents a participant of the game. Each Player has a dedicated symbol to indicate the field occupation.
 */
public class Player {

    private String name;
    private final char symbol;

    /**
     * Creates a new Player for the given symbol.
     *
     * @param symbol each player has an associated symbol which is used to identify by whom a {@link Field} is occupied
     */
    public Player(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Sets the name instance variable on a Player object. If the name is null or blank, the field symbol is used as the
     * name.
     *
     * @param name the name of player passed from the console
     */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            this.name = String.valueOf(symbol);
        } else {
            this.name = name;
        }
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the symbol of the player.
     *
     * @return the symbol of the player
     */
    public char getSymbol() {
        return symbol;
    }

}
