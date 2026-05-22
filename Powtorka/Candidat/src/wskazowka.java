// =============================================================================
// KOMPLETNE KOMPENDIUM JAVY: OD KLAS DO GENERYKÓW (JEDEN PLIK)
// =============================================================================
// Aby uruchomić ten kod, zapisz plik jako JavaKompendium.java i wykonaj:
//   java JavaKompendium.java
// =============================================================================

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class wskazowka {

    public static void main(String[] args) {
        System.out.println("=== ROZPOCZĘCIE PREZENTACJI KOMPENDIUM JAVA ===\n");

        // =====================================================================
        // 1 & 2. KLASY, OBIEKTY, ENKAPSULACJA I KONSTRUKTORY
        // =====================================================================
        printHeader("1 & 2. Klasy, Obiekt, Enkapsulacja i Konstruktory");

        // Tworzenie obiektów za pomocą różnych konstruktorów (Przeciążanie konstruktorów)
        KontoBankowe konto1 = new KontoBankowe("Jan Kowalski", 5000.0);
        KontoBankowe konto2 = new KontoBankowe("Anna Nowak"); // Używa przeciążonego konstruktora z this()

        // Demonstracja enkapsulacji - brak bezpośredniego dostępu do pól (konto1.saldo rzuci błąd kompilacji)
        System.out.println("Stan początkowy konta 1: " + konto1.getOpis());
        System.out.println("Stan początkowy konta 2: " + konto2.getOpis());

        // Użycie setterów/metod modyfikujących z wbudowaną walidacją danych
        System.out.println("\nPróba wpłaty ujemnej kwoty (-500):");
        konto1.wplac(-500);

        System.out.println("Wpłata poprawnej kwoty (1500):");
        konto1.wplac(1500);
        System.out.println("Nowy balans konta 1: " + konto1.getSaldo() + " PLN");

        // Prezentacja pól i metod statycznych (zmienna współdzielona przez całą klasę, wywoływana bez obiektu)
        System.out.println("\nCałkowita liczba utworzonych kont (pole static): " + KontoBankowe.getLiczbaKont());


        // =====================================================================
        // 3. DZIEDZICZENIE I POLIMORFIZM
        // =====================================================================
        printHeader("3. Dziedziczenie i Polimorfizm");

        // Polimorfizm: Typ referencji to klasa nadrzędna (Pojazd), a typ obiektu to klasa podrzędna (Samochod)
        Pojazd mojPojazd = new Samochod("Tesla", "Model 3", 4);

        // Wywołanie metody nadpisanej (@Override) - dynamiczne wiązanie metod w runtime
        mojPojazd.uruchom();
        mojPojazd.trab(); // Metoda odziedziczona wprost z klasy nadrzędnej Pojazd

        // Od Javy 16+: Bezpieczne rzutowanie (Pattern Matching for instanceof)
        if (mojPojazd instanceof Samochod auto) {
            System.out.println("Dostęp do metody specyficznej dla podklasy -> Liczba drzwi: " + auto.getLiczbaDrzwi());
        }


        // =====================================================================
        // 4. ABSTRAKCJA (KLASY ABSTRAKCYJNE I INTERFEJSY)
        // =====================================================================
        printHeader("4. Abstrakcja (Klasy Abstrakcyjne i Interfejsy)");

        // Klasa abstrakcyjna Pracownik, podklasa Programista
        Pracownik programista = new Programista("Michał", 12000.0, "Java");
        programista.pracuj();         // Implementacja metody abstrakcyjnej dostarczona przez Programistę
        programista.wyswietlDane();   // Wywołanie zwykłej metody zaimplementowanej w klasie abstrakcyjnej

        System.out.println();

        // Interfejsy: Implementacja niezależnych kontraktów (Klasa ZapalonyGracz implementuje dwa interfejsy)
        Gamer gracz = new ZapalonyGracz();
        gracz.graj();
        gracz.pauzuj();       // Wywołanie metody domyślnej (default) wprowadzonej w Java 8
        Streamer.ogloszenie(); // Wywołanie metody statycznej bezpośrednio z poziomu interfejsu


        // =====================================================================
        // 5. KONTENERY (JAVA COLLECTIONS FRAMEWORK)
        // =====================================================================
        printHeader("5. Kontenery (List, Set, Map)");

        // List - zachowuje kolejność wstawiania, pozwala na duplikaty
        List<String> listaArrayList = new ArrayList<>();
        listaArrayList.add("Java");
        listaArrayList.add("Python");
        listaArrayList.add("Java"); // Duplikat
        System.out.println("ArrayList (kolejność + duplikaty): " + listaArrayList);

        // Set - unikalne elementy. HashSet nie gwarantuje żadnej kolejności
        Set<String> zbiorHashSet = new HashSet<>(listaArrayList);
        System.out.println("HashSet (usuwa duplikaty, brak stałej kolejności): " + zbiorHashSet);

        // Map - struktura klucz-wartość. Klucze są unikalne
        Map<Integer, String> mapaPracownikow = new HashMap<>();
        mapaPracownikow.put(101, "Jan Kowalski");
        mapaPracownikow.put(102, "Anna Nowak");
        mapaPracownikow.put(101, "Mariusz Śląsk"); // Nadpisanie klucza 101 nową wartością
        System.out.println("HashMap (pary klucz-wartość, unikalne klucze): " + mapaPracownikow);

        // Bezpieczeństwo własnych obiektów w kolekcjach bazujących na hashowaniu (HashSet, HashMap)
        Set<Klient> zbiorKlientow = new HashSet<>();
        zbiorKlientow.add(new Klient("12345", "Tomasz"));
        zbiorKlientow.add(new Klient("12345", "Tomasz")); // Identyczny biznesowo obiekt (ten sam PESEL)
        System.out.println("Rozmiar zbioru klientów (powinien być 1 dzięki equals i hashCode): " + zbiorKlientow.size());


        // =====================================================================
        // 6. PLIKI I WYJĄTKI
        // =====================================================================
        printHeader("6. Pliki i Wyjątki");

        String nazwaPliku = "temp_test_plik.txt";

        // Konstrukcja Try-With-Resources (Automatyczne zamykanie zasobów implementujących AutoCloseable)
        // Obsługa Checked Exception (Wyjątek sprawdzany - kompilator wymusza blok try-catch)
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nazwaPliku))) {
            writer.write("Wpis testowy do pliku.\n");
            writer.write("Java Kompendium - demonstracja zapisu I/O.\n");
            System.out.println("Pomyślnie zapisano dane do pliku: " + nazwaPliku);
        } catch (IOException e) {
            System.err.println("Błąd operacji wejścia/wyjścia na pliku: " + e.getMessage());
        }

        // Odczyt z pliku oraz czyszczenie zasobów środowiska
        try {
            List<String> linie = Files.readAllLines(Paths.get(nazwaPliku));
            System.out.println("Odczytano linie z pliku:");
            linie.forEach(l -> System.out.println(" -> " + l));

            Files.deleteIfExists(Paths.get(nazwaPliku)); // Posprzątanie po teście
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu: " + e.getMessage());
        }

        // Prezentacja Unchecked Exception (Runtime Exception - kompilator nie zmusza do obsługi)
        try {
            System.out.println("\nWywołanie operacji dzielenia przez zero (Unchecked ArithmeticException):");
            int wynik = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Złapano wyjątek Runtime: " + e);
        } finally {
            System.out.println("Blok 'finally' wykonuje się ZAWSZE, niezależnie od tego czy błąd wystąpił.");
        }


        // =====================================================================
        // 7. PROGRAMOWANIE FUNKCYJNE
        // =====================================================================
        printHeader("7. Programowanie Funkcyjne");

        // Gotowe wbudowane Interfejsy Funkcyjne z pakietu java.util.function
        Predicate<Integer> czyParzysta = x -> x % 2 == 0; // Przyjmuje T, zwraca boolean
        Function<String, Integer> dlugoscNapisu = String::length; // Referencja do metody (zamiast s -> s.length())
        Consumer<String> logger = msg -> System.out.println("[LOG]: " + msg); // Przyjmuje T, zwraca void (akcja)

        logger.accept("Czy liczba 8 jest parzysta? " + czyParzysta.test(8));
        logger.accept("Długość słowa 'Strumienie': " + dlugoscNapisu.apply("Strumienie"));

        // Stream API - Potężne, deklaratywne przetwarzanie kolekcji danych
        List<String> programisci = List.of("Kamil", "Anna", "Krzysztof", "Andrzej", "Beata");
        System.out.println("\nOryginalna lista: " + programisci);

        List<String> przetworzonaLista = programisci.stream()
                .filter(imie -> imie.startsWith("K") || imie.startsWith("A")) // Operacja pośrednia (filtrowanie)
                .map(String::toUpperCase)                                     // Operacja pośrednia (transformacja)
                .sorted()                                                     // Operacja pośrednia (sortowanie)
                .collect(Collectors.toList());                                // Operacja terminalna (zakończenie strumienia)

        System.out.println("Strumień (Filtrowane na K lub A -> Wielkie litery -> Sortowane): " + przetworzonaLista);


        // =====================================================================
        // 8. PROGRAMOWANIE GENERYCZNE (GENERICS)
        // =====================================================================
        printHeader("8. Programowanie Generyczne");

        // Użycie własnej klasy generycznej gwarantującej bezpieczeństwo typów (Type Safety)
        Pudelko<Integer> pudelkoNaLiczbe = new Pudelko<>(42);
        System.out.println("Pudełko przechowuje typ " + pudelkoNaLiczbe.getZawartosc().getClass().getSimpleName() + " o wartości: " + pudelkoNaLiczbe.getZawartosc());

        Pudelko<String> pudelkoNaTekst = new Pudelko<>("Generyki w Javie");
        System.out.println("Pudełko przechowuje typ " + pudelkoNaTekst.getZawartosc().getClass().getSimpleName() + " o wartości: '" + pudelkoNaTekst.getZawartosc() + "'");

        // Wildcards (Znaki wieloznaczne)
        List<Integer> liczbyInt = List.of(1, 2, 3, 4);
        List<Double> liczbyDouble = List.of(1.5, 2.5, 3.5);

        System.out.println("\nPrezentacja Upper Bound Wildcard (? extends Number):");
        PomocnikGeneryczny.wyswietlNumery(liczbyInt);
        PomocnikGeneryczny.wyswietlNumery(liczbyDouble);

        System.out.println("\n=== KONIEC PREZENTACJI KOMPENDIUM JAVA ===");
    }

    private static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" TEMAT: " + title);
        System.out.println("=".repeat(60));
    }
}


