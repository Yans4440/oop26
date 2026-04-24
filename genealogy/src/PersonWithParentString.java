import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PersonWithParentString {
    private Person person;
    private String[] parents;

    public PersonWithParentString(Person person, String[] parents){

        this.person = person;
        this.parents=parents;
    }
    public static PersonWithParentString fromCsvLine(String line)throws NegativeLifespanException{
        Person person = Person.fromCsvLine(line);
        String[] columns = line.split(",",-1);
        return new PersonWithParentString(person, Arrays.copyOfRange(columns,3,5));
    }
    public String name(){
        return this.person.name();
    }

    public static void connectRelatives(Map<String, PersonWithParentString> peopleMap){
        for (PersonWithParentString child: peopleMap.values()){
            for(String parentString : child.parents){
                peopleMap.get(parentString).person.adopt(child.person);
            }
        }
    }
    public static List<Person> unpackMap(Map<String, PersonWithParentString> people){
        ArrayList<Person> peopleResult = new ArrayList<>();
        for(PersonWithParentString personWithParentString : people.values()){
            peopleResult.add(personWithParentString.person);
        }
        return peopleResult;
    }
}
