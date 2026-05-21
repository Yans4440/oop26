import java.io.IOException;import java.nio.file.Files;import java.nio.file.Path;import java.util.ArrayList;import java.util.List;

//Krok 4
public class ElectionTurn {
    private List<Candidate> candidates;

    public ElectionTurn(List<Candidate> candidates){
        this.candidates = candidates;
    }

    public List<Candidate> getCandidates(){
        return candidates;
    }


    /* Krok 7
    W klasie ElectionTurn:
Dodaj prywatne pole votes, będące listą obiektów Vote.
Zaimplementuj w niej także metodę populate(), która otrzyma ścieżkę do pliku z wynikami głosowań.
przyjmującą ścieżkę do pliku z wynikami głosowań. Metoda powinna wczytać dane z pliku i zapełnić nimi
listę votes.
Następnie w klasie Election, w metodzie populate() (utworzonej w kroku 3), wywołaj firstTurn.populate(),
przekazując do niej ścieżkę do pliku 1.csv
     */
    private List<Vote> votes = new ArrayList<>();

    public void populate(String path) throws IOException{
        List<String> lines = Files.readAllLines(Path.of(path));

        for(int i = 1;i<lines.size();i++){
            votes.add(Vote.fromCsvLine(lines.get(i),candidates));
        }


    }

    //Krok12
//W klasie ElectionTurn zaimplementuj metodę winner(), która zwraca kandydata będącego zwycięzcą tury.
//Zwycięzcą jest kandydat, który zdobył ponad 50% wszystkich oddanych głosów. Jeśli żaden kandydat nie
//osiągnie tego progu, metoda powinna rzucić (samodzielnie napisany) wyjątek NoWinnerException.
    public Candidate winner() throws NoWinnderException{
        Vote summery = Vote.summarize(votes);

        int total = 0;
        for(Integer v : summery.getVotesForCandidate().values()){
            total += v;
        }

        for(Candidate candidate : candidates){
            int votes = summery.votes(candidate);

            if((double) votes * 100 / total > 50.0){
                return candidate;
            }
        }
        throw  new NoWinnderException();
    }

    //kork 14
    // W klasie ElectionTurn zaimplementuj metodę runoffCandidates(), która zwróci listę dwóch kandydatów z
    //największą liczbą głosów.
    public List<Candidate> runoffCandidates(){
        Vote summary = Vote.summarize(votes);

        List<Candidate> sortedCandidate = new ArrayList<>(candidates);

        sortedCandidate.sort((c1, c2) -> summary.votes(c2) - summary.votes(c1));

        return  sortedCandidate.subList(0, 2);
    }


}
