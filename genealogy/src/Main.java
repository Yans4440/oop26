import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Person> people = Person.fromCsv("family.csv");
            Person.toBinaryFile(people,"data.bin");
            List<Person> people2 = Person.fromBinaryFile("data.bin");
            System.out.println(people);
        } catch (IOException e){
            System.err.println("Blad dostepu do pliku" + e.getMessage());
        }
    }
}