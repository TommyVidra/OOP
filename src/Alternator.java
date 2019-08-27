import java.util.*;

public class Alternator<T> implements Iterable<T> {

    private List<Iterable<T>> iterables;

    public Alternator(Iterable<T>... iterables) {
        this.iterables = new ArrayList<>(iterables.length);
        Collections.addAll(this.iterables, iterables);
    }

    @Override
    public Iterator<T> iterator(){
        return new It();
    }

    public class It implements Iterator<T>{

        private int curr = -1;
        private List<Iterator<T>> i;
        private List<Iterator<T>> iAbs;

        public It(){

            i = new ArrayList<>(iterables.size());
            iAbs = new ArrayList<>(iterables.size());

            iterables.forEach(it -> iAbs.add(it.iterator()));
            //ovo su dvije iste funk
            for(Iterable<T> itt : iterables){
                i.add(itt.iterator());
            }

        }
        @Override
        public boolean hasNext(){
            for (Iterator<T> itt : i){
                if(itt.hasNext())
                    return true;
            }
            return false;
        }
        @Override
        public T next(){
            if(hasNext()){
                T trenutni;
                while(true){

                    curr = (curr + 1) % i.size();
                    if(i.get(curr).hasNext()){
                        trenutni = i.get(curr).next();
                        break;
                    }
                }
                return trenutni;
            }
            else{
                throw new NoSuchElementException();
            }
        }

    }
}
