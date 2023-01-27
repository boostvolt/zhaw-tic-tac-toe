package tictactoe;

import java.util.Scanner;

/**
 * Console class to handle input and output of the game. It interacts with the command line and prints out user prompts.
 */
public class Console {

    private static final String LANGUAGE_SWITCH_ENGLISH_KEYBINDING = "E";
    private static final String LANGUAGE_SWITCH_GERMAN_KEYBINDING = "D";
    private static final String QUIT_KEYBINDING = "Q";

    private final Scanner scanner;
    private boolean isEnglish;

    /**
     * Creates a new Console.
     * It initializes the scanner which is used to read the input from the {@link Player}.
     * It also sets a default value to the language option.
     */
    public Console() {
        scanner = new Scanner(System.in);
        isEnglish = false;
    }

    /**
     * Prints out the intro text in the currently selected language.
     */
    public void printIntro() {
        if (isEnglish) {
            System.out.println("Welcome to Tic-Tac-Toe.");
        } else {
            System.out.println("Willkommen zu Tic-Tac-Toe.");
        }
    }

    /**
     * Prints out the info text for the quit and language switch keybinding in the currently selected language.
     */
    public void printKeybindingInfo() {
        if (isEnglish) {
            System.out.printf(
                    "You can press [%s] at any time during the game to quit the game, press [%s] to change the language to English or press [%s] to change the language to German.%n",
                    QUIT_KEYBINDING, LANGUAGE_SWITCH_ENGLISH_KEYBINDING, LANGUAGE_SWITCH_GERMAN_KEYBINDING);
        } else {
            System.out.printf(
                    "Du kannst jederzeit während des Spiels [%s] drücken, um das Spiel zu beenden, [%s] drücken, um die Sprache auf Englisch zu ändern oder [%s] drücken, um die Sprache auf Deutsch zu ändern.%n",
                    QUIT_KEYBINDING, LANGUAGE_SWITCH_ENGLISH_KEYBINDING, LANGUAGE_SWITCH_GERMAN_KEYBINDING);
        }
    }

    /**
     * Prints out the winning message and name of the {@link Player} who has won in the currently selected language.
     *
     * @param playerName the name of the {@link Player} who has won
     */
    public void printWin(String playerName) {
        if (isEnglish) {
            System.out.printf("Congratulations! %s, you've won!%n", playerName);
        } else {
            System.out.printf("Glückwunsch! %s, du hast gewonnen!%n", playerName);
        }
    }

    /**
     * Prints out the end of game message in case of a draw in the currently selected language.
     */
    public void printDraw() {
        if (isEnglish) {
            System.out.println("The board is full! No one has won.");
        } else {
            System.out.println("Das Brett ist voll! Keiner hat gewonnen.");
        }
    }

    /**
     * Prints out the current state of the {@link Board} in the currently selected language.
     *
     * @param board the {@link Board} object containing all {@link Field} from the current {@link Game}
     */
    public void printBoard(Board board) {
        char a1 = board.getFieldByCoordinate("A1").getSymbolIfOccupied();
        char a2 = board.getFieldByCoordinate("A2").getSymbolIfOccupied();
        char a3 = board.getFieldByCoordinate("A3").getSymbolIfOccupied();
        char b1 = board.getFieldByCoordinate("B1").getSymbolIfOccupied();
        char b2 = board.getFieldByCoordinate("B2").getSymbolIfOccupied();
        char b3 = board.getFieldByCoordinate("B3").getSymbolIfOccupied();
        char c1 = board.getFieldByCoordinate("C1").getSymbolIfOccupied();
        char c2 = board.getFieldByCoordinate("C2").getSymbolIfOccupied();
        char c3 = board.getFieldByCoordinate("C3").getSymbolIfOccupied();

        System.out.println(" ___________________");
        System.out.println("A|  " + a1 + "  |  " + a2 + "  |  " + a3 + "  |");
        System.out.println(" |_____|_____|_____|");
        System.out.println("B|  " + b1 + "  |  " + b2 + "  |  " + b3 + "  |");
        System.out.println(" |_____|_____|_____|");
        System.out.println("C|  " + c1 + "  |  " + c2 + "  |  " + c3 + "  |");
        System.out.println(" |_____|_____|_____|");
        System.out.println("    1     2     3");
    }

