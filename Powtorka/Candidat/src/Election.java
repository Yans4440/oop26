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
        firstTurn.populate("1.csv");//Kork 7  Następnie w klasie Election, w metodzie populate() (utworzonej w kroku 3), wywołaj firstTurn.populate() przekazując do niej ścieżkę do pliku 1.csv

        //kork 13 W metodzie populate() klasy Election wywołaj metodę winner() na
        //obiekcie firstTurn. Jeśli nie zostanie rzucony wyjątek NoWinnerException, ustaw wartość zwróconą przez tę
        //metodę jako wartość pola winner w klasie Election.
        try{
            winner = firstTurn.winner();
        }catch (NoWinnderException e){
            //Kork 15
            // Jeśli wywołanie metody winner() na obiekcie firstTurn spowoduje rzucenie wyjątku, należy:
            //Uzyskać listę kandydatów, którzy zakwalifikowali się do drugiej tury,
            //Utworzyć obiekt secondTurn,
            //Wczytać dane z pliku 2.csv i zapełnić nimi obiekt secondTurn,
            //Na końcu metody populate() odczytać zwycięzcę drugiej tury i ustawić go jako zwycięzcę całych wyborów
            List<Candidate> runoff = firstTurn.runoffCandidates();
            secondTurn = new ElectionTurn(runoff);
            secondTurn.populate("2.csv");
            try {
                winner = secondTurn.winner();
            }catch (NoWinnderException ex){
                throw new RuntimeException(ex);
            }
        }
    }

    public ElectionTurn getFirstTurn(){
        return firstTurn;
    }
    public ElectionTurn getSecondTurn(){
        return secondTurn;
    }

    //Krok 13
    //W klasie Election dodaj prywatne pole winner, które będzie przechowywać referencję do zwycięskiego
    //kandydata.
    private Candidate winner;
    //Stwórz akcesor do tego pola
    public Candidate getWinner() {
        return winner;
    }



}
