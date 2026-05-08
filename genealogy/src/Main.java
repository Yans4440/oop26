import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Person> people = Person.fromCsv("family.csv");

            List<Person> sortedDead = Person.getDeceasedByLifespan(people);

            PlantUmlRunner.setJarPath("/home/student/Pobrane/plantuml-java8-SNAPSHOT.jar");
            PlantUmlRunner.generate(Person.generateTree(
                    people, text -> String.format("%s #FFFF00", text), sortedDead::contains), "output", "test");
            //Person.toBinaryFile(people,"data.bin");
            //List<Person> people2 = Person.fromBinaryFile("data.bin");

            //List<Person> filtered = Person.filterPersonbySubstring(people, "ska");
            //filtered.stream().map(Person::name).forEach(System.out::println);

            //List<Person> sorted = Person.filterPersonbySubstring(people, "ska");
            //sorted.stream().map(Person::name).forEach(System.out::println);
            Person p = Person.getOldestLiving(people);
            System.out.println(p);
            //List<Person> sortedDead = Person.getDeceasedByLifespan(people);
            //sortedDead.stream().map(Person::name).forEach(System.out::println);
            //System.out.println(people);
        } catch (IOException e){
            System.err.println("Blad dostepu do pliku" + e.getMessage());
        }
//        PlantUmlRunner.setJarPath("/home/student/Pobrane/plantuml-java8-SNAPSHOT.jar");
//        String umlData = "@startuml\n" +
//                "Alice -> Bob: Authentication Request\n" +
//                "Bob --> Alice: Authentication Response\n" +
//                "\n" +
//                "Alice -> Bob: Another authentication Request\n" +
//                "Alice <-- Bob: Another authentication Response\n" +
//                "@enduml";
        //PlantUmlRunner.generate(, "output", "test");

    }
}