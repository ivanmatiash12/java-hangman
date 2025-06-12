import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WordLoader {
    public static String getZufaelligesWort(String level) {
        String dateiname = switch (level) {
            case "simple" -> "simple.txt";
            case "middle" -> "middle.txt";
            case "hard" -> "hard.txt";
            default -> "simple.txt";
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
