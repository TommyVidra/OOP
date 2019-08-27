import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Test1 {

    public static void main(String[] args) {

        //PRVI ZADATAK
        List<String> list1 = Arrays.asList("Ana", "Ivana", "Marija");
        List<String> list2 = Arrays.asList("Petar", "Zoran", "Filip", "Denis", "Miho");
        List<String> list3 = Arrays.asList("jabuka", "gljiva", "breskva", "salata");
        Alternator<String> alternator = new Alternator<>(list1, list2, list3);
        for (String s : alternator) {
            System.out.print(s + " ");
        }

        System.out.println();
        alternator.iterator().forEachRemaining(a -> System.out.print(a + " "));
        System.out.println();
        alternator.forEach(a -> System.out.print(a + " "));

        System.out.println();
        for (String s : alternator) {
            System.out.print(s + " ");
        }

        //DRUGI ZADATAK
        List<Result> results = new LinkedList<>();
        results.add(new Result("Ivana", 100));
        results.add(new Result("Klara", 150));
        results.add(new Result("Petar", 100));
        results.add(new Result("Ivana", 105));
        results.add(new Result("Vinko", 150));
        results.add(new Result("Klara", 180));
        results.add(new Result("Ivana", 105));
        results.add(new Result("Davor", 100));
        results.add(new Result("Ivana", 80));
        results.add(new Result("Zlatko", 95));
        results.add(new Result("Zlatko", 90));
        results.add(new Result("Davor", 95));
        results.add(new Result("Lucija", 100));
        results.add(new Result("Ivana", 70));
        results.add(new Result("Ana", 80));
        results.add(new Result("Ana", 90));
        results.add(new Result("Eva", 90));
        HighScore.print(results);

        //TRECI ZADATAK
        try (Scanner sc = new Scanner(System.in)) {

            String dirName = "D:\\Projects\\OOP_JESENSKI_2018";
            Path path = Paths.get(dirName);

            //TO DO: dopisati tip za varijablu visitor (obratiti pažnju na ostatak koda)
            MyFileVisitor visitor = new MyFileVisitor(); ///NEMA <PATH> TO JE KVAKA

            //TO DO: Pokreniti file visitor (ne morate pisati hvatanje IOExceptiona)
            try {
                Files.walkFileTree(path, visitor); //PRVO IDE PATH, PA VIZITOR
            } catch (IOException e) {
                System.out.println(e);
            }

            //TO DO: dovršiti tako da ispiše imena i broj brojeva u posjećenim *.java datotekama
            visitor.getMap().entrySet().stream().forEach(m -> System.out.println(m.getKey() + "\t" + m.getValue()));

            //TO DO: dovršiti tako da ispiše prosječan broj brojeva u datotekama koje u imenu imaju riječ Main
            OptionalDouble optional = visitor.getMap().entrySet().stream().filter(m -> m.getKey().contains("Main")).mapToInt(m -> m.getValue()).average();

            if (optional.isPresent())
                System.out.println("Prosjecan broj brojeva u datotekama koje sadrze rijec Main: " + optional.getAsDouble());
            else
                System.out.println("Ne postoji java datoteka koja sadrzi rijec Main.");

            //PETI ZADATAK

/*
        open 12
        open 6
        open 2
        m: 3
        close 2
        inner finally
        close 6
        null pointer exc
        finally
        end

        POANTA JE DA IMAMO TRY UNUTRAR TRY-A, TAKO DA KADA UNUTARNJI BACI EXC I NE UHVATI SE, BACA GA KASIJE VANJSKI I HVATA SE
        */
        }
    }
}
