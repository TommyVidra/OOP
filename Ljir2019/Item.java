public abstract class Item implements Barcoded{ //apstraktna je jer ce je nasljeđivat, odnosno nece postojati njena instanca

    private String name;
    private String barcode;
    protected double netPrice; // neto cijena
    public String getName() { return name; }

    public Item(String name, String barcode, double netPrice) {
        this.name = name;
        this.barcode = barcode;
        this.netPrice = netPrice;
    }

    public double getSalePrice() { //za odredene iteme drugacije
        return 0;
    }

    @Override
    public String Barcode(){
        return barcode;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Item){
            Item i = (Item) o;
            if(this.name.equals(i.name) && this.barcode.equals(i.barcode) && this.netPrice == i.netPrice)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = ((name == null) ? 0 : name.hashCode()) +
                ((barcode == null) ? 0 : barcode.hashCode()) +
                (Double.valueOf(netPrice).hashCode());
        return result;
    }

    @Override
    public String toString(){
        return String.format("%s %s Net:%.2f Sale:%.2f",name, barcode, netPrice, getSalePrice());
    }
}

