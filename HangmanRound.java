import java.util.Scanner;
import java.util.Arrays;

// spiellogik für eine runde hangman
public class HangmanRound {
    private String word;
    private char[] guessedWord;
    private int remainingAttempts;
    private Scanner scanner;

    // konstruktor
    public HangmanRound(String level) {
        scanner = new Scanner(System.in);
        remainingAttempts = level.equals("easy") ? 9 : 7;
        word = WordLoader.getRandomWord(level.equals("easy") ? "easy" : "hard");
        guessedWord = new char[word.length()];
        Arrays.fill(guessedWord, '_');
    }

    // startet die spielrunde
    public boolean start() {
        System.out.println("\n----------------------------------Willkommen zu Hangman!----------------------------------");
        System.out.println("Das Wort hat " + word.length() + " Buchstaben.");
     
        while (remainingAttempts > 0) {
            displayGameState();
            System.out.print("\nBitte gib einen Buchstaben ein: ");
            String input = scanner.nextLine().toLowerCase();
            
            if (input.length() != 1) {
                System.out.println("Bitte gib nur einen Buchstaben ein!");
                continue;
            }
            
            char guess = input.charAt(0);
            if (!Character.isLetter(guess)) {
                System.out.println("Bitte gib einen gültigen Buchstaben ein!");
                continue;
            }
            
            if (!makeGuess(guess)) {
                remainingAttempts--;
                System.out.println("Falsch! Noch " + remainingAttempts + " Versuche übrig.");
            }
            
            if (isWordGuessed()) {
                displayGameState();
                System.out.println("\nGlückwunsch! Du hast gewonnen!");
                return true;
            }
        }
        
        System.out.println("\nGame Over! Das Wort war: " + word);
        return false;
    }

    // zeigt aktuellen spielstand
    private void displayGameState() {
        System.out.println("\nWort: " + String.valueOf(guessedWord));
        System.out.println("Verbleibende Versuche: " + remainingAttempts);
    }

    // prüft buchstaben
    private boolean makeGuess(char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord[i] = guess;
                correctGuess = true;
            }
        }
        return correctGuess;
    }

    // prüft ob wort erraten
    private boolean isWordGuessed() {
        return String.valueOf(guessedWord).equals(word);
    }
} 