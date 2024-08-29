import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LocalDate bestellDatum = getDatum(scanner, "Bitte geben Sie das Bestelldatum im Format 'dd-MM-yyyy' ein: ");
        LocalDate lieferDatum = getDatum(scanner, "Bitte geben Sie das Lieferdatum im Format 'dd-MM-yyyy' ein: ");

        long tage = Duration.between(bestellDatum.atStartOfDay(), lieferDatum.atStartOfDay()).toDays();
        System.out.println("Die Anzahl der Tage zwischen Bestellung und Auslieferung beträgt: " + tage + " Tage");

        // Berechnung der Arbeitszeit
        LocalDateTime startZeit = getZeit(scanner, "Bitte geben Sie den Arbeitsbeginn im Format 'dd-MM-yyyy HH:mm:ss' ein: ");
        LocalDateTime endZeit = getZeit(scanner, "Bitte geben Sie das Arbeitsende im Format 'dd-MM-yyyy HH:mm:ss' ein: ");

        if (endZeit.isBefore(startZeit)) {
            System.out.println("Das Arbeitsende liegt vor dem Arbeitsbeginn. Bitte überprüfen Sie die Eingaben.");
        } else {
            Duration dauer = Duration.between(startZeit, endZeit);
            long hours = dauer.toHours();
            long minutes = (dauer.toMinutes() % 60);
            long seconds = (dauer.getSeconds() % 60);

            System.out.printf("Die Arbeitszeit beträgt: %d Stunden, %d Minuten und %d Sekunden%n", hours, minutes, seconds);
        }
    }

    private static LocalDate getDatum(Scanner scanner, String prompt) {
        DateTimeFormatter datumsFormatierer = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, datumsFormatierer);
            } catch (DateTimeParseException e) {
                System.out.println("Ungültiges Datum. Bitte verwenden Sie das Format 'dd-MM-yyyy'.");
            }
        }
    }

    private static LocalDateTime getZeit(Scanner scanner, String prompt) {
        DateTimeFormatter zeitFormatierer = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return LocalDateTime.parse(input, zeitFormatierer);
            } catch (DateTimeParseException e) {
                System.out.println("Ungültiges Datum/Zeitformat. Bitte verwenden Sie das Format 'dd-MM-yyyy HH:mm:ss'.");
            }
        }
    }
}