    /**
     * Prints out the next turn prompt in the currently selected language. It awaits and returns the next user input.
     *
     * @param player the {@link Player} object whose turn it is
     * @return the next user input as a {@code String}
     */
    public String printNextTurnPrompt(Player player) {
        if (isEnglish) {
            System.out.printf("%s, it's your turn to set your '%s'. Choose a free field and enter the coordinate: %n",
                    player.getName(), player.getSymbol());
        } else {
            System.out.printf(
                    "%s, du bist dran mit dem Setzen deines '%s'. Wähle ein freies Feld und gebe die Koordinate ein: %n",
                    player.getName(), player.getSymbol());
        }

        return nextUserInput();
    }

    /**
     * Prints out the prompt to choose a name for the {@link Player} in the currently selected language.
     * It awaits and returns the next user input.
     *
     * @param numberOfPrompt the number of the name prompt to distinguish the two {@link Player}
     * @return the next user input as a {@code String}
     */
    public String getPlayerNamePrompt(int numberOfPrompt) {
        if (isEnglish) {
            System.out.printf("Player %s, please choose a name:%n", numberOfPrompt);
        } else {
            System.out.printf("Spieler %s, bitte wähle einen Namen:%n", numberOfPrompt);
        }

        return nextUserInput();
    }

    /**
     * Prints out the error text for an illegal move when the coordinate does not match any {@link Field} in the
     * currently selected language.
     * It awaits and returns the next user input.
     *
     * @return the next user input as a {@code String}
     */
    public String printWrongCoordinate() {
        if (isEnglish) {
            System.out.println("Please choose a valid coordinate:");
        } else {
            System.out.println("Bitte wähle eine valide Koordinate aus:");
        }

        return nextUserInput();
    }

    /**
     * Prints out the error text for an illegal move when the {@link Field} is already occupied in the currently
     * selected language.
     * It awaits and returns the next user input.
     *
     * @return the next user input as a {@code String}
     */
    public String printAlreadyOccupied() {
        if (isEnglish) {
            System.out.println("Please choose an unoccupied field:");
        } else {
            System.out.println("Bitte wähle ein freies Feld aus:");
        }

        return nextUserInput();
    }

    /**
     * Prints out the text when the game was quit in the currently selected language.
     */
    public void printQuitLine() {
        if (isEnglish) {
            System.out.printf("Game quit.%nSee you next game!%n");
        } else {
            System.out.printf("Das Spiel wurde beendet.%nBis zum nächsten Spiel%n");
        }
    }

    /**
     * Prints out the language selection prompt in the currently selected language.
     * It awaits the next user input and switches the language according to the user input.
     */
    public void printLanguageSelection() {
        System.out.println("Press [" + LANGUAGE_SWITCH_ENGLISH_KEYBINDING + "] for English oder ["
                + LANGUAGE_SWITCH_GERMAN_KEYBINDING + "] für Deutsch.");
        switchLanguage(nextUserInput());
    }

    /**
     * Checks if the passed user input is matching a language switch keybinding.
     *
     * @param userInput the previous user input
     * @return {@code true} if the user input matches a language switch keybinding, otherwise {@code false}
     *
     */
    public boolean isInputLanguageSwitch(String userInput) {
        return LANGUAGE_SWITCH_GERMAN_KEYBINDING.equalsIgnoreCase(userInput)
                || LANGUAGE_SWITCH_ENGLISH_KEYBINDING.equalsIgnoreCase(userInput);
    }

    /**
     * Switches the language based on the previous user input.
     *
     * @param userInput the previous user input to switch the language
     */
    public void switchLanguage(String userInput) {
        if (LANGUAGE_SWITCH_GERMAN_KEYBINDING.equalsIgnoreCase(userInput)) {
            System.out.println("Die Sprache wurde auf Deutsch gewechselt.");
            isEnglish = false;
        } else {
            System.out.println("Language switched to English.");
            isEnglish = true;
        }
    }

    /**
     * Checks if the passed user input is matching a quit game keybinding.
     *
     * @param userInput the previous user input
     * @return {@code true} if the user input matches a quit game keybinding, otherwise {@code false}
     *
     */
    public boolean isInputQuit(String userInput) {
        return QUIT_KEYBINDING.equalsIgnoreCase(userInput);
    }

    /**
     * Prompt the {@link Player} for next user input.
     *
     * @return the next user input from the scanner as a {@code String}
     */
    public String nextUserInput() {
        return scanner.nextLine();
    }

}
