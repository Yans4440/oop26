import java.nio.file.Path;

public class Main {
    public static void main(String[] args){
       /* String line = "A05.9          ,2,-,-,-,-,-,-,-,-,-,-,-,-,-,-,1,1,-,-,-,-";
        DeathCauseStatistic statistic = DeathCauseStatistic.fromCsvLine(line);


        DeathCauseStatistic.AgeBracketDeaths abd = statistic.getAge(77);

        System.out.println(abd);*/
        DeathCauseStatisticsList statistics = DeathCauseStatisticsList.fromCsv(Path.of("/home/student/Dokumenty/death/src/zgony.csv"));
        int age= 60;
        ICDCodeTabular icd = new ICDCodeTabularOptimisedForTime(Path.of("/home/student/Dokumenty/death/src/ICD10"));
        statistics.mostDeadlyDiseases(age, 10).stream()
                .forEach(stat -> System.out.println(
                        stat.getCode() + "\t" + stat.getAge(age).deathCount()+  " " + icd.getDescription(stat.getCode())
                ));


    }

}