// =============================================================================
// KLASY POMOCNICZE (WSPÓŁDZIELĄCE PAKIET, DOPUSZCZALNE W JEDNYM PLIKU ŹRÓDŁOWYM)
// =============================================================================

// --- TEMAT 1 & 2: Klasy, Enkapsulacja i Konstruktory ---
class KontoBankowe {
    // Hermetyzacja (Enkapsulacja) - zmienne prywatne
    private final String wlasciciel;
    private double saldo;

    // Pole statyczne - zmienna powiązana z samą klasą (wspólna dla wszystkich instancji kont)
    private static int liczbaKont = 0;

    // Konstruktor główny (dwuargumentowy)
    public KontoBankowe(String wlasciciel, double saldoPoczatkowe) {
        this.wlasciciel = wlasciciel;
        this.saldo = Math.max(saldoPoczatkowe, 0.0); // Zabezpieczenie przed ujemnym balansem
        liczbaKont++; // Inkrementacja licznika przy tworzeniu dowolnego obiektu
    }

    // Konstruktor przeciążony (jednoargumentowy) - wywołuje konstruktor główny poprzez this()
    public KontoBankowe(String wlasciciel) {
        this(wlasciciel, 0.0); // Wywołanie this() musi być pierwszą instrukcją w konstruktorze!
    }

