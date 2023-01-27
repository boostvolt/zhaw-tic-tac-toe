package tictactoe;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Handles the game flow and communicates between the {@link Board}, {@link Console} and the two {@link Player}.
 *
 * It evaluates every game turn and checks for a winner or a tie using a recursive function.
 */
public class Game {

    private static final int MIN_WINNING_THRESHOLD_NUMBER = 3;
    private static final int MAX_NUMBER_OF_GAME_ROUNDS = 9;

    private final Board board;
    private final Console console;
    private final Player player1;
    private final Player player2;

    /**
     * Creates a new Game with the given {@link Board}, {@link Console} and two {@link Player}.
     *
     * @param board   the {@link Board} on which the game is being played
     * @param console the {@link Console} to take care of input and output
     * @param player1 the first {@link Player} participating in the current game
     * @param player2 the second {@link Player} participating in the current game
     */
    public Game(Board board, Console console, Player player1, Player player2) {
        this.board = requireNonNull(board);
        this.console = requireNonNull(console);
        this.player1 = requireNonNull(player1);
        this.player2 = requireNonNull(player2);
    }

    /**
     * Starts this game and initiates the first round. It prints out an intro where the language can be chosen and
     * players can set a name.
     * It then initiates the first game turn.
     */
    public void start() {
        int gameRound = 1;
        console.printLanguageSelection();
        console.printIntro();
        player1.setName(console.getPlayerNamePrompt(1));
        player2.setName(console.getPlayerNamePrompt(2));
        console.printKeybindingInfo();
        runGameTurn(gameRound);
    }

    /**
     * Recursive function which handles the game flow. It calls itself to run the next game turn as long as there is no
     * winner or the {@link Board} is not full.
     * Invalid turns are detected and shown to the {@link Player}.
     * Additionally, {@link Player} are given the opportunity to switch the game's language or quit the game.
     *
     * @param gameRound the current game round to detect who the current {@link Player} is and when the game is over
     */
    private void runGameTurn(int gameRound) {
        Player currentPlayer = gameRound % 2 == 0
                ? player2
                : player1;

        if (gameRound <= MAX_NUMBER_OF_GAME_ROUNDS) {
            console.printBoard(board);
            String userInput = console.printNextTurnPrompt(currentPlayer);
            processUserInput(currentPlayer, userInput);
            if (hasPlayerWon(currentPlayer)) {
                console.printBoard(board);
                console.printWin(currentPlayer.getName());
            } else {
                runGameTurn(++gameRound);
            }
        } else {
            console.printBoard(board);
            console.printDraw();
        }
    }

    /**
     * Processes and validates the userInput from the current {@link Player}. The userInput can either be a language
     * switch, a quit game command or a coordinate.
     * The coordinate is seen as invalid if it does not exist on the {@link Board} or if it is already occupied.
     * Once a valid coordinate is chosen, we set the occupied {@link Player} on the {@link Field} accordingly.
     *
     * @param currentPlayer the {@link Player} who has chosen the coordinate
     * @param userInput     the selected coordinate from the {@link Player}
     */
    private void processUserInput(Player currentPlayer, String userInput) {
        boolean validCoordinate = false;
        while (!validCoordinate) {
            Field selectedField = board.getFieldByCoordinate(userInput);
            if (console.isInputQuit(userInput)) {
                console.printQuitLine();
                System.exit(0);
            } else if (console.isInputLanguageSwitch(userInput)) {
                console.switchLanguage(userInput);
                userInput = console.nextUserInput();
            } else if (selectedField == null) {
                userInput = console.printWrongCoordinate();
            } else if (selectedField.getOccupiedByPlayer() != null) {
                userInput = console.printAlreadyOccupied();
            } else {
                selectedField.setOccupiedByPlayer(currentPlayer);
                validCoordinate = true;
            }
        }
    }

    /**
     * Returns if the current {@link Player} has won the game after the latest turn.
     *
     * @param currentPlayer the {@link Player} on which it should evaluate if they have won
     * @return {@code true} if the current {@link Player} has won vertically, horizontally or diagonally,
     *         {@code false} otherwise
     *
     */
    private boolean hasPlayerWon(Player currentPlayer) {
        List<Field> fieldsOccupiedByPlayer = board.getFieldsOccupiedByPlayer(currentPlayer);
        return fieldsOccupiedByPlayer.size() >= MIN_WINNING_THRESHOLD_NUMBER
                && (hasWonHorizontally(fieldsOccupiedByPlayer)
                    || hasWonVertically(fieldsOccupiedByPlayer)
                    || hasWonDiagonally(fieldsOccupiedByPlayer));
    }

