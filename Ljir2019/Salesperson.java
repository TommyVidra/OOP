import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Salesperson {

    public void printItems(List<Item> list, Predicate<Item> p){

        list.stream().filter(p).sorted((item1, item2) -> item1.getName().compareTo(item2.getName())).forEach(item -> System.out.println(item));
    }

    public void printTheBill(Item[] items){

        double total = 0;
        for( Item item : items){

            if (item instanceof Pack){
                int n = ((Pack) item).getItemsInPack().length;
                total += ((Pack) item).getSalePrice();
                Item jedan = ((Pack) item).getItemsInPack()[0];

                System.out.format("%s %dx%.2f\n%-30s%6.2f\n", item.getName(), n, jedan.getSalePrice(),"", item.getSalePrice() );
            }
            else{
                total += item.getSalePrice();
                System.out.format("%-30s%6.2f\n", item.getName(), item.getSalePrice());
            }
        }
    }

    public Map<String, OriginOfProduct> getOrigin(List<? extends Barcoded> list){

        Map<String, OriginOfProduct> items = new HashMap<>();
        for(Barcoded b : list){

            if(b.Barcode().startsWith("385"))
                items.put(b.Barcode(), OriginOfProduct.DOMESTIC);

            else
                items.put(b.Barcode(), OriginOfProduct.FOREIGN);
        }
        return items;
    }
}
