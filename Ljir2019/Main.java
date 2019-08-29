import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    //Prvi zadatak
    /*public static void main(String[] args) {
        List<Item> items = getItems();
        Item[] kosarica = new Item[items.size()];
        items.toArray(kosarica);
        Salesperson s = new Salesperson();
        s.printItems(items, i->i.Barcode().startsWith("385"));
        s.printTheBill(kosarica);
        Map<String, OriginOfProduct> origins = s.getOrigin(items);
        for(Map.Entry<String, OriginOfProduct> entry: origins.entrySet())
            System.out.println(entry);
        Set<Item> skup = new LinkedHashSet<>();
        skup.add(new Food("Dukat jogurt", "385000909", 2.20, 0.1));
        skup.add(new Food("Dukat jogurt", "385000909", 2.20, 0.1));
        skup.addAll(items);
        for(Item i: skup)
            System.out.println(i.toString());
    }
    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Beverage("Jana voda", "38523456", 5.90, 1.5));
        items.add(new Food("Lay's", "492345678", 10.00, 0.3));
        Item [] itemsInPack = new Item [6];
        for(int i=0; i < itemsInPack.length; i++)
            itemsInPack[i] = new Food("Dukat jogurt", "385000909", 2.20, 0.1);
        items.add(new Pack("Dukat jogurt pack", "385876543", itemsInPack));
        return items;
    }*/

    //Cetvrti zadatak

   /* public static void main(String[] args) {
        final String directory = "D:\\Financije";
        final String extension = "*"; // ili "pdf" ili "ppt" ili "java" ...
        Path root = Paths.get(directory);
        MetadataFileVisitor visitor = new MetadataFileVisitor(extension);
        try {
            Files.walkFileTree(root, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

// TO DO: napisati kod za ispis srednje vrijednosti veličina pronađenih datoteka
// morate koristiti kolekcijske tokove

        try {
            OptionalDouble d = visitor.getMetaData().stream().map(s -> s.split(",")).filter(s -> !s[2].equals("0")).mapToDouble(s -> Integer.parseInt(s[2])).average();
            System.out.format("Average size is %.2f", d.getAsDouble());
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

   //peti zadatak

    public static void main (String[] args){

        SimulatedTaskFrame s = new SimulatedTaskFrame();
    }
}
