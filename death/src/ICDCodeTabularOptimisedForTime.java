import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ICDCodeTabularOptimisedForTime implements ICDCodeTabular{
    public Map<String, String> codeDescriptionMap = new HashMap<>();

    public ICDCodeTabularOptimisedForTime(Path path){
        try (Stream<String> lines = Files.lines(path)){
        lines.skip(88).map(String::trim).filter(s->s.matches("[A-Z][0-9]{2}.*"))
                .map(s->s.split(" ", 2))
                .forEach(parts -> codeDescriptionMap.put(parts[0],parts [1]));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getDescription(String code) {
        return codeDescriptionMap.getOrDefault(code, "?");
    }
}
