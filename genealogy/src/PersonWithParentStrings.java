import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PersonWithParentStrings {
    private Person person;
    private String[] parents;

    public PersonWithParentStrings(Person person, String[] parents){

        this.person = person;
        this.parents=parents;
    }
    public static PersonWithParentStrings fromCsvLine(String line)throws NegativeLifespanException{
        Person person = Person.fromCsvLine(line);
        String[] columns = line.split(",",-1);
        return new PersonWithParentStrings(person, Arrays.copyOfRange(columns,3,5));
    }
    public String name(){
        return this.person.name();
    }

    public static void connectRelatives(Map<String, PersonWithParentStrings> peopleMap){
        for (PersonWithParentStrings child: peopleMap.values()){
            for(String parentString : child.parents){
                if(!parentString.isEmpty()) {
                    peopleMap.get(parentString).person.adopt(child.person);
                }
            }
        }
    }
    public static List<Person> unpackMap(Map<String, PersonWithParentStrings> people){
        ArrayList<Person> peopleResult = new ArrayList<>();
        for(PersonWithParentStrings personWithParentString : people.values()){
            peopleResult.add(personWithParentString.person);
        }
        return peopleResult;
    }
}