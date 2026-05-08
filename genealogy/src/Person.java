import java.io.*;
import java.nio.Buffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.InflaterOutputStream;

public class Person implements Comparable<Person>, Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Set<Person> children = new HashSet<>();
    private LocalDate death;
    private Set<Person> parents = new HashSet<>();


    public Person getYoungestChild() {
//        Iterator<Person> iter = this.children.iterator();
//        Person now = iter.next();
//        Person youngest = now;
//
//        while(true){
//            if(youngest.birthday.compareTo(now.birthday)<0){
//                youngest=now;
//            }
//            try {
//                iter.next();
//            } catch(NoSuchElementException e){
//                break;
//            }
//
//        }
        if (this.children.isEmpty()) return null;
        Person youngest = children.iterator().next();
        for (Person person : children) {
            if (youngest.compareTo(person) < 0) {
                youngest = person;
            }
        }
        return youngest;
    }

    public Person(String firstName, String lastName, LocalDate birthday, LocalDate death) throws NegativeLifespanException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.death = death;

        if (this.death != null && this.birthday.isAfter(this.death)) {
            throw new NegativeLifespanException(this);
        }
    }


    public Person(String firstName, String lastName, LocalDate birthday) throws NegativeLifespanException {
        this(firstName, lastName, birthday, null);
    }

    public boolean adopt(Person child) {
        if (child == this) {
            return false;
        }
        return children.add(child);
    }

    public List<Person> getChildren() {
        //       List<Person> result = new ArrayList<>();
        //       result.addAll(children);
//
//        result.sort(Person::compareTo);
//        return result;
        return children.stream().sorted().toList();
    }

    public static Person fromCsvLine(String line) throws NegativeLifespanException {
        String[] columns = line.split(",", -1);
        String fullName = columns[0];
        String[] name = fullName.split(" ");
        String fname = name[0];
        String lname = name[1];
        String birth = columns[1];
        String death = columns[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y");
        LocalDate birthdate = LocalDate.parse(birth, formatter);
        LocalDate deathdate = null;
        if (!death.isEmpty()) {
            deathdate = LocalDate.parse(death, formatter);
        }
        return new Person(fname, lname, birthdate, deathdate);
    }

    public static List<Person> fromCsv(String path) throws IOException {
        //List<Person> people = new ArrayList<>();
        Map<String, PersonWithParentStrings> people = new HashMap<>();
        BufferedReader file = new BufferedReader(new FileReader(path));
        file.readLine();
        String line;
        while ((line = file.readLine()) != null) {
            try {
                //Person newPerson = fromCsvLine(line);
                PersonWithParentStrings newPerson = PersonWithParentStrings.fromCsvLine(line);
                people.put(newPerson.name(), newPerson);

            } catch (NegativeLifespanException e) {
                System.err.println(e.getMessage());
            }
        }
        file.close();
        PersonWithParentStrings.connectRelatives(people);
        return PersonWithParentStrings.unpackMap(people);

    }

    public String negativeLifespanExceptionMessege() {
        return String.format("Osoba %s %s ma dae smierci %s wczesniejsza niz data urodzenia %s",
                this.firstName, this.lastName, this.death, this.birthday);
    }

    public String name() {
        return String.format("%s %s", firstName, lastName);
    }

    public static void toBinaryFile(List<Person> people, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(people);

        oos.close();

    }

    public static List<Person> fromBinaryFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Person> people = (ArrayList<Person>) ois.readObject();
        ois.close();
        return people;

    }

    public String toPlantUml(){
        StringBuilder sb= new StringBuilder();
        String myId = name().replace(" ", "_");
        sb.append(String.format("object \"%s\" as %s \n", name(), myId));

        for(Person p: parents){
            String parentId = p.name()
                    .replace(" ","_");
            sb.append(parentId).append(" <|--").append(myId).append("\n");
        }

        return sb.toString();
    }

    public static List<Person> filterPersonbySubstring(List<Person> people, String sunstring){
        return people.stream()
                .filter(person-> person.name().contains(sunstring))
                .collect(Collectors.toList());
    }

    public static List<Person> sorted(List<Person>people){
        return people.stream().sorted(Comparator.comparing(person -> person.birthday)).toList();
    }

    public long lifespan(){
        if(death==null){
            return -1;
        }else{
            return ChronoUnit.DAYS.between(birthday, death);
        }
    }

    public static List<Person> getDeceasedByLifespan(List<Person> people){
        return people.stream().filter(person -> person.death != null).sorted(Comparator.comparing(Person::lifespan).reversed()).toList();
    }

    public static Person getOldestLiving(List<Person> people){
        return people.stream()
                .filter(person -> person.death == null)
                .min(Comparator.comparing(person -> person.birthday))
                .orElse(null);
    }

    public static String generateTree(List<Person> people, Function<String, String> func, Predicate<Person> condition){
        Set<Person> objects = new HashSet<>();
        for(Person person : people){
            objects.add(person);
            objects.addAll(person.children);
        }
        Map<Boolean, List<Person>> passOrFail = people.stream()
                .collect(Collectors.partitioningBy(condition));

        String deadString = passOrFail.get(false).stream()
                .map(person -> String.format("object \"%s\"",person.name()))

                .collect(Collectors.joining("\n"));

        String livingString = passOrFail.get(true).stream()
                .map(person -> String.format("object \"%s\"",person.name()))
                .map(func)
                .collect(Collectors.joining("\n"));


        String relationString = objects.stream()
                .flatMap(parent -> parent.getChildren().stream()
                        .map(child -> String.format("\"%s\"<|--\"%s\"",parent.name(), child.name()))
                ).collect(Collectors.joining("\n"));

        return String.format("@startuml\n%s\n%s\n%s\n@enduml", deadString, livingString, relationString);
    }

    @Override
    public int compareTo(Person other) {
        return this.birthday.compareTo(other.birthday);
    }

    @Override
    public String toString() {
        return "Person{" +
                " firstName='" + firstName + "'" +
                " lastName='" + lastName + "'" +
                " birthday=" + birthday +
                " children=" + children +
                "death =" + death + "}";
    }
}