    /**
     * Returns if the current {@link Player} has won the game horizontally.
     *
     * @param fieldsOccupiedByPlayer the {@link Field}'s occupied by the current {@link Player}
     * @return {@code true} if the current {@link Player} has won horizontally, {@code false} otherwise
     */
    private boolean hasWonHorizontally(List<Field> fieldsOccupiedByPlayer) {
        return checkAxisWin(fieldsOccupiedByPlayer, 0, 'A')
                || checkAxisWin(fieldsOccupiedByPlayer, 0, 'B')
                || checkAxisWin(fieldsOccupiedByPlayer, 0, 'C');
    }

    /**
     * Returns if the current {@link Player} has won the game vertically.
     *
     * @param fieldsOccupiedByPlayer the {@link Field}'s occupied by the current {@link Player}
     * @return {@code true} if the current {@link Player} has won vertically, {@code false} otherwise
     */
    private boolean hasWonVertically(List<Field> fieldsOccupiedByPlayer) {
        return checkAxisWin(fieldsOccupiedByPlayer, 1, '1')
                || checkAxisWin(fieldsOccupiedByPlayer, 1, '2')
                || checkAxisWin(fieldsOccupiedByPlayer, 1, '3');
    }

    /**
     * Returns if the current {@link Player} has occupied three {@link Field}'s on an axis.
     *
     * @param fieldsOccupiedByPlayer the {@link Field}'s occupied by the current {@link Player}
     * @param coordinateIndex        the index of the coordinate to check
     * @param coordinateMatcher      the coordinate to match
     * @return {@code true} if 3 {@link Field}'s on an axis are occupied by a {@link Player}, {@code false} otherwise
     */
    private boolean checkAxisWin(List<Field> fieldsOccupiedByPlayer, int coordinateIndex, char coordinateMatcher) {
        int count = 0;
        for (Field field : fieldsOccupiedByPlayer) {
            if (field.getCoordinate().charAt(coordinateIndex) == coordinateMatcher) {
                count++;
            }
        }
        return count == MIN_WINNING_THRESHOLD_NUMBER;
    }

    /**
     * Returns if the current {@link Player} has won the game diagonally.
     *
     * @param fieldsOccupiedByPlayer the {@link Field}'s occupied by the current {@link Player}
     * @return {@code true} if the current {@link Player} has won diagonally, {@code false} otherwise
     */
    private boolean hasWonDiagonally(List<Field> fieldsOccupiedByPlayer) {
        return hasMiddle(fieldsOccupiedByPlayer)
                && ((isCoordinateOccupiedByPlayer(fieldsOccupiedByPlayer, "A1") && isCoordinateOccupiedByPlayer(fieldsOccupiedByPlayer, "C3"))
                    || (isCoordinateOccupiedByPlayer(fieldsOccupiedByPlayer, "A3") && isCoordinateOccupiedByPlayer(fieldsOccupiedByPlayer, "C1")));
    }

    /**
     * Returns if the {@link Field}'s occupied by the current {@link Player} contain the middle {@link Field}.
     *
     * @param fieldsOccupiedByPlayer the {@link Field}'s occupied by the current {@link Player}
     * @return {@code true} if the {@link Field}'s occupied by the current{@link Player} contain the middle
     *         {@link Field}, {@code false} otherwise
     */
    private static boolean hasMiddle(List<Field> fieldsOccupiedByPlayer) {
        return isCoordinateOccupiedByPlayer(fieldsOccupiedByPlayer, "B2");
    }

    /**
     * Returns if the given coordinate is occupied by the current {@link Player}.
     *
     * @param fieldsOccupiedByPlayer the {@link Field} occupied by the current {@link Player}
     * @param coordinate             the coordinate to check if it is occupied by the current {@link Player}
     * @return {@code true} if the given coordinate is occupied by the current {@link Player}, {@code false} otherwise
     */
    private static boolean isCoordinateOccupiedByPlayer(List<Field> fieldsOccupiedByPlayer, String coordinate) {
        for (Field field : fieldsOccupiedByPlayer) {
            if (field.getCoordinate().equalsIgnoreCase(coordinate)) {
                return true;
            }
        }
        return false;
    }

}
