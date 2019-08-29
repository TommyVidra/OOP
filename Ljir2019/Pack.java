import java.util.Iterator;

public class Pack extends Item{

    private Item[] items;

    public Item[] getItemsInPack(){
        return  items;
    }

    public Pack(String name, String barcode, Item[] items){
        super(name, barcode, items[0].netPrice * items.length);
        this.items = items;
    }

    @Override
    public double getSalePrice(){

        return items[0].getSalePrice()*items.length;
    }
}
