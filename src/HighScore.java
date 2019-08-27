import java.util.*;

public class HighScore {

    public static void print(List<Result> results){

        Map<Integer, List<String>> mapa = new TreeMap<>();
        for(Result r : results){

            List<String> lista = mapa.get(r.getTime());

            if(lista == null){
                lista = new LinkedList<>();
                mapa.put(r.getTime(), lista);
            }
            lista.add(r.getName());

        }

        List<String> used = new LinkedList<>();
        int pos = 1;


        for(Map.Entry<Integer, List<String>> entry : mapa.entrySet()) {
            for (String name : entry.getValue()) {

                if (!used.contains(name)) {
                    used.add(name);
                    System.out.format("%d. %10s %5d%n", pos++, name, entry.getKey());
                }
            }
        }

    }
}
