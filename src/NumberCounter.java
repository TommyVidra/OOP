import java.util.List;

public class NumberCounter {

    public static int countNumbers(List<String> lines){

        int count = 0;
        for(String l : lines){

            for(int i = 0 ; i < l.length(); ++i){

                if(Character.isDigit(l.charAt(i)))
                    count++;
            }
        }
        return count;
    }
}
