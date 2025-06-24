import java.io.*;
import java.nio.file.*;
import java.util.*;

// lädt wörter aus dateien
public class WordLoader {
    // gibt ein zufälliges wort je level zurück
    public static String getRandomWord(String level) {
        String dateiname = switch (level) {
            case "easy" -> "easy_words.txt";
            case "hard" -> "hard_words.txt";
            default -> "easy_words.txt";
        };

        try {
            List<String> woerter = Files.readAllLines(Paths.get(dateiname));
            if (!woerter.isEmpty()) {
                Random random = new Random();
                return woerter.get(random.nextInt(woerter.size())).toLowerCase();
            }
        } catch (IOException e) {
            System.out.println("konnte datei nicht laden: " + dateiname);
        }
        return "fehlerwort";
    }
}
