import java.util.*;

public class Vote {
    /*Krok 5
    Utwórz klasę Vote, która zawiera:
    Prywatną mapę votesForCandidate, w której kluczem jest obiekt Candidate, a wartością - liczba
    uzyskanych przez niego głosów.
    Prywatną listę location, przechowującą w kolejności: nazwę województwa, powiatu i gminy
     */
    private Map<Candidate, Integer> votesForCandidate;
    private List<String> location;
    //Akcesor do listy location
    public List<String> getLocation(){
        return location;
    }

    public Vote(Map<Candidate, Integer> voteForCandidate, List<String> location){
        this.location = location;
        this.votesForCandidate = votesForCandidate;
    }
    /* Krok 6
    W klasie Vote dodaj publiczną, statyczną metodę fromCsvLine(), która:
    Przyjmuje wiersz tekstu z pliku CSV (1.csv lub 2.csv).
    Parsuje zawartość tego wiersza i na jej podstawie tworzy nowy obiekt Vote.
    Wypełnia w tym obiekcie obie struktury danych utworzone wcześniej w kroku 5. (tj. mapę głosów
    votesForCandidate oraz listę location)
     */
    public static Vote fromCsvLine(String line, List<Candidate> candidates){
        String[] parts = line.split(",");

        List<String> location = new ArrayList<>();
        location.add(parts[0]); //województwo
        location.add(parts[1]); // powiat
        location.add(parts[2]); // gmina

        Map<Candidate, Integer> votes = new HashMap<>();

        for(int i = 0;i<candidates.size();i++){
            votes.put(candidates.get(i),Integer.parseInt(parts[i+3]));
        }
        return new Vote(votes, location);
    }

    /*Krok 8
    W klasie Vote dodaj metodę summarize(), która:
Przyjmie jako argument listę obiektów Vote - wyniki głosowania z poszczególnych gmin.
Utworzy i zwróci nowy obiekt Vote, którego:
Mapa votesForCandidate będzie zawierać te same klucze co mapy w obiektach przekazanych w
argumencie.
Wartości przy tych kluczach będą sumami głosów oddanych na poszczególnych kandydatów we
wszystkich obiektach z listy.
Ustawi pole location nowego obiektu jako pustą listę
     */
    public Map<Candidate, Integer> getVotesForCandidate() {
        return votesForCandidate;
    }

    public static Vote summarize(List<Vote> votes){
        Map<Candidate, Integer> summary = new HashMap<>();


        for(Vote vote : votes){
            for(Map.Entry<Candidate, Integer> entry : vote.getVotesForCandidate().entrySet()){
                Candidate candidate = entry.getKey();
                Integer count = entry.getValue();

                summary.put(candidate, summary.getOrDefault(candidate, 0) + count);
            }
        }
        return new Vote(summary, new ArrayList<>());
    }

    //Krok 9 votes(), która zwraca liczbę głosów zapisanych w obiekcie klasy Vote
    public int votes(Candidate candidate){
        return votesForCandidate.get(candidate);
    }

    //Krok 9 percentage(), która zwraca procentowy udział głosów oddanych na niego względem łącznej liczby głosów
    //zapisanych w obiekcie Vote
    public double percentage(Candidate candidate){
        int candidateVotes = votes(candidate);

        int totalVotes = 0;
        for(Integer value : votesForCandidate.values()){
            totalVotes += value;
        }
        return (double) candidateVotes * 100/totalVotes;
    }


    //Krok 16
    //W klasie Vote zaimplementuj publiczną, statyczną metodę filterByLocation(), która przyjmuje dwie listy jako
    //argumenty:
    //listę obiektów klasy Vote,
    //listę napisów opisujących lokalizację.
    //Lista lokalizacji może zawierać:
    //jeden element - województwo (np. "lubelskie") - zwróci wszystkie obiekty z województwa,
    //dwa elementy - województwo i powiat (np. "lubelskie", "Lublin") - zwróci wszystkie obiekty z powiatu,
    //trzy elementy - województwo, powiat i gmina (np. "lubelskie", "Lublin", "m. Lublin") - zwróci dokładnie
    //jeden element odpowiadający gminie.
    //Metoda powinna zwracać nową listę obiektów Vote, których lokalizacja odpowiada wskazanemu filtrowi.
    public static List<Vote> filterByLocation(List<Vote> votes, List<String> location){
        List<Vote> result = new ArrayList<>();
        for(Vote vote : votes){
            boolean matches = true;

            for(int i = 0; i<location.size();i++){
                if(!vote.getLocation().get(i).equals(location.get(i))){
                    matches = false;
                    break;
                }
            }
            if(matches){
                result.add(vote);
            }
        }
        return result;
    }






    //Krok 10 W klasie Vote nadpisz metodę toString(), tak aby zwracała napis zawierający w kolejnych liniach imiona i
    //nazwiska kandydatów wraz z ich procentowymi wynikami. Kolejność może być dowolna. Następnie przetestuj
    //wywołanie tej metody w metodzie main()
    //11 Wyświetlenie napisu z poprzedniego kroku wymaga wielokrotnego wykorzystania sumy wszystkich głosów.
    //Zagwarantuj, że suma ta będzie obliczana tylko raz
    @Override
    public String toString(){
        String result = "";

        int totalVotes = 0;
        for(Integer value : votesForCandidate.values()){
            totalVotes += value;
        }

        for(Candidate candidate : votesForCandidate.keySet()){
            double percentage = (double) votesForCandidate.get(candidate) * 100 / totalVotes;
            result += candidate.name() + " - " + percentage + "%\n";
        }
        return  result;
    }
}