    // Publiczne Gettery umożliwiające bezpieczny kontrolowany odczyt danych
    public String getWlasciciel() { return wlasciciel; }
    public double getSaldo() { return saldo; }

    // Metoda modyfikująca stan (Setter biznesowy) zawierająca walidację reguł logicznych
    public void wplac(double kwota) {
        if (kwota > 0) {
            this.saldo += kwota;
        } else {
            System.out.println("  [BŁĄD WALIDACJI]: Kwota wpłaty nie może być mniejsza lub równa 0!");
        }
    }

    public String getOpis() {
        return "Właściciel: " + wlasciciel + ", Saldo: " + saldo + " PLN";
    }

    // Metoda statyczna - operuje wyłącznie na strukturze samej klasy (nie ma dostępu do 'this')
    public static int getLiczbaKont() {
        return liczbaKont;
    }
}


// --- TEMAT 3: Dziedziczenie ---
class Pojazd {
    protected String marka; // protected pozwala na bezpośredni dostęp w klasach dziedziczących
    protected String model;

    public Pojazd(String marka, String model) {
        this.marka = marka;
        this.model = model;
    }

    public void uruchom() {
        System.out.println("Pojazd " + marka + " " + model + " uruchomił tradycyjny silnik.");
    }

    public final void trab() { // Słowo kluczowe final uniemożliwia nadpisanie tej metody przez podklasy
        System.out.println("Trąbienie: BEEP BEEP!");
    }
}

// Dziedziczenie po klasie nadrzędnej za pomocą słowa kluczowego extends
class Samochod extends Pojazd {
    private final int liczbaDrzwi;

