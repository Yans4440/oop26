import java.io.File;import java.io.IOException;import java.nio.file.Files;import java.nio.file.Path;import java.util.ArrayList;import java.util.List;

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


}
