import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Election {
    //Krok 2
    private List<Candidate> candidates = new ArrayList<>();

    //Krok 4
    private ElectionTurn firstTurn;
    private ElectionTurn secondTurn = null;

    public Election(){
        firstTurn = new ElectionTurn(candidates);
    }

    //Krok 2
    //public Election(List<Candidate> candidates){
    //    this.candidates = candidates;
    //}

    public List<Candidate> getCandidatesCopy(){
        return new ArrayList<>(candidates);
    }

    //Krok 3
    public void populateCandidates(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));

        for(String line : lines){
            candidates.add(new Candidate(line));
        }
    }
    public void populate(String path) throws IOException {
        populateCandidates(path);
        firstTurn.populate(path); //Kork 7  Następnie w klasie Election, w metodzie populate() (utworzonej w kroku 3), wywołaj firstTurn.populate() przekazując do niej ścieżkę do pliku 1.csv
    }

    public ElectionTurn getFirstTurn(){
        return firstTurn;
    }
    public ElectionTurn getSecondTurn(){
        return secondTurn;
    }

}