    public Samochod(String marka, String model, int liczbaDrzwi) {
        // super() wywołuje konstruktor klasy rodzica i musi być zawsze na samym początku
        super(marka, model);
        this.liczbaDrzwi = liczbaDrzwi;
    }

    // Polimorfizm: Nadpisanie metody klasy bazowej w celu zmiany jej zachowania dla tej podklasy
    @Override
    public void uruchom() {
        System.out.println("Samochód elektryczny " + marka + " " + model + " aktywuje baterie i uruchamia komputer pokładowy.");
    }

    public int getLiczbaDrzwi() {
        return liczbaDrzwi;
    }
}


// --- TEMAT 4: Abstrakcja ---
// Klasa abstrakcyjna stanowi niekompletny szablon. Nie pozwala na budowanie instancji (new Pracownik() wywoła błąd)
abstract class Pracownik {
    protected String imie;
    protected double pensja;

    public Pracownik(String imie, double pensja) {
        this.imie = imie;
        this.pensja = pensja;
    }

    // Metoda abstrakcyjna: Wskazuje CO podklasa musi zrobić, ale nie definiuje kodu (brak ciala metody)
    public abstract void pracuj();

    // Klasa abstrakcyjna może posiadać w pełni zaimplementowane, standardowe metody
    public void wyswietlDane() {
        System.out.println("Dane -> Pracownik: " + imie + " | Wynagrodzenie: " + pensja + " PLN");
    }
}

class Programista extends Pracownik {
    private final String glownyJezyk;

    public Programista(String imie, double pensja, String glownyJezyk) {
        super(imie, pensja);
        this.glownyJezyk = glownyJezyk;
    }

    // Obowiązkowa implementacja metody abstrakcyjnej zadeklarowanej u rodzica
    @Override
    public void pracuj() {
        System.out.println(imie + " programuje i rozwiązuje algorytmy przy użyciu " + glownyJezyk + ".");
    }
}

// Interfejsy - Definiują czysty zestaw funkcjonalności (kontrakt zachowań)
interface Gamer {
    void graj(); // Domyślnie i niejawnie jest to metoda public abstract

    // Metoda domyślna (default) od Javy 8 - zawiera gotową implementację, nie niszczy wstecznej kompatybilności
    default void pauzuj() {
        System.out.println("[Interfejs Gamer]: Gra została wstrzymana.");
    }
}

interface Streamer {
    // Metoda statyczna w interfejsie (wprowadzona w Java 8)
    static void ogloszenie() {
        System.out.println("[Interfejs Streamer]: Zapowiedź - transmisja live rusza za 5 minut!");
    }
}

// Java nie wspiera wielodziedziczenia klas (extends), ale klasa może implementować WIELE interfejsów jednocześnie
class ZapalonyGracz implements Gamer, Streamer {
    @Override
    public void graj() {
        System.out.println("ZapalonyGracz odpala platformę gamingową i rozpoczyna mecz.");
    }
}


// --- TEMAT 5: Kontenery (Bezpieczeństwo struktur Hash) ---
class Klient {
    private final String pesel;
    private final String imie;

    public Klient(String pesel, String imie) {
        this.pesel = pesel;
        this.imie = imie;
    }

    // Kontenery HashSet/HashMap wymagają poprawnego nadpisania equals i hashCode,
    // aby poprawnie identyfikować duplikaty biznesowe, a nie odwołania do pamięci.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klient klient = (Klient) o;
        return Objects.equals(pesel, klient.pesel); // Biznesowa identyfikacja tożsamości po numerze PESEL
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel); // Wygenerowanie hashcode opartego na polu unikalnym
    }
}


// --- TEMAT 8: Programowanie Generyczne ---
// Klasa generyczna ze znacznikiem typu T (Zastępowanym konkretnym typem w fazie kompilacji)
class Pudelko<T> {
    private T zawartosc;

    public Pudelko(T zawartosc) {
        this.zawartosc = zawartosc;
    }

    public T getZawartosc() { return zawartosc; }
    public void setZawartosc(T zawartosc) { this.zawartosc = zawartosc; }
}

class PomocnikGeneryczny {
    // Upper Bound Wildcard (? extends Number) - akceptuje kolekcje dowolnego typu dziedziczącego po Number
    // (np. Integer, Double, Float). Zapewnia bezpieczny odczyt z kolekcji.
    public static void wyswietlNumery(List<? extends Number> lista) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Number n : lista) {
            sj.add(n.toString());
        }
        System.out.println("  Liczby z ograniczeniem górnym: " + sj);
    }
}