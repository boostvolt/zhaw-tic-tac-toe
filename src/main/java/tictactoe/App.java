package tictactoe;

/**
 * Starts a new Game of Tic Tac Toe.
 */
public class App {

    /**
     * Initializes the {@link Game} along with the {@link Board}, {@link Console} and two {@link Player}.
     * It assigns a default field symbol to each {@link Player} and starts the {@link Game}.
     *
     * @param args no arguments are expected
     */
    public static void main(String[] args) {
        new Game(new Board(), new Console(), new Player('X'), new Player('O')).start();
    }

}
