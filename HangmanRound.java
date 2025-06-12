import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class HangmanRound {
    private String[] easyWords = {"computer", "programm", "sprache", "entwicklung", "system"};
    private String[] hardWords = {"algorithmus", "programmierung", "entwickler", "software", "hardware"};
    private String word;
    private char[] guessedWord;
    private int remainingAttempts;
    private Scanner scanner;

    public HangmanRound(String level) {
        scanner = new Scanner(System.in);
        remainingAttempts = level.equals("easy") ? 9 : 7;
        selectWord(level);
        guessedWord = new char[word.length()];
        Arrays.fill(guessedWord, '_');
    }

    private void selectWord(String level) {
        Random random = new Random();
        String[] wordList;
        switch (level) {
            case "easy":
                wordList = easyWords;
                break;
            case "hard":
                wordList = hardWords;
                break;
            default:
                System.out.println("Ungültige Eingabe. Standardwert für easy verwendet.");
                wordList = easyWords;
        }
        word = wordList[random.nextInt(wordList.length)];
    }

    public boolean start() {
        System.out.println("\nWillkommen zu Hangman!");
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

    private void displayGameState() {
        System.out.println("\nWort: " + String.valueOf(guessedWord));
        System.out.println("Verbleibende Versuche: " + remainingAttempts);
    }

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

    private boolean isWordGuessed() {
        return String.valueOf(guessedWord).equals(word);
    }
} 