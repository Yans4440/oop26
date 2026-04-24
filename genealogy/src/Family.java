import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Family {
    private Map<String, List<Person>> people= new HashMap<>();



    public void add(Person... people){
        for(Person person : people){
            String key =person.name();
            if(this.people.containsKey(key)){
                List<Person> temp = this.people.get(key);
                temp.add(person);
                temp.sort(Person::compareTo);
            }else{
                List<Person> temp2 = new ArrayList<>();
                temp2.add(person);
                this.people.put(key, temp2);
            }
        }
    }
    public List<Person> get(String key){
        return people.get(key);
    }
}